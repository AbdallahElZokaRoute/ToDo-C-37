package com.route.todoappc_37.ui.fragments

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.text.method.TextKeyListener.clear
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.ernestoyaquello.dragdropswiperecyclerview.util.DragDropSwipeDiffCallback
import com.route.todoappc_37.BaseRepository
import com.route.todoappc_37.R
import com.route.todoappc_37.database.MyDataBase
import com.route.todoappc_37.database.model.Todo
import java.util.Collections.addAll




class SwipAdapter(var todosList: List<Todo>?, val doneColor: Int, val primaryColor: Int) : DragDropSwipeAdapter<Todo, SwipAdapter.ViewHolder>() {





    fun updateData(todosList: List<Todo>?) {


        super.dataSet = todosList!!
        this.todosList = todosList

        notifyDataSetChanged()
    }







    override fun getViewHolder(itemView: View) = ViewHolder(itemView)



    override fun onBindViewHolder(item: Todo, viewHolder: ViewHolder, position: Int) {




        viewHolder.taskTitle.text = todosList?.get(position)?.todoName
        viewHolder.taskDate.text = todosList?.get(position)?.date.toString()
        if (todosList?.get(position)?.isDone == true) {
            viewHolder.line.setBackgroundColor(doneColor)
            viewHolder.checkImage.visibility = View.INVISIBLE
            viewHolder.doneTextView.visibility = View.VISIBLE

        } else {
            viewHolder.line.setBackgroundColor(primaryColor)
            viewHolder.checkImage.visibility = View.VISIBLE
            viewHolder.doneTextView.visibility = View.INVISIBLE


        }

    }




    override fun getViewToTouchToStartDraggingItem(
        item: Todo,
        viewHolder: ViewHolder,
        position: Int,
    ): View? {
       return viewHolder.taskTitle
    }


    override fun canBeDragged(item: Todo, viewHolder: ViewHolder, position: Int): Boolean {

        return false
    }


    class ViewHolder( itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView){

        val taskTitle: TextView = itemView.findViewById(R.id.task_title)
        val taskDate: TextView = itemView.findViewById(R.id.date_text)
        val checkImage: ImageView = itemView.findViewById(R.id.check_image)
        val line: View = itemView.findViewById(R.id.line)
        val doneTextView: TextView = itemView.findViewById(R.id.done_text)


    }



}



