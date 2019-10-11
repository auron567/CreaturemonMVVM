package com.example.creaturemonmvvm.view.avatar

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.creaturemonmvvm.R
import com.example.creaturemonmvvm.app.inflate
import com.example.creaturemonmvvm.model.Avatar
import kotlinx.android.synthetic.main.list_item_avatar.view.*

class AvatarAdapter(private val avatars: List<Avatar>, private val listener: AvatarListener)
    : RecyclerView.Adapter<AvatarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_item_avatar))
    }

    override fun getItemCount() = avatars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(avatars[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private lateinit var avatar: Avatar

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(avatar: Avatar) {
            this.avatar = avatar

            itemView.avatarImageView.setImageResource(avatar.drawable)
        }

        override fun onClick(v: View?) {
            listener.avatarClicked(avatar)
        }
    }
}