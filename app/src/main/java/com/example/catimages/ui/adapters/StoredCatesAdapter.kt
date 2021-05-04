package com.example.catimages.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catimages.databinding.ItemStoredBinding
import com.example.catimages.models.Cat

class StoredCatesAdapter  :
    ListAdapter<Cat, StoredCatesAdapter.StoredViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class StoredViewHolder(private var binding: ItemStoredBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(cat: Cat) {
                binding.cat = cat
                binding.executePendingBindings()
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoredViewHolder {
        return StoredViewHolder(ItemStoredBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: StoredViewHolder, position: Int) {
        val cat = getItem(position)
        holder.bind(cat)
    }

    fun refreshList(cats: List<Cat>) {
        submitList(cats)
    }

}