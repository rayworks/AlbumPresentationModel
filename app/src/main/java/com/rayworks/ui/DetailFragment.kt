package com.rayworks.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rayworks.albumpresentationmodel.AlbumDetailViewModel
import com.rayworks.albumpresentationmodel.R
import com.rayworks.albumpresentationmodel.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass for displaying the detail info of [Album].
 *
 */
class DetailFragment : Fragment() {

    private lateinit var rootView: View
    private var binding: FragmentDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_detail, container, false)
        return rootView
    }

    fun bind(viewModel: AlbumDetailViewModel) {
        if (binding == null)
            binding = FragmentDetailBinding.bind(rootView)

        binding!!.viewmodel = viewModel
    }
}
