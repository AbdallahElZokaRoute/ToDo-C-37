package com.route.todoappc_37.ui

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.ViewContainer
import com.route.todoappc_37.R
import java.time.LocalDate

class DayViewContainer(view: View) : ViewContainer(view) {
    val dayTextView: TextView = view.findViewById(R.id.calendarDayText)
    val dayOfWeek: TextView = view.findViewById(R.id.calenderDayOfWeek)
}
