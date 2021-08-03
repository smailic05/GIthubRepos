package com.example.githubrepos.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.githubrepos.ItemAction
import com.example.githubrepos.databinding.ReposItemBinding
import com.example.githubrepos.retrofit.ItemRepos


class RepositoriesAdapter(private var context: Context):
    ListAdapter<ItemRepos, RepositoriesAdapter.ViewHolder>(itemComparator) {

    val listener=context as ItemAction

    inner class ViewHolder(private val binding: ReposItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemRepos: ItemRepos){
            binding.description.text=itemRepos.description
            binding.reposName.text=itemRepos.name
            binding.nameUser.text=itemRepos.owner?.login
            binding.numberOfStars.text= itemRepos.stargazersCount.toString()
            binding.imageAvatar.load(itemRepos.owner?.avatarUrl){
                transformations(CircleCropTransformation())
            }
            binding.root.setOnClickListener {
                listener.openBrowser(itemRepos)
            }
            binding.download.setOnClickListener {

                listener.saveItem(itemRepos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding= ReposItemBinding.inflate(layoutInflater,parent,false)
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