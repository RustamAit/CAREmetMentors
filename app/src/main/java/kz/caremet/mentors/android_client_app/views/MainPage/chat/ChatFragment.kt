package kz.caremet.mentors.android_client_app.views.MainPage.chat

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_chat.*

import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.core.generateUUID
import kz.caremet.mentors.android_client_app.core.getCurrentDateISOString
import kz.caremet.mentors.android_client_app.repository.dao.ChatRoomDao
import kz.caremet.mentors.android_client_app.repository.dao.MessageDao
import kz.caremet.mentors.android_client_app.repository.interfaces.ChatRepositoty
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import kz.caremet.mentors.android_client_app.views.MainPage.MainPageFragmentListener
import kz.caremet.mentors.android_client_app.views.adapters.ChatAdapter
import kz.caremet.mentors.android_client_app.views.chat.ChatActivity
import org.koin.android.ext.android.inject


class ChatFragment : Fragment(), ItemChatRoomListener {

    val chatRoomDao: ChatRoomDao by inject()
    val sharedPref: LocalSharedPref by inject()
    val messageDao: MessageDao by inject()
    val chatRepositoty: ChatRepositoty by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        chatRecList.layoutManager = LinearLayoutManager(activity)
        val dataset = ArrayList<DataEntities.ChatRoomFromDb>()



        chatRoomDao.getUserChatRooms(sharedPref.getCurrentMentorId()!!).observeOn(AndroidSchedulers.mainThread()).subscribe { data ->
            context?.let {
                val adapter = ChatAdapter(data,it,this)
                chatRecList.adapter = adapter
            }
        }

        swipeRefresh.setOnRefreshListener {
            chatRepositoty.getChatRooms().observeOn(AndroidSchedulers.mainThread()).onErrorReturn {
                emptyList()
            }.subscribe{ it ->
                swipeRefresh.isRefreshing = false
            }
        }

        chatRepositoty.getChatRooms().onErrorReturn {
            emptyList()
        }.subscribe()

    }

    override fun startChatActivity(chatRoomId: Int) {
        (activity as? MainPageFragmentListener)?.startChatActivity(chatRoomId)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChatFragment()
    }
}
