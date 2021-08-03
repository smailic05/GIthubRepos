package com.example.githubrepos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepos.databinding.DownloadItemsBinding
import com.example.githubrepos.retrofit.ItemRepos

class DownloadAdapter:
    ListAdapter<ItemRepos, DownloadAdapter.ViewHolder>(itemComparator) {
    inner class ViewHolder(private val binding: DownloadItemsBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(itemRepos: ItemRepos){
                binding.owner.text=itemRepos.owner?.login
                binding.reposName.text=itemRepos.name
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding= DownloadItemsBinding.inflate(layoutInflater,parent,false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object {
        private val itemComparator = object : DiffUtil.ItemCallback<ItemRepos>() {

            override fun areItemsTheSame(oldItem: ItemRepos, newItem: ItemRepos): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ItemRepos, newItem: ItemRepos): Boolean {
                return oldItem.description == newItem.description
            }

            override fun getChangePayload(oldItem: ItemRepos, newItem: ItemRepos) = Any()
        }
    }
}

