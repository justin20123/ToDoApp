package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.DetailTodoViewModel

class CreateTodoFragment : Fragment() {

    private lateinit var viewModel: DetailTodoViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val btnAdd = view.findViewById<Button>(R.id.btnAddTodo)
        btnAdd.setOnClickListener {
            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(), 0, 0)
            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }

}