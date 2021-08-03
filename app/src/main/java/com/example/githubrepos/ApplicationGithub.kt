package com.example.githubrepos

import android.app.Application
import android.location.Location
import androidx.room.Room
import com.example.githubrepos.retrofit.Converters
import com.example.githubrepos.room.AppDatabase

class ApplicationGithub:Application() {
    companion object{

        //Чтобы иметь единственный экземпляр БД
        var instance: ApplicationGithub? = null
    }
    var database: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase")
            .build()
    }
}