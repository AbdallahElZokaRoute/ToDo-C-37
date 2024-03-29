package com.route.todoappc_37.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.todoappc_37.R
import com.route.todoappc_37.callbacks.OnDeleteClickListener
import com.route.todoappc_37.database.model.Todo

class TodosAdapter(var todosList: List<Todo>?, val doneColor: Int, val primaryColor: Int) :
    Adapter<TodosAdapter.TodosViewHolder>() {
    var onDeleteClickListener: OnDeleteClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodosViewHolder(view)
    }

    fun updateData(todosList: List<Todo>?) {
        this.todosList = todosList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        holder.taskTitle.text = todosList?.get(position)!!.todoName
        holder.taskDate.text = todosList?.get(position)?.date.toString()

        if (todosList?.get(position)?.isDone == true) {
            holder.line.setBackgroundColor(
                ResourcesCompat.getColor(
                    holder.itemView.resources, R.color.colorGreen, null
                )
            )
            holder.checkImage.visibility = View.INVISIBLE
            holder.doneTextView.visibility = View.VISIBLE
        } else {
            holder.line.setBackgroundColor(
                ResourcesCompat.getColor(
                    holder.itemView.resources,
                    R.color.colorPrimaryBlue,
                    null
                )
            )
            holder.checkImage.visibility = View.VISIBLE
            holder.doneTextView.visibility = View.INVISIBLE
        }
        holder.deleteLayout.setOnClickListener {
            onDeleteClickListener?.onDeleteClick(
                todosList?.get(position) ?: return@setOnClickListener, position
            )

        }
    }

    override fun getItemCount(): Int {
        return todosList?.size ?: 0
    }


    class TodosViewHolder(itemView: View) : ViewHolder(itemView) {
        val taskTitle: TextView = itemView.findViewById(R.id.task_title)
        val taskDate: TextView = itemView.findViewById(R.id.date_text)
        val checkImage: ImageView = itemView.findViewById(R.id.check_image)
        val line: View = itemView.findViewById(R.id.line)
        val doneTextView: TextView = itemView.findViewById(R.id.done_text)
        val deleteLayout: LinearLayout = itemView.findViewById(R.id.right_view)
    }
}

