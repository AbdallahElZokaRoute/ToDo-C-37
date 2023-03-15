package com.route.todoappc_37.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendar.core.*
import com.kizitonwose.calendar.view.*
import com.route.todoappc_37.R
import com.route.todoappc_37.callbacks.OnDeleteClickListener
import com.route.todoappc_37.database.TodosDataBase
import com.route.todoappc_37.database.model.Todo
import com.route.todoappc_37.ui.DayViewContainer
import com.route.todoappc_37.ui.fragments.adapters.TodosAdapter
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*


class TodoListFragment : Fragment() {
    lateinit var calendarView: WeekCalendarView
    lateinit var todosRecycler: RecyclerView
    lateinit var adapter: TodosAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        getTodosFromDatabase()
    }

    var selectedDate: LocalDate? = null
    var calendar: Calendar = Calendar.getInstance()
    fun getTodosFromDatabase() {
        if (isHidden || context == null || !isVisible)
            return
        val todos = if (selectedDate == null)
            TodosDataBase.getInstance(requireContext())
                .getTodoDao()
                .getTodos()
        else
            TodosDataBase
                .getInstance(requireContext())
                .getTodoDao()
                .getTodosByDate(calendar.time)
        adapter.updateData(todos)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todosRecycler = view.findViewById(R.id.todos_recycler_view)
        adapter = TodosAdapter(
            null,
            ResourcesCompat.getColor(resources, R.color.colorGreen, null),
            ResourcesCompat.getColor(resources, R.color.colorPrimaryBlue, null)
        )
        todosRecycler.adapter = adapter

        adapter.onDeleteClickListener = object : OnDeleteClickListener {
            override fun onDeleteClick(todo: Todo, position: Int) {
                TodosDataBase
                    .getInstance(requireContext())
                    .getTodoDao()
                    .deleteTodo(todo)
                getTodosFromDatabase()
                adapter.notifyItemRemoved(position)
            }
        }
        calendarView = view.findViewById(R.id.calendarView)
        calendarView.dayBinder = object : WeekDayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View): DayViewContainer {
                return DayViewContainer(view)
            }

            override fun bind(container: DayViewContainer, data: WeekDay) {

                container.dayTextView.text = data.date.dayOfMonth.toString()
                container.view.setOnClickListener {
                    // Check the day position as we do not want to select in or out dates.
                    // Keep a reference to any previous selection
                    // in case we overwrite it and need to reload it.
                    val currentSelection = selectedDate
                    if (currentSelection == data.date) {
                        // If the user clicks the same date, clear selection.
                        Log.e("TAG", "Date UnSelected ! ")
                        selectedDate = null
                        // Reload this date so the dayBinder is called
                        // and we can REMOVE the selection background.
                        getTodosFromDatabase()
                        calendarView.notifyDateChanged(currentSelection)
                    } else {
                        Log.e("TAG", "Date Selected ! ")
                        selectedDate = data.date
                        // Reload the newly selected date so the dayBinder is
                        // called and we can ADD the selection background.
                        calendarView.notifyDateChanged(data.date)
                        if (currentSelection != null) {
                            // We need to also reload the previously selected
                            // date so we can REMOVE the selection background.
                            calendarView.notifyDateChanged(currentSelection)
                        }
                    }
                }
                container.dayOfWeek.text =
                    data.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())

                if (data.date == selectedDate) {
                    container.dayTextView.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPrimaryBlue,
                            null
                        )
                    )
                    container.dayOfWeek.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPrimaryBlue,
                            null
                        )
                    )
                    val month = data.date.monthValue - 1
                    calendar.set(Calendar.YEAR, data.date.year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, data.date.dayOfMonth)
                    Log.e("TAG", "bind: Month ${calendar.get(Calendar.MONTH)}")
                    Log.e("TAG", "bind: Month ${data.date.monthValue}")
                    Log.e("TAG", "bind: Day ${calendar.get(Calendar.DAY_OF_MONTH)}")
                    Log.e("TAG", "bind: Day ${data.date.dayOfMonth}")
                    Log.e("TAG", "bind: Year ${data.date.year}")
                    Log.e("TAG", "bind: Year ${calendar.get(Calendar.YEAR)}")

                    calendar.clearTime()
                    val todos = TodosDataBase
                        .getInstance(requireContext())
                        .getTodoDao()
                        .getTodosByDate(calendar.time)

                    adapter.updateData(todos)
                } else {
                    container.dayTextView.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.black,
                            null
                        )
                    )
                    container.dayOfWeek.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.black,
                            null
                        )
                    )

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
}