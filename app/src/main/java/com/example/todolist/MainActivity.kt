package com.example.todolist

<<<<<<< HEAD
<<<<<<< HEAD
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
=======
=======
>>>>>>> 60898ae3102659eee2f263bee7f82e036092da82
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
<<<<<<< HEAD
>>>>>>> 60898ae3102659eee2f263bee7f82e036092da82
=======
>>>>>>> 60898ae3102659eee2f263bee7f82e036092da82
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()
    private lateinit var dbHelper: TaskDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = TaskDBHelper(this)
        tasks.addAll(dbHelper.getAllTasks())

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val floatingActionButton: FloatingActionButton = findViewById(R.id.floatingActionButton)

        taskAdapter = TaskAdapter(tasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        floatingActionButton.setOnClickListener {
            showAddTaskDialog()
        }

<<<<<<< HEAD
<<<<<<< HEAD
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
=======
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
>>>>>>> 60898ae3102659eee2f263bee7f82e036092da82
=======
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
>>>>>>> 60898ae3102659eee2f263bee7f82e036092da82
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
<<<<<<< HEAD
<<<<<<< HEAD
                val task = tasks[position]
                taskAdapter.deleteTask(position)
                dbHelper.deleteTask(task.id)
=======
                taskAdapter.deleteTask(position)
>>>>>>> 60898ae3102659eee2f263bee7f82e036092da82
=======
                taskAdapter.deleteTask(position)
>>>>>>> 60898ae3102659eee2f263bee7f82e036092da82
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
                    val id = UUID.randomUUID().hashCode()
                    val task = Task(id, title, description)
                    tasks.add(task)
                    taskAdapter.notifyItemInserted(tasks.size - 1)
                    dbHelper.addTask(task)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }
<<<<<<< HEAD
<<<<<<< HEAD
}
=======
}
>>>>>>> 60898ae3102659eee2f263bee7f82e036092da82
=======
}
>>>>>>> 60898ae3102659eee2f263bee7f82e036092da82
