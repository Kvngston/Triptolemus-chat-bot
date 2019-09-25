package com.triptolemus.hngchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        backButton.setOnClickListener(this)
        /*val username = intent.getStringExtra("username")
        welcomeTextview.text = username*/
    }
    override fun onClick(item: View?) {
        if (item!!.id == R.id.backButton){
            onBackPressed()
        }
    }

    private fun back(view: View){
        val intent = Intent(this, OnBoardingActivity::class.java)
        startActivity(intent)
    }
}