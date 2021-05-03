package com.example.catimages.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catimages.databinding.GridItemCatBinding
import com.example.catimages.models.Cat

class ImageGridAdapter(private val onClickListener: OnClickListener ) :
    ListAdapter<Cat, ImageGridAdapter.CatViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Cat>() {

        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class CatViewHolder(private var binding: GridItemCatBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: Cat) {
            binding.cat = cat
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CatViewHolder {
        return CatViewHolder(GridItemCatBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(cat)
        }
        holder.bind(cat)
    }


    class OnClickListener(val clickListener: (cat: Cat) -> Unit) {
        fun onClick(pagedCat: Cat) = clickListener(pagedCat)
    }
}