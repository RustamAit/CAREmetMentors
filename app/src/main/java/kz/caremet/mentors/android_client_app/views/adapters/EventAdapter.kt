package kz.caremet.mentors.android_client_app.views.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_event.view.*
import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.data.DataEntities
import kz.caremet.mentors.android_client_app.core.getDateStringFromIso

class EventAdapter(val dataset: List<DataEntities.EventFromDb>,
                   val listener: ItemEventListener): RecyclerView.Adapter<EventAdapter.EventViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event,parent,false))

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(dataset[position])
    }


    inner class EventViewHolder(v: View): RecyclerView.ViewHolder(v){
        fun bind(e: DataEntities.EventFromDb){
            itemView.eventTitle.text = e.title
            itemView.eventDescription.text = e.description
            e.date?.let {
                itemView.date.text = getDateStringFromIso(e.date)
                if(getDateStringFromIso(e.date) == "Сегодня"){
                    itemView.date.setTextColor(Color.RED)
                }

            }

            itemView.btnnlyat.setOnClickListener {
                listener.startReportCreateActivity(e.id, e.title)
            }

        }
    }
}

interface ItemEventListener{
    fun startReportCreateActivity(eventId: Int, eventTitle: String)
}