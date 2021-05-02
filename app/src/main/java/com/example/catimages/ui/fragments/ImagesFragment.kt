package com.example.catimages.ui.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.catimages.R
import com.example.catimages.databinding.FragmentImagesBinding
import com.example.catimages.ui.adapters.ImageGridAdapter
import com.example.catimages.viewmodels.ImagesViewModel


class ImagesFragment : Fragment() {

    private val viewModel: ImagesViewModel by lazy {
        ViewModelProvider(this).get(ImagesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentImagesBinding.inflate(inflater)

        bind.lifecycleOwner = this
        bind.viewModel = viewModel

        bind.btnSerach.setOnClickListener {
//            viewModel.getPagedCats(4,2)
        }

        bind.recCats.adapter = ImageGridAdapter(ImageGridAdapter.OnClickListener {
//            viewModel.displayPropertyDetails(it)
        })


        return bind.root
    }

}