package kz.caremet.mentors.android_client_app.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_post.view.*
import kz.caremet.mentors.android_client_app.R
import kz.caremet.mentors.android_client_app.core.DataEntities

class PostAdapter(val dataset: List<DataEntities.Post>): RecyclerView.Adapter<PostAdapter.PostViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    inner class PostViewHolder(v: View): RecyclerView.ViewHolder(v){
        fun bind(p: DataEntities.Post){
            itemView.postText.text = p.text

            p.title?.let {
                itemView.postTitle.text = p.title
                itemView.postTitle.visibility = TextView.VISIBLE
            }
            p.photo?.let {
                itemView.postPhoto.visibility = ImageView.VISIBLE
            }
        }
    }
}