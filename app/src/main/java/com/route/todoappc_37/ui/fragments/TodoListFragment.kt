package com.route.todoappc_37.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
import java.util.*


class TodoListFragment : Fragment() {
    lateinit var calendarView: WeekCalendarView
    lateinit var todosRecycler: DragDropSwipeRecyclerView
    lateinit var mAdapter: SwipAdapter

    private val onItemSwipeListener = object : OnItemSwipeListener<Todo> {
        override fun onItemSwiped(position: Int, direction: OnItemSwipeListener.SwipeDirection, item: Todo): Boolean {

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
        val todos = MyDataBase.getInstance(requireContext()).getTodoDao().getTodos()
        mAdapter.updateData(todos)


    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        mAdapter = SwipAdapter(null ,ResourcesCompat.getColor(resources, R.color.colorGreen, null),
            ResourcesCompat.getColor(resources, R.color.colorPrimaryBlue, null))


        todosRecycler = view.findViewById(R.id.todos_recycler_view)
        todosRecycler.layoutManager = LinearLayoutManager(context)
        todosRecycler.adapter = mAdapter
        todosRecycler.orientation = DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_UNCONSTRAINED_DRAGGING
        todosRecycler.behindSwipedItemLayoutId = R.layout.behind_swiped
        todosRecycler.swipeListener = onItemSwipeListener





        calendarView = view.findViewById(R.id.calendarView)
        calendarView.dayBinder = object : WeekDayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View): DayViewContainer {
                return DayViewContainer(view)
            }

            override fun bind(container: DayViewContainer, data: WeekDay) {
                container.dayTextView.text = data.date.dayOfMonth.toString()
                container.dayOfWeek.text = data.date.dayOfWeek.toString()
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