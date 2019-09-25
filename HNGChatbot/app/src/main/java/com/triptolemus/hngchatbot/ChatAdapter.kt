package com.triptolemus.hngchatbot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triptolemus.hngchatbot.model.Model

class ChatAdapter(private val chat: List<Model.ChatMessage>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chatbot_bubble_chat, parent, false))
    }

    override fun getItemCount(): Int {
        return chat.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chat[position])
    }

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val userChatText: TextView = view.findViewById(R.id.userBubbleBox)
        private val userTimeStampText: TextView = view.findViewById(R.id.userTimestamp)
        private val botChatText: TextView = view.findViewById(R.id.botBubbleBox)
        private val botTimeStampText: TextView = view.findViewById(R.id.botTimestamp)
        private val botLayout: LinearLayout = view.findViewById(R.id.botLayout)
        private val userLayout: LinearLayout = view.findViewById(R.id.userLayout)

        fun bind(chat: Model.ChatMessage){
            if (chat.msgUser != "bot"){
                botChatText.visibility = View.GONE
                botTimeStampText.visibility = View.GONE
                botLayout.visibility = View.GONE
                userChatText.text = chat.msgText
            } else{
                userChatText.visibility = View.GONE
                userTimeStampText.visibility = View.GONE
                userLayout.visibility = View.GONE
                botChatText.text = chat.msgText
            }
        }
    }
}