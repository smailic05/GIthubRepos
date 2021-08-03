package com.example.githubrepos

import com.example.githubrepos.retrofit.ItemRepos

interface ItemAction {
    fun saveItem(itemRepos: ItemRepos)
    fun openBrowser(itemRepos: ItemRepos)
}