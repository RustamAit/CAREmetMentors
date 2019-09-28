package kz.caremet.mentors.android_client_app.views.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import kz.caremet.mentors.android_client_app.R

import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.content_chat.*
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.core.generateUUID
import kz.caremet.mentors.android_client_app.core.getCurrentDateISOString
import kz.caremet.mentors.android_client_app.repository.dao.MessageDao
import kz.caremet.mentors.android_client_app.repository.interfaces.ChatRepositoty
import kz.caremet.mentors.android_client_app.repository.sharedPreferences.LocalSharedPref
import kz.caremet.mentors.android_client_app.views.adapters.ChatMessageAdapter
import org.koin.android.ext.android.inject

class ChatActivity : AppCompatActivity() {

    var chatRoomId: Int = -1
    val messageDao: MessageDao by inject()
    val sharedPref: LocalSharedPref by inject()
    val chatRepositoty: ChatRepositoty by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(toolbar)

        chatRoomId = intent.getIntExtra("chatRoomId", -1)
        chatRecList.layoutManager = LinearLayoutManager(this)
        if(chatRoomId != -1){
            chatRepositoty.getChatRoomMessagesInDb(chatRoomId).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val adapter = ChatMessageAdapter(it)
                    chatRecList.adapter = adapter
                }

            chatRepositoty.getChatRooms().subscribe()
        }

        inputView.editText?.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
                if(p0.isNullOrEmpty()){
                    inputView?.sendBtn?.visibility = ImageButton.GONE
                    inputView?.voiceRecordBtn?.visibility = ImageButton.VISIBLE
                }
                else{
                    inputView?.sendBtn?.visibility = ImageButton.VISIBLE
                    inputView?.voiceRecordBtn?.visibility = ImageButton.GONE
                }
            }
        })

        inputView.sendBtn?.setOnClickListener {
            if (inputView.getInputText().trim().isNotEmpty()){
                inputView.sendBtn?.isEnabled = false
                postAnswer(inputView.getInputText(),chatRoomId)
                inputView.editText?.setText("")
                inputView.sendBtn?.isEnabled = true
                chatRecList?.adapter?.let {
                    chatRecList?.scrollToPosition(it.itemCount+1)
                }
            }
        }

        inputView.voiceRecordBtn?.setOnClickListener {
            Toast.makeText(this,"Все еще делаем", Toast.LENGTH_LONG).show()
        }
    }

    fun postAnswer(text: String, chatRoomId: Int){
        messageDao.upsertDeal(
            DataEntities.MessageFormDb(
                generateUUID(),
                null,
                chatRoomId,
                sharedPref.getCurrentMentorId()!!,
                text,
                sharedPref.getCurrentMentorName(),
                "Mentor",
                getCurrentDateISOString(),
                DataEntities.StatusConstants.CREATED

            )
        )
    }



}
