package com.example.githubrepos.retrofit


import retrofit2.http.GET
import retrofit2.http.Path

public interface RepositoriesApi {
    @GET("/users/{q}/repos")
    suspend fun getRepos(@Path("q") query: String): List<ItemRepos>
}