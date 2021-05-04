package com.example.catimages.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.catimages.models.Cat


class MyDiffCallback(newCats: List<Cat>, oldCats: List<Cat>) :
    DiffUtil.Callback() {
    var oldCats: List<Cat> = oldCats
    var newCats: List<Cat> = newCats
    override fun getOldListSize(): Int {
        return oldCats.size
    }

    override fun getNewListSize(): Int {
        return newCats.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCats[oldItemPosition].id === newCats[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCats[oldItemPosition] == newCats[newItemPosition]
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}