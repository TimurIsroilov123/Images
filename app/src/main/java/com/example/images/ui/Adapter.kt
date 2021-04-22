package com.example.images.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.images.databinding.ListItemBinding

class Adapter(private val listener: OnClickListener) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var contentData = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contentData[position])
    }

    override fun getItemCount(): Int = contentData.size

    fun update(urls: List<String>) {
        notifyItemRangeChanged(contentData.size, urls.size)
        contentData = urls.toMutableList()
    }

    inner class ViewHolder(private val binding: ListItemBinding, listener: OnClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val url = contentData[position]
                            listener.onItemCLick(url)
                    }
                }
            }
        }

        fun bind(url: String) {
            binding.apply {
                Glide
                    .with(itemView.context)
                    .load(url)
                    .centerCrop()
                    .into(ivImage)
            }
        }
    }
}

interface OnClickListener {
    fun onItemCLick(url: String)
}