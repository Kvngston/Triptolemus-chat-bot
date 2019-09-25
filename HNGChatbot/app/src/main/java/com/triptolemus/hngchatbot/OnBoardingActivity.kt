package com.triptolemus.hngchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnBoardingActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        startButton.setOnClickListener(this)
    }

    private fun navigateToChatActivity(username: String) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("username", username)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        startActivity(intent)
    }

    override fun onClick(item: View?) {
        val username: String = usernameEdittext.text.toString().trim()
        if (item!!.id == R.id.startButton){
            navigateToChatActivity(username)
        }
    }
}
