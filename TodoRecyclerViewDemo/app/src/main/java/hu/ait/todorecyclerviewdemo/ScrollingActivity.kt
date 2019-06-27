package hu.ait.todorecyclerviewdemo

import android.content.Context
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
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt

class ScrollingActivity : AppCompatActivity(),
    TodoDialog.TodoHandler {

    companion object {
        public val KEY_TODO_EDIT = "KEY_TODO_EDIT"
    }


    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            TodoDialog().show(supportFragmentManager, "Dialog")
        }

        initRecyclerView()

        if (!getWasStarted()) {
            MaterialTapTargetPrompt.Builder(this)
                .setTarget(R.id.fab)
                .setPrimaryText("Create Todo")
                .setSecondaryText("Click here to create a new Todo object")
                .show()

            saveWasStarted()
        }
    }

    fun saveWasStarted() {
        var sharedPref = getSharedPreferences("NAME_PREF", Context.MODE_PRIVATE)
        var editor = sharedPref.edit()
        editor.putBoolean("KEY_STARTED", true)
        editor.apply()
    }

    fun getWasStarted() : Boolean {
        var sharedPref = getSharedPreferences("NAME_PREF", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("KEY_STARTED", false)
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

    var editIndex : Int = -1

    public fun showEditTodoDialog(todoToEdit: Todo, todoIndex: Int) {
        editIndex = todoIndex

        val editTodoDialog = TodoDialog()

        val bundle = Bundle()
        bundle.putSerializable(KEY_TODO_EDIT, todoToEdit)
        editTodoDialog.arguments = bundle

        editTodoDialog.show(supportFragmentManager, "EDITDIALOG")
    }


    override fun todoCreated(todo: Todo) {
        Thread{
            var newId = AppDatabase.getInstance(this@ScrollingActivity).
                todoDao().insertTodo(todo)
            todo.id = newId

            runOnUiThread {
                todoAdapter.addTodo(todo)
            }
        }.start()
    }

    override fun todoUpdated(todo: Todo) {
        Thread {
            AppDatabase.getInstance(this@ScrollingActivity).
                todoDao().updateTodo(todo)

            runOnUiThread {
                todoAdapter.updateTodo(todo, editIndex)
            }

        }.start()
    }
}
