package kz.caremet.mentors.android_client_app.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_notiffication.view.*
import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.GlideApp
import kz.caremet.mentors.android_client_app.core.data.DataEntities

class NotificationAdapter(val dataset: List<DataEntities.Notification>,
                          val context: Context): RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_notiffication,parent,false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    inner class NotificationViewHolder(v: View): RecyclerView.ViewHolder(v){
        fun bind(p: DataEntities.Notification){
            itemView.notificationTitle.text = p.title
            itemView.notificationDescription.text = p.description

            when(p.type){
                "message" -> {
                    GlideApp.with(context)
                        .load(R.drawable.ic_message_black_24dp)
                        .placeholder(R.color.colorWhite)
                        .into(itemView.notificationLogo)
                }
                "important" -> {
                    GlideApp.with(context)
                        .load(R.drawable.ic_access_time_black_24dp)
                        .placeholder(R.color.colorWhite)
                        .into(itemView.notificationLogo)

                }
                "accept" -> {
                    GlideApp.with(context)
                        .load(R.drawable.ic_check_black_24dp)
                        .placeholder(R.color.colorWhite)
                        .into(itemView.notificationLogo)

                }
                else -> {
                    GlideApp.with(context)
                        .load(R.drawable.ic_priority_high_black_24dp)
                        .placeholder(R.color.colorWhite)
                        .into(itemView.notificationLogo)

                }
            }
        }
    }
}