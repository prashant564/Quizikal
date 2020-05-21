package com.example.quizzio.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent : Intent;
        Handler().postDelayed({
            if(FirebaseAuth.getInstance().currentUser==null){
                intent = Intent(this,SignInActivity::class.java)
            }else{
                intent = Intent(this,
                    MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        },100)
    }
}
