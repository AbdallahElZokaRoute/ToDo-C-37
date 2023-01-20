package com.route.todoappc_37.ui.fragments

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendar.core.*
import com.kizitonwose.calendar.view.*
import com.route.todoappc_37.Handlers
import com.route.todoappc_37.R
import com.route.todoappc_37.database.MyDataBase
import com.route.todoappc_37.ui.DayViewContainer
import com.route.todoappc_37.ui.Prefrences.PreferenceManager
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
        val todos = MyDataBase.getInstance(requireContext()).getTodoDao().getTodos()
        adapter.updateData(todos)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todosRecycler = view.findViewById(R.id.todos_recycler_view)
        adapter = TodosAdapter(
            null,
            ResourcesCompat.getColor(resources, R.color.colorGreen, null),
            ResourcesCompat.getColor(resources, R.color.colorPrimaryBlue, null)
        )
        todosRecycler.adapter = adapter


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
}