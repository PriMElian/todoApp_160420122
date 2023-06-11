package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentEditTodoBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.DetailTodoViewModel
import com.google.android.material.textfield.TextInputEditText

class EditTodoFragment : Fragment(), RadioClickListener, TodoSaveChangesListener {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding:FragmentEditTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_create_todo, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater,
            R.layout.fragment_edit_todo, container, false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        var txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
        var btnCreateTodo = view.findViewById<Button>(R.id.btnCreateTodo)

        /*txtJudulTodo.text = "Edit Todo"
        btnCreateTodo.text = "Save Changes"*/

        /*btnCreateTodo.setOnClickListener {
            val radio = view.findViewById<RadioGroup>(R.id.radioGroupPriority).checkedRadioButtonId
            val txtTitle = view.findViewById<TextInputEditText>(R.id.txtTitle)
            var txtNotes = view?.findViewById<TextInputEditText>(R.id.txtNotes)

            viewModel.update(txtTitle.text.toString(), txtNotes?.text.toString(), radio.toString().toInt(), uuid = id)
            Toast.makeText(view.context, "Todo Update", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }*/

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        dataBinding.radiolistener = this
        dataBinding.listener = this

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it

           /* var txtTitle = view?.findViewById<TextView>(R.id.txtTitle)
            txtTitle?.setText(it.title)
            var txtNotes = view?.findViewById<TextView>(R.id.txtNotes)
            txtNotes?.setText(it.notes)

            var radioLow = view?.findViewById<RadioButton>(R.id.radioLow)
            var radioMedium = view?.findViewById<RadioButton>(R.id.radioMedium)
            var radioHigh = view?.findViewById<RadioButton>(R.id.radioHigh)
            when (it.priority) {
                1 -> radioLow?.isChecked = true
                2 -> radioMedium?.isChecked = true
                else -> radioHigh?.isChecked = true
            }*/
        })
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onTodoSaveChanges(v: View, obj: Todo) {
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo updated", Toast.LENGTH_SHORT).show()
    }
}