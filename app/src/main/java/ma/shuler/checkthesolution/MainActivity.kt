package ma.shuler.checkthesolution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.graphics.Color
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {
    lateinit var summary : TextView

    lateinit var correct : TextView
    lateinit var incorrect : TextView

    lateinit var percent : TextView

    lateinit var first : TextView
    lateinit var second : TextView
    lateinit var operation : TextView

    lateinit var answer : TextView

    lateinit var correctBtn : Button
    lateinit var incorrectBtn : Button
    lateinit var start : Button

    lateinit var minText : TextView
    lateinit var maxText : TextView
    lateinit var avgText : TextView

    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    var avgSummary = 0

    var correctAns = 0
    var incorrectAns = 0

    val operations = listOf("+", "-", "*", "/")

    var seconds = 0

    var firststart = true

    val totalSeconds = 1000
    lateinit var timer : CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        summary = findViewById(R.id.textView1)

        correct = findViewById(R.id.textView4)
        incorrect = findViewById(R.id.textView5)

        percent = findViewById(R.id.textView6)

        first = findViewById(R.id.textView7)
        second = findViewById(R.id.textView9)
        operation = findViewById(R.id.textView8)
        answer = findViewById(R.id.editTextText)

        minText = findViewById(R.id.textView30)
        maxText = findViewById(R.id.textView31)
        avgText = findViewById(R.id.textView32)

        correctBtn = findViewById<Button>(R.id.button1)
        correctBtn.setOnClickListener {
            correctBtnCallback()
        }

        incorrectBtn = findViewById<Button>(R.id.button2)
        incorrectBtn.setOnClickListener {
            incorrectBtnCallback()
        }

        start = findViewById<Button>(R.id.button)
        start.setOnClickListener {
            start()
        }

        correctBtn.isEnabled = false
        incorrectBtn.isEnabled = false

        timer = object : CountDownTimer(totalSeconds * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                seconds++
            }
            override fun onFinish() { }
        }.start()
    }

    fun correctBtnCallback() {
        updateTime()
        correctBtn.isEnabled = false
        incorrectBtn.isEnabled = false
        start.isEnabled = true

        val ans = getAns().toInt().toString()

        if (answer.text.toString() == ans) {
            correctAns++
            answer.setTextColor(Color.WHITE)
            answer.setBackgroundColor(Color.GREEN)

        }
        else {
            incorrectAns++
            answer.setTextColor(Color.WHITE)
            answer.setBackgroundColor(Color.RED)
        }

        correct.text = correctAns.toString()
        incorrect.text = incorrectAns.toString()
        summary.text = (correctAns + incorrectAns).toString()
        percent.text = String.format("%.2f", (correctAns.toDouble() / (correctAns + incorrectAns) * 100)) + "%"
    }

    fun incorrectBtnCallback() {
        updateTime()
        correctBtn.isEnabled = false
        incorrectBtn.isEnabled = false
        start.isEnabled = true

        val ans = getAns().toInt().toString()

        if (answer.text.toString() != ans) {
            correctAns++
            answer.setTextColor(Color.WHITE)
            answer.setBackgroundColor(Color.GREEN)
        }
        else {
            incorrectAns++
            answer.setTextColor(Color.WHITE)
            answer.setBackgroundColor(Color.RED)
        }

        correct.text = correctAns.toString()
        incorrect.text = incorrectAns.toString()
        summary.text = (correctAns + incorrectAns).toString()
        percent.text = String.format("%.2f", (correctAns.toDouble() / (correctAns + incorrectAns) * 100)) + "%"
    }

    fun start() {
        start.isEnabled = false
        correctBtn.isEnabled = true
        incorrectBtn.isEnabled = true
        answer.isEnabled = true
        answer.setBackgroundColor(Color.TRANSPARENT)
        answer.setTextColor(Color.BLACK)
        answer.text =""

        makeNewExpr()

        seconds = 0
    }

    fun updateTime() {
        if (seconds < min) {
            min = seconds
        }
        if (seconds > max) {
            max = seconds
        }
        avgSummary += seconds

        minText.text = min.toString()
        maxText.text = max.toString()
        avgText.text = String.format("%.2f", avgSummary.toDouble() / (correctAns + incorrectAns))
        firststart = false

    }

    fun makeNewExpr() {
        first.text = Random.nextInt(10, 100).toString()
        second.text = Random.nextInt(10, 100).toString()
        operation.text = operations[Random.nextInt(0, 4)]

        while (operation.text == "/" && first.text.toString().toDouble() % second.text.toString().toDouble() != 0.0) {
            first.text = Random.nextInt(10, 100).toString()
            second.text = Random.nextInt(10, 100).toString()
        }

        if (Random.nextInt(0, 2) == 1) {
            answer.text = getAns().toInt().toString()
        }
        else {
            answer.text = Random.nextInt(10, 100).toString()
        }
    }

    fun getAns() : Double {
        when (operation.text){
            "+" -> {
                return first.text.toString().toDouble() + second.text.toString().toDouble()
            }
            "-" -> {
                return first.text.toString().toDouble() - second.text.toString().toDouble()
            }
            "*" -> {
                return first.text.toString().toDouble() * second.text.toString().toDouble()
            }
            "/" -> {
                return first.text.toString().toDouble() / second.text.toString().toDouble()
            }
        }
        return 0.0
    }
}
/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
*/