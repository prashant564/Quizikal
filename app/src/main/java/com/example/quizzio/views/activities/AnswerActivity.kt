package com.example.quizzio.views.activities

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.quizzio.R
import com.example.quizzio.utils.AppConstants
import com.example.quizzio.utils.ResourceUtils
import com.example.quizzio.views.ui.TriviaUI
import kotlinx.android.synthetic.main.activity_answer.*
import java.util.*

class AnswerActivity : AppCompatActivity() {

    var trivia: TriviaUI? = null
    var colorId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        initParcelableItems()
        btn_submit.setBackgroundColor(ResourceUtils.toColor(colorId!!))
        btn_show_hint.setBackgroundColor(ResourceUtils.toColor(colorId!!))
        btn_show_answer.setBackgroundColor(ResourceUtils.toColor(colorId!!))

        val hint = trivia!!.answer.toCharArray()
        tv_hint.text=createHint(hint)
        tv_ques.text=trivia!!.question
        tv_answer.text=trivia!!.answer

        btn_show_hint.setOnClickListener {
            tv_hint.visibility= View.VISIBLE
        }
        btn_show_answer.setOnClickListener {
            tv_answer.visibility = View.VISIBLE
        }
        btn_submit.setOnClickListener {
            if(checkAnswer(editText.text.toString(),trivia!!.answer)){
                Toast.makeText(baseContext,"Yay!! Thats the correct answer. Well Done!!",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(baseContext,"Sorry, but that's not correct. Better luck next time",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
    }

    private fun initParcelableItems() {
        val extras = intent.extras
        trivia= extras?.getParcelable(AppConstants.quizData)
        colorId=extras?.getInt(AppConstants.colorId)
        supportActionBar?.title=trivia!!.category
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ResourceUtils.toColor(colorId!!)))
//        answer_main.setBackgroundColor(ResourceUtils.toColor(colorId!!))
    }

    private fun createHint(hint: CharArray): String{
        var hintString=""
        for( i in hint.indices step 2) {
            if(hint[i]==' '){
                hintString+=' '
            }else{
                hintString+=hint[i]
            }
            if(i+1<hint.size){
                if(hint[i+1]!=' ') {
                    hintString+='_'
                }else{
                    hintString+=' '
                }
            }
        }
        return hintString
    }

    private fun checkAnswer(userInput:String,answer:String):Boolean{
        return userInput.toLowerCase() == answer.toLowerCase()
    }
}
