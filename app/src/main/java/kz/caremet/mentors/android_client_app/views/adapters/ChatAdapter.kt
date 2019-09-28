package kz.caremet.mentors.android_client_app.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_chat_room.view.*
import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.GlideApp
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.views.MainPage.chat.ItemChatRoomListener

class ChatAdapter(val dataset: List<DataEntities.ChatRoomFromDb>,
                  val context: Context,
                  val listener: ItemChatRoomListener): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat_room,parent,false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    inner class ChatViewHolder(v: View): RecyclerView.ViewHolder(v){
        fun bind(p: DataEntities.ChatRoomFromDb){
            itemView.chatRoomName.text = p.name
            when(p.type){
                "curator" -> {
                    GlideApp.with(context)
                        .load(R.drawable.ic_customer_service)
                        .placeholder(R.color.colorWhite)
                        .into(itemView.chatRoomAvatar)
                }

                "psychologist" -> {
                    GlideApp.with(context)
                        .load(R.drawable.ic_doctor)
                        .placeholder(R.color.colorWhite)
                        .into(itemView.chatRoomAvatar)
                }

                else -> {
                    GlideApp.with(context)
                        .load(R.drawable.ic_person_black_24dp)
                        .placeholder(R.color.colorWhite)
                        .into(itemView.chatRoomAvatar)
                }
            }

            itemView.setOnClickListener {
                listener.startChatActivity(p.id)
            }
//            itemView.notificationTitle.text = p.title
//            itemView.notificationDescription.text = p.description
        }
    }
}