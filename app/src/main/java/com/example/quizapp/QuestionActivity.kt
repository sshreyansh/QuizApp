package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    private var name: String ?= null
    private var score: Int = 0

    private var currentPosition:Int = 1
    private var questionList: ArrayList<QuestionData> ?= null
    private var selectedOption:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        questionList = SetData.getQuestion()

        name = intent.getStringExtra(SetData.name)


        setQuestion()

        opt_1.setOnClickListener {
            selectedOptionStyle(opt_1, 1);
        }
        opt_2.setOnClickListener {
            selectedOptionStyle(opt_2, 2);
        }
        opt_3.setOnClickListener {
            selectedOptionStyle(opt_3, 3);
        }
        opt_4.setOnClickListener {
            selectedOptionStyle(opt_4, 4);
        }
        submit.setOnClickListener {
            if(selectedOption != 0) {
                // if user has selected a value
                val question = questionList!![currentPosition - 1]
                if(selectedOption != question.correct_ans) {
                    setColor(selectedOption, R.drawable.wrong_question_option)
                }else {
                    score++;
                }
                // else mei isliye nahi likha qki if wrong to we have to show correct as well as wrong
                setColor(question.correct_ans, R.drawable.correct_question_option)
                if(currentPosition == questionList!!.size) {
                    submit.text = "FINISH"
                }else {
                    submit.text = "Go To Next"
                }
            }else {
                // even if user dosent give answer and press submit we go to next Q
                currentPosition++;
                if(currentPosition <= questionList!!.size) {
                    setQuestion()
                }else {
                    var intent = Intent(this, Result::class.java)
                    intent.putExtra(SetData.score, score.toString())
                    intent.putExtra(SetData.name, name.toString())
                    startActivity(intent)
                    finish()
                }
            }
            selectedOption = 0
            // at the end of each Q we again make selected optipn = 0
        }
    }

    private fun setColor(opt: Int, color: Int) {
        when(opt) {
            1-> {
                opt_1.background = ContextCompat.getDrawable(this, color)
            }
            2-> {
                opt_2.background = ContextCompat.getDrawable(this, color)
            }
            3-> {
                opt_3.background = ContextCompat.getDrawable(this, color)
            }
            4-> {
                opt_4.background = ContextCompat.getDrawable(this, color)
            }
        }
    }

    private fun setQuestion() {

        setOptionStyle()
        val question = questionList!![currentPosition - 1]
        progress_bar.progress = currentPosition
        progress_bar.max = questionList!!.size
        progress_text.text = "${currentPosition}" + "/" + "${questionList!!.size}"
        question_text.text = question.question
        opt_1.text = question.option_one
        opt_2.text = question.option_two
        opt_3.text = question.option_three
        opt_4.text = question.option_four
    }

    private fun setOptionStyle() {
        var optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(0, opt_1)
        optionList.add(1, opt_2)
        optionList.add(2, opt_3)
        optionList.add(3, opt_4)

        for(op in optionList) {
            op.setTextColor(Color.parseColor("#555151"))
            op.background = ContextCompat.getDrawable(this, R.drawable.question_option)
            op.typeface = Typeface.DEFAULT
        }
    }

    // 2 parameter 1st is the text ie the box that user selects
    // option no to check its correctness
    fun selectedOptionStyle(view: TextView, opt: Int) {
        // option select krne ke baad default krdiya
        setOptionStyle() // agar ye call nahi karenge to user can select more 1 option and sab selected show honge
        selectedOption = opt
            // if we select a option to grey color ka ho jaae option
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_question_option)
        view.typeface = Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))
    }

}
