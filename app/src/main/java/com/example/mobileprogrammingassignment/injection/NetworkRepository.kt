package com.example.mobileprogrammingassignment.injection

import com.example.mobileprogrammingassignment.api.RestApi
import com.example.mobileprogrammingassignment.model.UsersResponse
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(val restApi: RestApi) {
    suspend fun getUsersData(): Response<List<UsersResponse>> {
        return restApi.getUsers()
    }
}