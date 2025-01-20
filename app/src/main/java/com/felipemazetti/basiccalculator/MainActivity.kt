package com.felipemazetti.basiccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var tvInput: TextView
    private lateinit var tvCurrentOperand: TextView
    private lateinit var tvOldInput: TextView

    private var oldValue: String = ""
    private var newValue: String = ""
    private var operator: String = ""
    private var isNewInput: Boolean = true




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvOldInput = findViewById(R.id.tv_old_input)
        tvCurrentOperand = findViewById(R.id.tv_current_operand)
        tvInput = findViewById(R.id.tv_input)


        val btnAllClear = findViewById<Button>(R.id.btn_all_clear)
        val btnClear = findViewById<Button>(R.id.btn_clear)
        val btnPercentage = findViewById<Button>(R.id.btn_percentage)
        val btnDivide = findViewById<Button>(R.id.btn_divide)
        val btnMultiply = findViewById<Button>(R.id.btn_multiply)
        val btnMinus = findViewById<Button>(R.id.btn_minus)
        val btnPlus = findViewById<Button>(R.id.btn_plus)
        val btnEqual = findViewById<Button>(R.id.btn_equal)

        val btnZero = findViewById<Button>(R.id.btn_zero)
        val btnOne = findViewById<Button>(R.id.btn_one)
        val btnTwo = findViewById<Button>(R.id.btn_two)
        val btnThree = findViewById<Button>(R.id.btn_three)
        val btnFour = findViewById<Button>(R.id.btn_four)
        val btnFive = findViewById<Button>(R.id.btn_five)
        val btnSix = findViewById<Button>(R.id.btn_six)
        val btnSeven = findViewById<Button>(R.id.btn_seven)
        val btnEight = findViewById<Button>(R.id.btn_eight)
        val btnNine = findViewById<Button>(R.id.btn_nine)
        val btnDot = findViewById<Button>(R.id.btn_dot)


        val btnList = listOf( btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine)

        btnList.forEach{ button ->
            button.setOnClickListener {
                if (isNewInput) {
                    tvInput.text = ""
                    isNewInput = false

                }
                tvInput.append((it as Button).text)
            }
        }

        btnDot.setOnClickListener {
            if (isNewInput) {
                tvInput.text = "0."
                isNewInput = false
            } else if (!tvInput.text.contains(".")) {
                tvInput.append(".")

            }
        }

        btnMultiply.setOnClickListener { setOperator("x")}
        btnMinus.setOnClickListener { setOperator("-")}
        btnPlus.setOnClickListener { setOperator("+")}
        btnDivide.setOnClickListener { setOperator("÷")}
        btnPercentage.setOnClickListener { setOperator("%") }


        btnAllClear.setOnClickListener {
            tvOldInput.text = ""
            tvCurrentOperand.text = ""
            tvInput.text = ""
            oldValue = ""
            newValue = ""
            operator = ""

        }
        btnClear.setOnClickListener {
            tvInput.text = ""

        }

        btnEqual.setOnClickListener{
            newValue = tvInput.text.toString()
            if (newValue.isEmpty()){
                tvInput.text = "0"
                return@setOnClickListener
            }
            val result = calculate()
            tvInput.text = result.toString()
            tvOldInput.text = ""
            isNewInput = true


        }



    }
    private fun calculate(): Double {
        return when (operator) {
            "+" -> oldValue.toDouble() + newValue.toDouble()
            "-" -> oldValue.toDouble() - newValue.toDouble()
            "x" -> oldValue.toDouble() * newValue.toDouble()
            "÷" -> oldValue.toDouble() / newValue.toDouble()
            "%" -> oldValue.toDouble() * (newValue.toDouble() / 100)

            else -> 0.0
        }
    }

    private fun setOperator(op: String){
        oldValue = tvInput.text.toString()
        operator = op
        tvOldInput.text = "$oldValue $operator"
        isNewInput = true

    }

}