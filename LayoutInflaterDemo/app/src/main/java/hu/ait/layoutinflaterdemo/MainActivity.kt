package hu.ait.layoutinflaterdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_row.*
import kotlinx.android.synthetic.main.todo_row.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            createTodo()
        }


    }


    fun createTodo() {
        var todoView = layoutInflater.inflate(R.layout.todo_row, null)

        todoView.tvTodoText.text = etTodo.text.toString()

        todoView.btnDelete.setOnClickListener {
            layoutMain.removeView(todoView)
        }

        layoutMain.removeAllViews()

        layoutMain.addView(todoView, 0)
    }
}
