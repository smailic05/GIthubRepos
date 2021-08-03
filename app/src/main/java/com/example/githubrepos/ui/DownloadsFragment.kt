package com.example.githubrepos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.githubrepos.adapter.DownloadAdapter
import com.example.githubrepos.MainViewModel
import com.example.githubrepos.databinding.DownloadsFragmentBinding


import com.example.githubrepos.R


class DownloadsFragment: Fragment() {
    private var _binding: DownloadsFragmentBinding? = null
    private val binding get() = _binding!!
    val model: MainViewModel by activityViewModels()
    private val downloadAdapter= DownloadAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DownloadsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter=downloadAdapter
        model.listOfDownloadedItems?.observe(viewLifecycleOwner,{
            downloadAdapter.submitList(it)
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val search = menu.findItem(R.id.search)
        search?.isVisible = false
        menu.findItem(R.id.downloadsFragment)?.isVisible = false
    }
}