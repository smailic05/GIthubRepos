package com.example.githubrepos.retrofit


import retrofit2.http.GET
import retrofit2.http.Query

public interface RepositoriesApi {
    @GET("/search/repositories")
    suspend fun getRepos(@Query("q") query: String): Repositories
}