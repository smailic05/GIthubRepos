package com.example.githubrepos.room

import androidx.lifecycle.LiveData
import com.example.githubrepos.ApplicationGithub
import com.example.githubrepos.retrofit.ItemRepos


class RoomRepository {
    private val db= ApplicationGithub.instance?.database
    fun insertIntoDatabase( arr: ItemRepos){
        db?.repositoriesDao()?.insert(arr)
    }
    fun deleteFromDatabase(item: ItemRepos)
    {
        db?.repositoriesDao()?.delete(item)
    }
    fun getAllRepositories(): LiveData<MutableList<ItemRepos>>?
    {
        return db?.repositoriesDao()?.getAllRepositories()
    }

}