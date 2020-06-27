package id.sharekom.githubuser.adapters

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.sharekom.githubuser.views.DetailActivity
import id.sharekom.githubuser.R
import id.sharekom.githubuser.helpers.Values
import id.sharekom.githubuser.models.searchdata.SearchDataItem
import kotlinx.android.synthetic.main.list_main.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private lateinit var list:List<SearchDataItem>

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setData(list:List<SearchDataItem>){
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_main, parent, false)

        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .setDefaultRequestOptions(object : RequestOptions() {
                override fun placeholder(drawable: Drawable?): RequestOptions {
                    return super.placeholder(R.color.colorPrimary)
                }
            })
            .load(list[position].avatar_url)
            .into(holder.itemView.profile_image)

        holder.itemView.name.text = list[position].login
        holder.itemView.type.text = holder.itemView.resources.getString(R.string.user_type, list[position].type)
        holder.itemView.user_id.text = holder.itemView.resources.getString(R.string.user_id, list[position].id)

        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString(Values.DATA_MAIN, list[position].login)
            intent.putExtras(bundle)
            ContextCompat.startActivity(it.context, intent, null)
        }
    }
}