package com.example.githubrepos.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubrepos.retrofit.Converters
import com.example.githubrepos.retrofit.ItemRepos

@Database(entities = arrayOf(ItemRepos::class), version = 1)
@TypeConverters(Converters::class)
abstract  class AppDatabase: RoomDatabase() {
    abstract fun repositoriesDao(): DatabaseDao
}