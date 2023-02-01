package com.route.todoappc_37.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.kizitonwose.calendar.core.*
import com.kizitonwose.calendar.view.*
import com.route.todoappc_37.R
import com.route.todoappc_37.database.MyDataBase
import com.route.todoappc_37.database.model.Todo
import com.route.todoappc_37.ui.DayViewContainer
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*


class TodoListFragment : Fragment() {
    lateinit var calendarView: WeekCalendarView
    lateinit var todosRecycler: DragDropSwipeRecyclerView
    lateinit var mAdapter: SwipAdapter



    /*
      1- Activity should have reference on fragments
     */

    private val onItemSwipeListener = object : OnItemSwipeListener<Todo> {
        override fun onItemSwiped(
            position: Int,
            direction: OnItemSwipeListener.SwipeDirection,
            item: Todo,
        ): Boolean {

            when (direction) {
                OnItemSwipeListener.SwipeDirection.LEFT_TO_RIGHT -> onItemSwipedLeft(item)

                else -> {}
            }


            return false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        getTodos()


    }

    private var selectedDate: LocalDate? = null


    fun getTodos() {
        if(context ==null)return
        val todos = MyDataBase.getInstance(requireContext()).getTodoDao().getTodos()
        mAdapter.updateData(todos)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        mAdapter = SwipAdapter(
            null, ResourcesCompat.getColor(resources, R.color.colorGreen, null),
            ResourcesCompat.getColor(resources, R.color.colorPrimaryBlue, null)
        )



        todosRecycler = view.findViewById(R.id.todos_recycler_view)
        todosRecycler.layoutManager = LinearLayoutManager(context)
        todosRecycler.adapter = mAdapter
        todosRecycler.orientation =
            DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_UNCONSTRAINED_DRAGGING
        todosRecycler.behindSwipedItemLayoutId = R.layout.behind_swiped
        todosRecycler.swipeListener = onItemSwipeListener




        calendarView = view.findViewById(R.id.calendarView)
        calendarView.dayBinder = object : WeekDayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View): DayViewContainer {
                return DayViewContainer(view)
            }

            override fun bind(container: DayViewContainer, data: WeekDay) {
                var calendar : Calendar = Calendar.getInstance()
                container.dayTextView.text = data.date.dayOfMonth.toString()
                container.dayOfWeek.text = data.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                container.view.setOnClickListener {
                    // Check the day position as we do not want to select in or out dates.

                        // Keep a reference to any previous selection
                        // in case we overwrite it and need to reload it.
                        val currentSelection = selectedDate
                        if (currentSelection == data.date) {
                            // If the user clicks the same date, clear selection.
                            selectedDate = null

                            getTodos()
                            // Reload this date so the dayBinder is called
                            // and we can REMOVE the selection background.
                            calendarView.notifyDateChanged(currentSelection)
                        } else {
                            selectedDate = data.date
                            // Reload the newly selected date so the dayBinder is
                            // called and we can ADD the selection background.
                            calendarView.notifyDateChanged(data.date)
                            if (currentSelection != null) {
                                // We need to also reload the previously s elected
                                // date so we can REMOVE the selection background.
                                calendarView.notifyDateChanged(currentSelection)
                            }
                        }
                }


                if (data.date == selectedDate){

                    container.dayTextView.setTextColor(ResourcesCompat.getColor(resources,R.color.colorPrimaryBlue,null))
                    container.dayOfWeek.setTextColor(ResourcesCompat.getColor(resources, R.color.colorPrimaryBlue,null))
                    val month = data.date.monthValue - 1
                    calendar.set(Calendar.YEAR,data.date.year)
                    calendar.set(Calendar.MONTH,month)
                    calendar.set(Calendar.DAY_OF_MONTH, data.date.dayOfMonth)


                    calendar.clearTime()
                   val todos = MyDataBase.getInstance(requireContext()).getTodoDao().getTodosByDate(calendar.time)
                    mAdapter.updateData(todos)

                }
                else{

                    container.dayTextView.setTextColor(ResourcesCompat.getColor(resources,R.color.black,null))
                    container.dayOfWeek.setTextColor(ResourcesCompat.getColor(resources, R.color.black,null))

                }




            }
        }
        val currentDate = LocalDate.now()
        val currentMonth = YearMonth.now()
        val startDate = currentMonth.minusMonths(100).atStartOfMonth() // Adjust as needed
        val endDate = currentMonth.plusMonths(100).atEndOfMonth()  // Adjust as needed
        val firstDayOfWeek = firstDayOfWeekFromLocale() // Available from the library
        firstDayOfWeek.name.substring(0, 3)
        val daysOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.SATURDAY)
        calendarView.setup(startDate, endDate, daysOfWeek.first())
        calendarView.scrollToWeek(currentDate)

    }

    private fun onItemSwipedLeft(todo: Todo) {
        MyDataBase.getInstance(requireContext()).getTodoDao().deleteTodo(todo)
    }
}