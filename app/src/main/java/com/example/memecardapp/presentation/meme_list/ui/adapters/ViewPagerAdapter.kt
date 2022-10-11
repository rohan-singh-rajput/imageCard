package com.example.memecardapp.presentation.meme_list.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memeapp.data.network.apiData.Meme
import com.example.memecardapp.R


class ViewPagerAdapter (val context: Context, val memes : List<Meme>): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>(){
    class ViewPagerViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val imageview : ImageView = itemView.findViewById(R.id.memeImageView)
        val title : TextView = itemView.findViewById(R.id.memeTextView)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_container,parent,false)
        return ViewPagerViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentMeme = memes[position]
        holder.apply {
            Glide.with(context)
                .load(currentMeme.url)
                .into(imageview)
            title.text = currentMeme.name
        }

    }

    override fun getItemCount(): Int {
        return memes.size
    }


}
