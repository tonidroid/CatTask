package com.example.catimages.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.catimages.databinding.BottomSheetStoredBinding
import com.example.catimages.db.CatDatabase
import com.example.catimages.ui.adapters.StoredCatesAdapter
import com.example.catimages.utils.setupRatio
import com.example.catimages.viewmodels.ImagesViewModel
import com.example.catimages.viewmodels.ImagesViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class StoreBottomSheet  : BottomSheetDialogFragment() {

    private lateinit var bind: BottomSheetStoredBinding

    private lateinit var viewModel: ImagesViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = BottomSheetStoredBinding.inflate(inflater, container, false)

        bind.ibClose.setOnClickListener { dismiss() }
        bind.lifecycleOwner = this

        val catDao = CatDatabase.invoke(requireContext()).catDao()
        viewModel =  ViewModelProvider(this, ImagesViewModelFactory(catDao = catDao))
            .get(ImagesViewModel::class.java)
        bind.viewModel = viewModel


        configRecycler()


        bind.btnDeleteAll.setOnClickListener {
            viewModel.deleteAllCachedCats()
            (bind.recyclerView.adapter as StoredCatesAdapter).refreshList(listOf())
        }

        return bind.root
    }

    private fun configRecycler() {

        val adapter = StoredCatesAdapter()
        viewModel.localCats.observe(viewLifecycleOwner, {
            adapter.refreshList(it)
        })
        bind.recyclerView.adapter = adapter

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                viewModel.deleteCachedCat(viewModel.localCats.value!![pos])
                adapter.refreshList(viewModel.localCats.value!!)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(bind.recyclerView)

    }


    @Override
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            context?.let { setupRatio(bottomSheetDialog, it, 85) }
        }
        return dialog
    }


}

abstract class SwipeToDeleteCallback : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(0, swipeFlag)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }
}