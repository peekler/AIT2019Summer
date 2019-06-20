package hu.ait.todorecyclerviewdemo.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.ait.todorecyclerviewdemo.R
import hu.ait.todorecyclerviewdemo.data.Todo
import kotlinx.android.synthetic.main.todo_row.view.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    var todoItems = mutableListOf<Todo>(
        Todo("2019. 06. 20", false, "Todo1"),
        Todo("2019. 06. 21", false, "Todo2"),
        Todo("2019. 06. 22", false, "Todo3")
    )

    val context: Context
    constructor(context: Context) : super() {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val todoView = LayoutInflater.from(context).inflate(
            R.layout.todo_row, parent, false
        )
        return ViewHolder(todoView)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var todo = todoItems.get(holder.adapterPosition)

        holder.cbDone.text = todo.todoText
        holder.cbDone.isChecked = todo.done
        holder.tvDate.text = todo.createDate

        holder.btnDelete.setOnClickListener {
            removeTodo(holder.adapterPosition)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbDone = itemView.cbDone
        val tvDate = itemView.tvDate
        val btnDelete = itemView.btnDelete
    }

    fun addTodo(todo: Todo){
        todoItems.add(todo)

        //notifyDataSetChanged()
        notifyItemInserted(todoItems.lastIndex)
    }

    fun removeTodo(index: Int) {
        todoItems.removeAt(index)
        notifyItemRemoved(index)
    }
}