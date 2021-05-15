package com.example.chatbot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.R
import com.example.chatbot.data.Message
import com.example.chatbot.utils.Constants.RECEIVE_ID
import com.example.chatbot.utils.Constants.SEND_ID
import com.google.android.material.transition.Hold
import kotlinx.android.synthetic.main.message_item.view.*

class MessagingAdapter:RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {
    val messageList= mutableListOf<Message>()

    inner class MessageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener() {
                messageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return  MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item,parent,false))
    }

    override fun getItemCount(): Int {
        return messageList.size


    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage=messageList[position]

        when(currentMessage.id){
            SEND_ID->{
                holder.itemView.tv_message.apply {
                    text=currentMessage.message
                    visibility=View.VISIBLE
                }
                holder.itemView.tv_bot_message.visibility=View.GONE
            }
            RECEIVE_ID->{
                holder.itemView.tv_bot_message.apply {
                    text=currentMessage.message
                    visibility=View.VISIBLE
                }
                holder.itemView.tv_message.visibility=View.GONE


            }
        }

    }
    fun insertMessage(message: Message){
        this.messageList.add(message)
        notifyItemInserted(messageList.size)
        notifyDataSetChanged()
    }
}