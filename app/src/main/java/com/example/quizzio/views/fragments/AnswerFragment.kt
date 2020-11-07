package com.example.quizzio.views.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.quizzio.R
import com.example.quizzio.database.TriviaDatabase
import com.example.quizzio.databinding.FragmentAnswerBinding
import com.example.quizzio.repository.TriviaRepository
import com.example.quizzio.utils.AppConstants
import com.example.quizzio.utils.AppUtils.createHint
import com.example.quizzio.utils.FirebaseUtils
import com.example.quizzio.views.activities.DetailActivity
import com.example.quizzio.views.ui.TriviaUI
import com.example.quizzio.views.viewmodelFactory.TriviaViewModelFactory
import com.example.quizzio.views.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_answer.*

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
                hideKeyboard()
                tvHint.visibility= View.VISIBLE
                FirebaseUtils.sendClickEvent(FirebaseUtils.ACTION.HINT_BTN)
            }
            btnShowAnswer.setOnClickListener {
                hideKeyboard()
                tvAnswer.visibility = View.VISIBLE
                FirebaseUtils.sendClickEvent(FirebaseUtils.ACTION.REVEAL_ANS_BTN)
            }
            btnSubmit.setOnClickListener {
                hideKeyboard()
                if(checkAnswer(editText.text.toString(),trivia!!.answer)){
                    showAlertDialog(getString(R.string.winner_msg))
                }else{
                    showAlertDialog(getString(R.string.loser_msg))
                }
                FirebaseUtils.sendClickEvent(FirebaseUtils.ACTION.SUBMIT_BTN)
            }
            floatingActionButton.setOnClickListener {
                viewmodel.insertTrivia(trivia!!)
                Toast.makeText(context,"Added to Favourites!!",Toast.LENGTH_SHORT).show()
                FirebaseUtils.sendClickEvent(FirebaseUtils.ACTION.FAB_ADD_TO_FAVOURITES)
            }
        }
        return binding.root
    }

    private fun showAlertDialog(message:String) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context!!)
        alertDialog.setTitle("App Info")
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton(
            "OK"
        ) { _, _ ->
            Toast.makeText(context, "Alert dialog closed.", Toast.LENGTH_LONG).show()
        }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }

    private fun checkAnswer(userInput:String,answer:String):Boolean{
        return userInput.toLowerCase() == answer.toLowerCase()
    }

    private fun hideKeyboard(){
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }
}
