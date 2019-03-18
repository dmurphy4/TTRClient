package com.d.tickettoride.util

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.d.tickettoride.R
import com.d.tickettoride.model.gameplay.Event
import com.d.tickettoride.views.iviews.IChatView
import kotlinx.android.synthetic.main.row_chat_list.view.*

class ChatAdapter(private val messageList: ArrayList<Event>,
                  private val ChatFragment: IChatView,
                  var selectedRowIndex: Int = -1) :
    RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = parent.inflate(R.layout.row_chat_list, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val event = messageList[position]
        holder.bind(event)
        holder.view.setOnClickListener {
            selectedRowIndex = position
            notifyDataSetChanged()
        }

        if (selectedRowIndex == position) {
            holder.view.row_chat_layout.setBackgroundColor(Color.parseColor("#BEEB9F"))
            holder.view.message.setTextColor(Color.parseColor("#000000"))
        } else {
            holder.view.row_chat_layout.setBackgroundColor(Color.parseColor("#79BD8F"))
            holder.view.message.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(event: Event) {
            view.message.text = event.content
        }
    }

}