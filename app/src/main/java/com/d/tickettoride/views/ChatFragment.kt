package com.d.tickettoride.views

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d.tickettoride.R
import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ChatPresenter
import com.d.tickettoride.presenters.ChooseGamePresenter
import com.d.tickettoride.presenters.ipresenters.IChatPresenter
import com.d.tickettoride.presenters.ipresenters.IChooseGamePresenter
import com.d.tickettoride.util.ChatAdapter
import com.d.tickettoride.util.GameInfoAdapter
import com.d.tickettoride.util.afterTextChanged
import com.d.tickettoride.views.iviews.IChatView
import com.d.tickettoride.views.iviews.IGameView
import kotlinx.android.synthetic.main.activity_choose_game.*
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment(), IChatView {
    private lateinit var adapter: ChatAdapter
    private val chatPresenter: IChatPresenter = ChatPresenter(this)
    var listener: OnStatsButtonPushedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_chat, container, false)

        adapter = ChatAdapter(ArrayList(RootModel.instance.game!!.eventHistory), this)
        player_chat_list.layoutManager = LinearLayoutManager(activity) // Displays games 1 per row
        player_chat_list.adapter = adapter
        player_chat_list.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))

        button_send_message.isEnabled = false

        chat_box.afterTextChanged { chatBox ->
            button_send_message.isEnabled = chatBox.isNotEmpty()
        }
        // send message
        button_send_message.setOnClickListener{
            chatPresenter.sendMessage(chat_box.text.toString())
        }
        // Player pushes stats button to see player info
        button_stats.setOnClickListener {
            listener?.onStatsButtonPushed(StatsFragment())
        }

        //TODO: Do I need to start Poller?

        return view
    }
    interface OnStatsButtonPushedListener{
        fun onStatsButtonPushed(frag: Fragment)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ChatFragment.OnStatsButtonPushedListener
        if (listener == null) {
            throw ClassCastException("$context must implement OnStatsButtonPushedListener")
        }

    }
}