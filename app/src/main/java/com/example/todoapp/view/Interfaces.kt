package com.example.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.example.todoapp.model.Todo

interface TodoCheckedChangeListener {
    fun onTodoCheckedChange(cb:CompoundButton, isChecked:Boolean, obj:Todo)
}

interface TodoEditClickListener {
    fun onTodoEditClick(v:View)
}

interface RadioClickListener {
    fun onRadioClick(v:View, priority:Int, obj:Todo)
}

interface TodoSaveChangesListener {
    fun onTodoSaveChanges(v:View, obj:Todo)
}