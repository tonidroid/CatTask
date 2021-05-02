package com.example.catimages.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catimages.databinding.GridItemCatBinding
import com.example.catimages.models.PagedCat

class ImageGridAdapter(private val onClickListener: OnClickListener ) :
    ListAdapter<PagedCat, ImageGridAdapter.CatViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<PagedCat>() {

        override fun areItemsTheSame(oldItem: PagedCat, newItem: PagedCat): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: PagedCat, newItem: PagedCat): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class CatViewHolder(private var binding: GridItemCatBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: PagedCat) {
            binding.cat = cat
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CatViewHolder {
        return CatViewHolder(GridItemCatBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }


    class OnClickListener(val clickListener: (cat:PagedCat) -> Unit) {
        fun onClick(pagedCat:PagedCat) = clickListener(pagedCat)
    }
}