package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editName = findViewById<EditText>(R.id.editName)
        val editPoints = findViewById<EditText>(R.id.editPoints)
        val button = findViewById<Button>(R.id.buttonCalculate)
        val displayPoints = findViewById<TextView>(R.id.displayPoints)
        val displayGrade = findViewById<TextView>(R.id.displatyGrade)

        button.setOnClickListener {
            val name = editName.text.toString().trim()
            val points = editPoints.text.toString().trim()
            val pointsAmount = points.toIntOrNull()

            if(name.isBlank()){
                displayPoints.text = "Wpisz imię i nazwisko."
                displayGrade.text = ""
            }
            else if(pointsAmount == null){
                displayPoints.text = "Wpisz prawidłową liczbę punktów."
                displayGrade.text = ""
            }
            else if(pointsAmount !in 0..100){
                displayPoints.text = "Punkty muszą być w zakresie od 0 do 100."
                displayGrade.text = ""
            }
            else{
                val grade = GradeCalculator().calculateGrade(pointsAmount)
                var student = Student(name, pointsAmount, grade)


                displayGrade.setTextColor(
                    when (student.grade){
                        "2.0" -> android.graphics.Color.RED
                        "3.0", "3.5", "4.0", "4.5", "5.0" ->
                            android.graphics.Color.rgb(0, 150, 0)
                        else -> android.graphics.Color.DKGRAY
                    }
                )


                displayPoints.text = "Student ${student.name} otrzymał ${student.points} punktów."
                displayGrade.text = "Ocena końcowa: ${student.grade}."
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

