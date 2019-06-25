package hu.ait.todorecyclerviewdemo

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import hu.ait.todorecyclerviewdemo.adapter.TodoAdapter
import hu.ait.todorecyclerviewdemo.data.AppDatabase
import hu.ait.todorecyclerviewdemo.data.Todo
import hu.ait.todorecyclerviewdemo.touch.TodoReyclerTouchCallback
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity(),
    TodoDialog.TodoHandler {

    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            TodoDialog().show(supportFragmentManager, "Dialog")
        }

        initRecyclerView()
    }

    fun initRecyclerView() {
        Thread {
            var todos = AppDatabase.getInstance(this@ScrollingActivity).
                todoDao().getAllTodos()

            runOnUiThread {
                todoAdapter = TodoAdapter(this, todos)
                recyclerTodo.adapter = todoAdapter

                val touchCallbackList = TodoReyclerTouchCallback(todoAdapter)
                val itemTouchHelper = ItemTouchHelper(touchCallbackList)
                itemTouchHelper.attachToRecyclerView(recyclerTodo)
            }
        }.start()



        //var itemDivider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        //recyclerTodo.addItemDecoration(itemDivider)

        //recyclerTodo.layoutManager = GridLayoutManager(this, 2)
        //recyclerTodo.layoutManager = StaggeredGridLayoutManager(2,
        //    StaggeredGridLayoutManager.VERTICAL)

    }




    override fun todoCreated(todo: Todo) {
        Thread{
            AppDatabase.getInstance(this@ScrollingActivity).
                todoDao().insertTodo(todo)

            runOnUiThread {
                todoAdapter.addTodo(todo)
            }
        }.start()
    }
}
