package com.example.githubrepos.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubrepos.retrofit.ItemRepos
import com.example.githubrepos.retrofit.Repositories

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg terminals: ItemRepos)

    @Delete
    fun delete(terminals: ItemRepos)

    @Query("SELECT * FROM repositories")
    fun getAllRepositories(): LiveData<MutableList<ItemRepos>>
}