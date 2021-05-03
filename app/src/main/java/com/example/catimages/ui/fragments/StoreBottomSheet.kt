package com.example.catimages.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.catimages.databinding.BottomSheetStoredBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StoreBottomSheet  : BottomSheetDialogFragment() {

    private lateinit var bind: BottomSheetStoredBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = BottomSheetStoredBinding.inflate(inflater, container, false)

        bind.ibClose.setOnClickListener { dismiss() }

        return bind.root
    }

}