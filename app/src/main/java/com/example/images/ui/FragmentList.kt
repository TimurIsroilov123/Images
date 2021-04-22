package com.example.images.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.images.R
import com.example.images.databinding.FragmentListOfImagesBinding
import kotlinx.coroutines.flow.collect

class FragmentList : Fragment(R.layout.fragment_list_of_images), OnClickListener {

    private var _binding: FragmentListOfImagesBinding? = null
    private val binding get() = _binding!!

    private val adapter: Adapter by lazy { Adapter(this) }

    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfImagesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.liveData.observe(viewLifecycleOwner) {
                adapter.update(it)
                rv.adapter = adapter
            }

            swipeRefresh.setOnRefreshListener {
                viewModel.updateImages(swipeRefresh)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemCLick(url: String) {
        val action = FragmentListDirections.actionFragmentListToFragmentImage(
           url
        )
        findNavController().navigate(action)
    }

}