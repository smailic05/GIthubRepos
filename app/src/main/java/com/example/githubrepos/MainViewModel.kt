package com.example.githubrepos

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepos.retrofit.ItemRepos
import com.example.githubrepos.retrofit.Repositories
import com.example.githubrepos.retrofit.RetrofitRepository
import com.example.githubrepos.room.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private val retrofitRepository=RetrofitRepository()

    private val roomRepository=RoomRepository()

    private val _responseRepos= MutableLiveData<Repositories>()
    val responseRepos:LiveData<Repositories>
        get() = _responseRepos

    var listOfDownloadedItems= roomRepository.getAllRepositories()

    fun saveItem(itemRepos: ItemRepos,context:Context)=viewModelScope.launch(Dispatchers.IO){
        val request= DownloadManager.Request(
            Uri.
        parse("https://api.github.com/repos/${itemRepos.owner?.login}/${itemRepos.name}/zipball"))
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,itemRepos.name )
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        val dm=context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)

        roomRepository.insertIntoDatabase(itemRepos)
    }

    fun openBrowserWithContext(itemRepos: ItemRepos,context: Context){
        val browserIntent= Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://github.com/${itemRepos.owner?.login}/${itemRepos.name}"))
        context.startActivity(browserIntent)
    }

    fun getRepositoriesFromInternet(query: String)=viewModelScope.launch(Dispatchers.IO) {
        _responseRepos.postValue(retrofitRepository.createRequest(query))
    }

}