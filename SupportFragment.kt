package com.prashD.quizzio.views.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.prashD.quizzio.R
import com.prashD.quizzio.database.TriviaDatabase
import com.prashD.quizzio.databinding.FragmentAnswerBinding
import com.prashD.quizzio.repository.TriviaRepository
import com.prashD.quizzio.utils.AppConstants
import com.prashD.quizzio.utils.AppUtils.createHint
import com.prashD.quizzio.utils.FirebaseUtils
import com.prashD.quizzio.views.ui.TriviaUI
import com.prashD.quizzio.views.viewmodelFactory.TriviaViewModelFactory
import com.prashD.quizzio.views.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_answer.*

class SupportFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_support, container, false)
        return view;
    }

    private fun showAlertDialog(message: String) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context!!)
        alertDialog.setTitle("App Info")
        alertDialog.setMessage("Do you wish to report a bug from this app?")
        alertDialog.setPositiveButton(
            "OK"
        ) { _, _ ->
//            Toast.makeText(context, "Alert dialog closed.", Toast.LENGTH_LONG).show()
        }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }

    private fun checkAnswer(userInput: String, answer: String): Boolean {
        return userInput.toLowerCase() == answer.toLowerCase()
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }
}