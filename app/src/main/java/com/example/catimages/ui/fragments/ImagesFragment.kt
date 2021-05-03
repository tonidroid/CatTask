package com.example.catimages.ui.fragments


import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.catimages.databinding.FragmentImagesBinding
import com.example.catimages.ui.adapters.ImageGridAdapter
import com.example.catimages.viewmodels.ImagesViewModel


class ImagesFragment : Fragment() {

    private val viewModel: ImagesViewModel by lazy {
        ViewModelProvider(this).get(ImagesViewModel::class.java)
    }

    private lateinit var bind: FragmentImagesBinding

    private lateinit var adapter: ImageGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentImagesBinding.inflate(inflater)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel

        configRecycler()

        bind.btnStore.setOnClickListener {
            val bottomSheetFragment = StoreBottomSheet()
            bottomSheetFragment.show(requireActivity().supportFragmentManager, bottomSheetFragment.tag)
        }

        return bind.root
    }


    private fun configRecycler() {
        adapter = ImageGridAdapter(ImageGridAdapter.OnClickListener { cat ->
            findNavController()
                .navigate(ImagesFragmentDirections.actionImagesFragmentToDetailFragment(cat))
        })
        bind.recCats.adapter = adapter
        viewModel.cats.observe(viewLifecycleOwner, { adapter.submitList(it) })

    }

}