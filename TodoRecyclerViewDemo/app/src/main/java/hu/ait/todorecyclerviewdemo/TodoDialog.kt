package hu.ait.todorecyclerviewdemo

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import hu.ait.todorecyclerviewdemo.data.Todo
import kotlinx.android.synthetic.main.todo_dialog.view.*
import java.lang.RuntimeException
import java.util.*

class TodoDialog : DialogFragment() {

    interface TodoHandler {
        fun todoCreated(todo: Todo)

        fun todoUpdated(todo: Todo)
    }

    lateinit var todoHandler: TodoHandler

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is TodoHandler) {
            todoHandler = context
        } else {
            throw RuntimeException(
                "The Activity is not implementing the TodoHandler interface."
            )
        }
    }

    lateinit var etTodoText: EditText
    lateinit var cbTodoDone: CheckBox


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder.setTitle("Todo dialog")
        val dialogView = requireActivity().layoutInflater.inflate(
            R.layout.todo_dialog, null
        )

        etTodoText = dialogView.etTodoText
        cbTodoDone = dialogView.cbTodoDone

        dialogBuilder.setView(dialogView)

        // IF WE ARE IN EDIT MODE
        if (arguments != null) {
            if (arguments!!.containsKey(ScrollingActivity.KEY_TODO_EDIT)) {
                val todoItem = arguments!!.getSerializable(ScrollingActivity.KEY_TODO_EDIT) as Todo

                etTodoText.setText(todoItem.todoText)
                cbTodoDone.isChecked = todoItem.done

                dialogBuilder.setTitle("Edit todo")
            }
        }


        dialogBuilder.setPositiveButton("Ok") {
            dialog, which ->

            //
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
        }


        return dialogBuilder.create()
    }


    override fun onResume() {
        super.onResume()

        val positiveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            if (etTodoText.text.isNotEmpty()) {
                if (arguments != null && arguments!!.containsKey(ScrollingActivity.KEY_TODO_EDIT)) {
                    val todoToEdit = arguments!!.getSerializable(ScrollingActivity.KEY_TODO_EDIT) as Todo

                    todoToEdit.todoText = etTodoText.text.toString()
                    todoToEdit.done = cbTodoDone.isChecked

                    todoHandler.todoUpdated(todoToEdit)
                } else {
                    todoHandler.todoCreated(
                        Todo(
                            null,
                            Date(System.currentTimeMillis()).toString(),
                            cbTodoDone.isChecked,
                            etTodoText.text.toString()
                        )
                    )
                }

                dialog.dismiss()
            } else {
                etTodoText.error = "This field can not be empty"
            }
        }
    }


}