package com.triptolemus.hngchatbot.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.triptolemus.hngchatbot.R
import kotlinx.android.synthetic.main.activity_chat.*
import ai.api.android.AIConfiguration.RecognitionEngine
import ai.api.AIConfiguration.SupportedLanguages
import ai.api.AIServiceException
import ai.api.android.AIConfiguration
import ai.api.android.AIDataService
import ai.api.android.AIService
import ai.api.model.AIRequest
import ai.api.model.AIResponse
import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triptolemus.hngchatbot.ChatAdapter
import com.triptolemus.hngchatbot.model.Model


class ChatActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var service: AIService
    lateinit var aiService: AIDataService
    lateinit var aiRequest: AIRequest

    private var chats = ArrayList<Model.ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        backButton.setOnClickListener(this)
        chatContainer.adapter = ChatAdapter(chats)
        chatContainer.layoutManager = LinearLayoutManager(this)
        sendButton.setOnClickListener(this)
        initAI()

        /*val username = intent.getStringExtra("username")
        welcomeTextview.text = username*/
    }

    private fun initAI(){
        val config = AIConfiguration("eb9c5a84584e426aa58b6fd00e410961",
            SupportedLanguages.English,
            RecognitionEngine.System)
        service = AIService.getService(this, config)
        aiService = AIDataService(this, config)
        aiRequest = AIRequest()
    }

    override fun onClick(item: View?) {
        if (item!!.id == R.id.backButton){
            onBackPressed()
        } else if (item.id == R.id.sendButton){
            val message = messageEdittext.text.toString().trim()
            performInteraction(message)
            messageEdittext.setText("")
        }
    }

    private fun performInteraction(message: String) {
        if (message.isNotEmpty()){
            val chat = Model.ChatMessage("user", message)
            chats.add(chat)
            chatContainer.adapter!!.notifyDataSetChanged()
            aiRequest.setQuery(message)
            val performAIRequest = PerformAIRequest(chatContainer, aiService, chats)
            performAIRequest.execute(aiRequest)
        }
    }

    class PerformAIRequest(private val view: RecyclerView,
                           private val aiService: AIDataService,
                           private val chats: ArrayList<Model.ChatMessage>) : AsyncTask<AIRequest, Void, AIResponse>() {

        override fun doInBackground(vararg request: AIRequest?): AIResponse? {
            val aiRequest = request[0]
            try {
                return aiService.request(aiRequest)
            } catch (err: AIServiceException){
                Log.e("AIRequest", "Failed to perform request", err)
            }
            return null
        }

        override fun onPostExecute(result: AIResponse?) {
            if (result != null){
                val aiResult = result.result
                val reply = aiResult.fulfillment.speech
                val chat = Model.ChatMessage("bot", reply)
                view.adapter!!.notifyDataSetChanged()
                chats.add(chat)
            }
        }
    }
}