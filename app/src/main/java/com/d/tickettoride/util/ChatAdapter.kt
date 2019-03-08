package com.d.tickettoride.util

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.d.tickettoride.R
import com.d.tickettoride.model.gameplay.Event
import com.d.tickettoride.views.iviews.IChatView
import kotlinx.android.synthetic.main.row_chat_list.view.*

class ChatAdapter(private val messageList: ArrayList<Event>,
                  private val ChatFragment: IChatView) :
    RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = parent.inflate(R.layout.row_chat_list, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val event = messageList[position]
        holder.bind(event)
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(event: Event) {
            view.message.text = event.content
        }
    }

}