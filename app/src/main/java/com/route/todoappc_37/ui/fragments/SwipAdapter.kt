package com.route.todoappc_37.ui.fragments

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeAdapter
import com.route.todoappc_37.R
import com.route.todoappc_37.database.model.Todo


class SwipAdapter(var todosList: List<Todo>?, val doneColor: Int, val primaryColor: Int) : DragDropSwipeAdapter<Todo, SwipAdapter.ViewHolder>() {

    var onImageClickListener : OnImageClickListener ?= null




    fun updateData(todosList: List<Todo>?) {


        super.dataSet = todosList!!
        this.todosList = todosList

        notifyDataSetChanged()
    }





    override fun getViewHolder(itemView: View) = ViewHolder(itemView)



    override fun onBindViewHolder(item: Todo, viewHolder: ViewHolder, position: Int) {


        var taskDate = todosList?.get(position)?.date.toString()


        taskDate = trimmer(taskDate)
        viewHolder.taskTitle.text = todosList?.get(position)?.todoName
        viewHolder.taskDate.text = taskDate





        if (todosList?.get(position)?.isDone == true) {
            viewHolder.line.setBackgroundColor(doneColor)
            viewHolder.checkImage.alpha = 0F
            viewHolder.doneTextView.alpha = 1F
            viewHolder.taskTitle.setTextColor(doneColor)


        } else {
            viewHolder.line.setBackgroundColor(primaryColor)
            viewHolder.checkImage.alpha = 1F
            viewHolder.doneTextView.alpha = 0F
            viewHolder.taskTitle.setTextColor(primaryColor)

        }


        viewHolder.checkImage.setOnClickListener {

            if (todosList?.get(position)?.isDone == true) {
                viewHolder.line.setBackgroundColor(primaryColor)
                viewHolder.checkImage.alpha = 1F
                viewHolder.doneTextView.alpha = 0F
                viewHolder.taskTitle.setTextColor(primaryColor)


                todosList!!.get(position).isDone = false
                onImageClickListener?.onImageClick(item)


            }
            else {

                viewHolder.line.setBackgroundColor(doneColor)
                viewHolder.taskTitle.setTextColor(doneColor)
                viewHolder.checkImage.alpha = 0F
                viewHolder.doneTextView.alpha = 1F
                onImageClickListener?.onImageClick(item)
                todosList!!.get(position).isDone = true
                onImageClickListener?.onImageClick(item)

            }
            notifyDataSetChanged()


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

    override fun canBeSwiped(item: Todo, viewHolder: ViewHolder, position: Int): Boolean {
        return super.canBeSwiped(item, viewHolder, position)
    }


    class ViewHolder( itemView: View) : DragDropSwipeAdapter.ViewHolder(itemView){

        val taskTitle: TextView = itemView.findViewById(R.id.task_title)
        val taskDate: TextView = itemView.findViewById(R.id.date_text)
        val checkImage: ImageView = itemView.findViewById(R.id.check_image)
        val line: View = itemView.findViewById(R.id.line)
        val doneTextView: TextView = itemView.findViewById(R.id.done_text)



    }

    fun trimmer(string: String): String {

        val listOfSplitContent = string.split(" ")
        return listOfSplitContent.joinToString(" ", postfix = " ", limit = 3, truncated = "")

    }




}
interface OnImageClickListener{

    fun onImageClick(item: Todo)
}

/*
         to explain what the hell is this :
         this was intended to trim down the taskDate. I used substring until 00:00:00 for it.
         But when I woke up, i found that when adding a task after 12pm, the 00:00:00 part of the line would now be added as
          12:00:00 instead and throws an outofbound exception of some sort. commented out since a better solution was found.






        try {
            trimmedTitle = trimmedTitle.substring(0,trimmedTitle.indexOf("00:00:00") )
        }
        catch (e: StringIndexOutOfBoundsException) {
            Log.e("00:00:00","outofbound")
        }
        try {
            trimmedTitle = trimmedTitle.substring(0,trimmedTitle.indexOf("12:00:00") )
        }
        catch (e: StringIndexOutOfBoundsException) {
            Log.e("12:00:00","outofbound")
        }

*/

