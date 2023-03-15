package com.route.todoappc_37.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todoappc_37.R
import com.route.todoappc_37.callbacks.OnAddTodoListener
import com.route.todoappc_37.database.TodosDataBase
import com.route.todoappc_37.database.model.Todo
import java.util.Calendar

class AddTodoBottomSheetFragment : BottomSheetDialogFragment() {
    lateinit var titleEditText: EditText
    lateinit var detailsEditText: EditText
    lateinit var selectDate: TextView
    lateinit var addTodoButton: Button
    var onAddTodoListener: OnAddTodoListener? = null
    lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)

    }

    fun initViews(view: View) {
        titleEditText = view.findViewById(R.id.input_todo_title)
        detailsEditText = view.findViewById(R.id.input_todo_details)
        selectDate = view.findViewById(R.id.date_text_view)
        calendar = Calendar.getInstance()

        selectDate.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext())
            datePicker.show()
            datePicker.setOnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                calendar.set(Calendar.MONTH, month)
                selectDate.text = "$dayOfMonth-${month + 1}-$year"
            }

        }
        addTodoButton = view.findViewById(R.id.add_new_task)

        addTodoButton.setOnClickListener {
            if (validateForm()) {
                calendar.clearTime()
                val todo = Todo(
                    todoName = titleEditText.text.toString(),
                    todoDescription = detailsEditText.text.toString(),
                    date = calendar.time
                )
                TodosDataBase
                    .getInstance(context = requireContext())
                    .getTodoDao()
                    .insertTodo(todo)
                onAddTodoListener?.onAddedTodo()
                dismiss()
            }
        }
    }

    fun validateForm(): Boolean {
        var isValid = true
        if (titleEditText.text.isEmpty()) {
            isValid = false
            titleEditText.error = "Enter todo title"
        } else if (detailsEditText.text.isEmpty()) {
            isValid = false
            detailsEditText.error = "Enter todo Description"
        } else if (selectDate.text == "Select Date...") {
            isValid = false
        }
        return isValid


    }

}