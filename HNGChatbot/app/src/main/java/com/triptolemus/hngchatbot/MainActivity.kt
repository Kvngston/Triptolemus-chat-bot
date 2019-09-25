package com.triptolemus.hngchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToOnBoarding()
    }

    private fun navigateToOnBoarding(){
        val timer = object : CountDownTimer(3000, 500){
            override fun onFinish() {
                val intent = Intent(this@MainActivity, OnBoardingActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }

            override fun onTick(p0: Long) { }
        }
        timer.start()
    }
}