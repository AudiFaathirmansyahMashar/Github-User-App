package id.sharekom.githubuser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.sharekom.githubuser.R
import id.sharekom.githubuser.models.searchdata.SearchDataItem
import kotlinx.android.synthetic.main.list_main.view.*

class FollowingAdapter(private val list:List<SearchDataItem>) : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {
    inner class FollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder = FollowingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_main, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(list[position].avatar_url)
            .into(holder.itemView.profile_image)

        holder.itemView.name.text = list[position].login
        holder.itemView.type.text = holder.itemView.resources.getString(R.string.user_type, list[position].type)
        holder.itemView.user_id.text = holder.itemView.resources.getString(R.string.user_id, list[position].id)
    }

}