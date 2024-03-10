package com.example.todolist

import Task
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val floatingActionButton: FloatingActionButton = findViewById(R.id.floatingActionButton)

        taskAdapter = TaskAdapter(tasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        floatingActionButton.setOnClickListener {
            showAddTaskDialog()
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                taskAdapter.deleteTask(position)
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun showAddTaskDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_task_dialog, null)
        val taskEditText = dialogView.findViewById<EditText>(R.id.task_edit_text)
        val taskDescriptionEditText = dialogView.findViewById<EditText>(R.id.task_description_edit_text)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add Task")
            .setMessage("What do you want to add to your list?")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val title = taskEditText.text.toString()
                val description = taskDescriptionEditText.text.toString()
                if (title.isNotEmpty()) {
                    val id = UUID.randomUUID().toString()
                    tasks.add(Task(id.hashCode(), title, description))
                    taskAdapter.notifyItemInserted(tasks.size - 1)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }
}