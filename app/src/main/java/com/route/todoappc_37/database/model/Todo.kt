package com.route.todoappc_37.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todoTable")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val todoName: String? = null,

    val todoDescription: String? = null,

    val date: Date? = null,

    var isDone: Boolean? = null
)