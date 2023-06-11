package com.example.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.TodoItemLayoutBinding
import com.example.todoapp.model.Todo

class TodoListAdapter(val todoList: ArrayList<Todo>, val adapterOnClick: (Todo) -> Unit):
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),
    TodoCheckedChangeListener, TodoEditClickListener {
    class TodoViewHolder(var view: TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater, R.layout.todo_item_layout, parent,false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        /*val todo = todoList[position]
        var checkTask = holder.view.findViewById<CheckBox>(R.id.checkTask)
        checkTask.text = todoList[position].title
        checkTask.isChecked = todo.isDone == 1
        checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked == true) {
                todo.isDone = 1
                adapterOnClick(todo)
            }
            if (!checkTask.isChecked) {
                todo.isDone = 0
            }
        }

        var imgEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
        imgEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }*/

        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.editListener = this
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if (isChecked) {
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val action = TodoListFragmentDirections.actionEditTodoFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}