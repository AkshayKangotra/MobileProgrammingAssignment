package com.example.mobileprogrammingassignment.injection

import com.example.mobileprogrammingassignment.api.RestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesUrl():String="https://gorest.co.in/public/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(providesUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): RestApi =retrofit.create(RestApi::class.java)
}