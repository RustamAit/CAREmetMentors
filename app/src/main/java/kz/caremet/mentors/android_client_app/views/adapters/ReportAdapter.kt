package kz.caremet.mentors.android_client_app.views.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_report.view.*
import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.data.DataEntities

class ReportAdapter(val dataset: List<DataEntities.Report>): RecyclerView.Adapter<ReportAdapter.ReportViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        return ReportViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_report,parent,false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    inner class ReportViewHolder(v: View): RecyclerView.ViewHolder(v){
        fun bind(p: DataEntities.Report){
            itemView.postText.text = p.text

            p.title.let {
                itemView.postTitle.text = p.title
                itemView.postTitle.visibility = TextView.VISIBLE
            }

            itemView.aaaa.text = p.status
        }
    }
}