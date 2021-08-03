package com.example.githubrepos.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.githubrepos.MainViewModel
import com.example.githubrepos.adapter.RepositoriesAdapter
import com.example.githubrepos.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment:Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val model: MainViewModel by activityViewModels()
    private lateinit var  repositoriesAdapter: RepositoriesAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        repositoriesAdapter= RepositoriesAdapter(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.snackbar.observe(viewLifecycleOwner,{
            Snackbar.make(view,it,Snackbar.LENGTH_SHORT).show()
        })
        binding.recycler.adapter=repositoriesAdapter
        model.responseRepos.observe(viewLifecycleOwner,{
            repositoriesAdapter.submitList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}