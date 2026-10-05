package com.sofiaverdugo.micalculadora

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.TextView
import android.view.View
import android.widget.Button
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var number: Double = 0.0
    var oper: Int = 0
    // 1 is sum, 2 is sub, 3 is multiply, 4 is div
    lateinit var TVnum1: TextView
    lateinit var TVnum2: TextView


    // 1) Aquí van las variables del video
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        TVnum1 = findViewById(R.id.num1)
        TVnum2 = findViewById(R.id.num2)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnEqual: Button = findViewById(R.id.btnEqual)
        btnEqual.setOnClickListener {
            if (oper == 0) {
                TVnum2.setText("Select an operation")
            } else if (TVnum2.text.toString() == "") {
                TVnum2.setText("Enter a number")
            } else {
                var number2: Double = TVnum2.text.toString().toDouble()
                var ans: Double = 0.0

                if (oper == 4 && number2 == 0.0) {
                    TVnum2.setText("Error")
                } else {
                    when (oper) {
                        1 -> ans = number + number2
                        2 -> ans = number - number2
                        3 -> ans = number * number2
                        4 -> ans = number / number2
                    }

                    TVnum2.setText(ans.toString())
                    TVnum1.setText("")
                    oper = 0
                }
            }
        }
        btnClear.setOnClickListener {
            TVnum1.setText("")
            TVnum2.setText("")
            number = 0.0
            oper = 0
        }
    }

    fun pressDigit(view: View) {
        var num2: String = TVnum2.text.toString()

        if (num2 == "Error" || num2 == "Enter a number" || num2 == "Select an operation") {
            num2 = ""
            TVnum2.setText("")
        }

        when (view.id) {
            R.id.btn0 -> TVnum2.setText(num2 + 0)
            R.id.btn1 -> TVnum2.setText(num2 + 1)
            R.id.btn2 -> TVnum2.setText(num2 + 2)
            R.id.btn3 -> TVnum2.setText(num2 + 3)
            R.id.btn4 -> TVnum2.setText(num2 + 4)
            R.id.btn5 -> TVnum2.setText(num2 + 5)
            R.id.btn6 -> TVnum2.setText(num2 + 6)
            R.id.btn7 -> TVnum2.setText(num2 + 7)
            R.id.btn8 -> TVnum2.setText(num2 + 8)
            R.id.btn9 -> TVnum2.setText(num2 + 9)
            R.id.btnDot -> {
                if (num2 != "" && !num2.contains(".")) {
                    TVnum2.setText(num2 + ".")
                }
            }
        }
    }

    fun clickOperation(view: View) {
        if (TVnum2.text.toString() == "" ||
            TVnum2.text.toString() == "Error" ||
            TVnum2.text.toString() == "Enter a number" ||
            TVnum2.text.toString() == "Select an operation") {

            TVnum2.setText("Enter a number")
            return
        }

        number = TVnum2.text.toString().toDouble()
        var num2_text: String = TVnum2.text.toString()
        TVnum2.setText("")

        when (view.id) {
            R.id.btnSum -> {
                TVnum1.setText(num2_text + "+")
                oper = 1
            }

            R.id.btnSub -> {
                TVnum1.setText(num2_text + "-")
                oper = 2
            }

            R.id.btnProd -> {
                TVnum1.setText(num2_text + "*")
                oper = 3
            }

            R.id.btnDiv -> {
                TVnum1.setText(num2_text + "/")
                oper = 4
            }
        }
    }
}