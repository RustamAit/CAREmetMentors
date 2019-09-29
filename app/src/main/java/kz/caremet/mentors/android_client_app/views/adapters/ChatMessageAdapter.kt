package kz.caremet.mentors.android_client_app.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_answer.view.*
import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.data.DataEntities

class ChatMessageAdapter(val dataset: List<DataEntities.MessageFormDb>): RecyclerView.Adapter<ChatMessageAdapter.ChatMessageViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder {
        return ChatMessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_answer,parent,false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ChatMessageViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    inner class ChatMessageViewHolder(v: View): RecyclerView.ViewHolder(v){
        fun bind(p: DataEntities.MessageFormDb){
            itemView.sender_name.text = p.sender_name
            itemView.position.text = p.sender_type
            itemView.text.text = p.text

            if(p.sendStatus == DataEntities.StatusConstants.CREATED){
                itemView.isSended.visibility = ImageView.VISIBLE
            }
            else{
                itemView.isSended.visibility = ImageView.GONE
            }

            itemView.senderPhotoText.text = p.sender_name[0].toString()

//            itemView.notificationTitle.text = p.title
//            itemView.notificationDescription.text = p.description
        }
    }
}