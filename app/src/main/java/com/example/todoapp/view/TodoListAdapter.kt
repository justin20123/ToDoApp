package com.example.todoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.todoapp.R
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoListAdapter (val todoList: ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(){
    class TodoViewHolder(var view: View): RecyclerView.ViewHolder(view)



    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        return TodoViewHolder(view)

    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int)
    {
        val imgEdit = holder.view.findViewById<Button>(R.id.imgEdit)
        val checkTask = holder.view.findViewById<CheckBox>(R.id.checkTask)
        imgEdit.setOnClickListener {
            val action =
                TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }

        checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            adapterOnClick(todoList[position])

        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

}