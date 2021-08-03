package com.example.githubrepos.retrofit

class RetrofitRepository {
    private var repositories: RepositoriesApi = RetrofitBuilder.API_SERVICE

    suspend fun createRequest(query:String) :Repositories
    {
        return repositories.getRepos(query)
    }
}