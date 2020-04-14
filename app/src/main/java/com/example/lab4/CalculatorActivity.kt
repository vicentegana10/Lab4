package com.example.lab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.lab4.HistoryActivity.Companion.OPERATIONS
import kotlinx.android.synthetic.main.activity_calculator.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class CalculatorActivity : AppCompatActivity() {

    private var operations: MutableList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun showResult(view: View) {
        val numbers = operationTextView.text.toString().split(Regex("[+\\-/*]"))
        if (numbers.size==2  ){
            if(numbers[0].isNotEmpty()){
                if(numbers[1].isNotEmpty()){
                    operations.add(operationTextView.text.toString())
                    val operation = operationTextView.text.toString()
                    val result = evalLeftToRight(operation).toString()
                    operationTextView.text = result
                }
            }
        }
    }

    fun pressCalculatorButton(view: View) {
        val button: Button = findViewById(view.id)
        operationTextView.text = operationTextView.text.toString() + button.text.toString()
    }

    fun eraseAll(view: View) {
        operationTextView.text = ""
    }

    fun pressEraseLastCharButton(view: View) {
        operationTextView.text = operationTextView.text.toString().substring(0,operationTextView.length() - 1)
    }

    fun onHistoryButtonPressed(view: View) {
        val intent = Intent(view.context, HistoryActivity::class.java)
        intent.putStringArrayListExtra(OPERATIONS, ArrayList(operations))
        view.context.startActivity(intent)
    }
    fun evalLeftToRight(text: String): Double {
        var result = 0.0
        var actualOperant = ' '
        val pattern: Pattern = Pattern.compile("((\\d*\\.\\d+)|(\\d+)|([\\+\\-\\*/]))")
        val m: Matcher = pattern.matcher(text)
        while (m.find()) {
            val part: String = m.group()
            if (part == "+") actualOperant = '+' else if (part == "-") actualOperant =
                '-' else if (part == "*") actualOperant =
                '*' else if (part == "/") actualOperant = '/' else {
                val actualNumber = part.toDouble()
                when (actualOperant) {
                    ' ' -> result = actualNumber
                    '+' -> result += actualNumber
                    '-' -> result -= actualNumber
                    '*' -> result *= actualNumber
                    '/' -> result /= actualNumber
                }
            }
        }
        return result
    }
}

