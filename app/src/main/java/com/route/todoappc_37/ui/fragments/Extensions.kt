package com.route.todoappc_37.ui.fragments

import java.util.Calendar

fun Calendar.clearTime(){

    this.clear(Calendar.HOUR_OF_DAY)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MILLISECOND)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.HOUR)
}