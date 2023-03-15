package com.route.todoappc_37.callbacks

import com.route.todoappc_37.database.model.Todo

interface OnDeleteClickListener {
    fun onDeleteClick(todo: Todo, position: Int)

}
