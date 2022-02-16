package com.example.mobileprogrammingassignment.api

import com.example.mobileprogrammingassignment.model.UsersResponse
import com.example.mobileprogrammingassignment.utils.ApiConstant
import retrofit2.Response
import retrofit2.http.GET

interface RestApi {

    @GET(ApiConstant.GET_USERS)
    suspend fun getUsers(): Response<UsersResponse>
}