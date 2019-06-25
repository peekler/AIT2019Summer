package hu.ait.roomgradedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.roomgradedemo.data.AppDatabase
import hu.ait.roomgradedemo.data.Grade
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnSave.setOnClickListener {
            var grade = Grade(null,
                etName.text.toString(),
                etGrade.text.toString(),
                Date(System.currentTimeMillis()).toString())

            Thread {
                AppDatabase.getInstance(this@MainActivity).gradeDao().saveGrade(grade)
            }.start()
        }

        btnQuery.setOnClickListener {
            Thread {
                //var gradesList =
                //    AppDatabase.getInstance(this@MainActivity).gradeDao().getAllGrades()

                var gradesList =
                    AppDatabase.getInstance(this@MainActivity).gradeDao().
                        getSpecificGrades("A+")


                runOnUiThread {
                    tvResult.text = ""
                    gradesList.forEach {
                        tvResult.append("${it.studentId}: ${it.grade} ${it.date}\n")
                    }
                }
            }.start()
        }
    }
}
