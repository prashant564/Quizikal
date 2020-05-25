package com.example.quizzio.views.activities

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val createHint = trivia!!.answer
        val hint = createHint.toCharArray()
        var hintString:String=""
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
        tv_hint.text=hintString
        tv_ques.text=trivia!!.question
        tv_answer.text=trivia!!.answer
    }

    private fun initParcelableItems() {
        val extras = intent.extras
        trivia= extras?.getParcelable(AppConstants.quizData)
        colorId=extras?.getInt(AppConstants.colorId)
        supportActionBar?.title=trivia!!.category
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ResourceUtils.toColor(colorId!!)))
//        answer_main.setBackgroundColor(ResourceUtils.toColor(colorId!!))
    }
}
