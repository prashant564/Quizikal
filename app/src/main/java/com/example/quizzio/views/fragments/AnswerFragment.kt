package com.example.quizzio.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.quizzio.R
import com.example.quizzio.database.TriviaDatabase
import com.example.quizzio.databinding.FragmentAnswerBinding
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.utils.AppConstants
import com.example.quizzio.views.ui.TriviaUI
import com.example.quizzio.views.viewmodelFactory.TriviaViewModelFactory
import com.example.quizzio.views.viewmodels.HomeViewModel

class AnswerFragment : Fragment() {
    lateinit var binding: FragmentAnswerBinding
    private var colorId:Int=R.color.entertainment
    var trivia: TriviaUI? = null
    var category:String?=null
    val viewmodel by lazy {
        val repository = TriviaRepository(TriviaDatabase.invoke(activity!!.applicationContext))
        var factory = TriviaViewModelFactory(repository,"Entertainment")
        ViewModelProviders.of(this,factory).get(HomeViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_answer, container, false)
        binding.lifecycleOwner=this
        trivia= arguments!!.getParcelable(AppConstants.quizData)
        colorId=arguments!!.getInt(AppConstants.colorId)
        binding.apply {
            val hint = trivia!!.answer.toCharArray()
            tvHint.text=createHint(hint)
            tvQues.text=trivia!!.question
            tvAnswer.text=trivia!!.answer

            btnShowHint.setOnClickListener {
                tvHint.visibility= View.VISIBLE
            }
            btnShowAnswer.setOnClickListener {
                tvAnswer.visibility = View.VISIBLE
            }
            btnSubmit.setOnClickListener {
                if(checkAnswer(editText.text.toString(),trivia!!.answer)){
                    Toast.makeText(context,"Yay!! Thats the correct answer. Well Done!!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Sorry, but that's not correct. Better luck next time", Toast.LENGTH_SHORT).show()
                }
            }
            floatingActionButton.setOnClickListener {
                viewmodel.insertTrivia(trivia!!)
                Toast.makeText(context,"Added to Favourites!!",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
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
