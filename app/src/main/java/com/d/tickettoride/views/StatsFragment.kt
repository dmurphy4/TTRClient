package com.d.tickettoride.views

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.d.tickettoride.R
import com.d.tickettoride.model.PlayerInfo
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ChatPresenter
import com.d.tickettoride.presenters.StatsPresenter
import com.d.tickettoride.presenters.ipresenters.IChatPresenter
import com.d.tickettoride.presenters.ipresenters.IStatsPresenter
import com.d.tickettoride.util.ChatAdapter
import com.d.tickettoride.util.StatsAdapter
import com.d.tickettoride.views.iviews.IStatsView
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_stats.*

class StatsFragment : Fragment(), IStatsView {
    private lateinit var adapter: StatsAdapter
    private val statsPresenter: IStatsPresenter = StatsPresenter(this)
    var listener: OnChatButtonPushedListener? = null
    var rootModel: RootModel = RootModel.instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_stats, container, false)
        // convert Map into array of PlayerInfo for the Adapter
        val playerInfo = ArrayList(rootModel.game!!.gamePlayers.values)

        adapter = StatsAdapter(playerInfo, this)
        player_chat_list.layoutManager = LinearLayoutManager(activity) // Displays games 1 per row
        player_chat_list.adapter = adapter
        player_chat_list.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))

        // Player pushes stats button to see player info
        button_chat.setOnClickListener {
            listener?.onChatButtonPushed(ChatFragment())
        }
        //TODO: how do I use the statsPresenter here?
        // Inflate the layout for this fragment
        return view
    }
    interface OnChatButtonPushedListener{
        fun onChatButtonPushed(frag: Fragment)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnChatButtonPushedListener
        if (listener == null) {
            throw ClassCastException("$context must implement OnChatButtonPushedListener")
        }

    }

}