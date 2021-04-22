package com.example.images.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.images.R
import com.example.images.databinding.FragmentImageBinding
import com.example.images.databinding.FragmentListOfImagesBinding

class FragmentImage : Fragment(R.layout.fragment_image) {

    val args: FragmentImageArgs by navArgs()

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Glide.with(view.context)
            .load(args.url)
            .fitCenter()
            .into(ivFullscreen)
        }
    }

}