package com.example.mobileprogrammingassignment.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobileprogrammingassignment.R
import com.example.mobileprogrammingassignment.api.RestApi
import com.example.mobileprogrammingassignment.database.DatabaseBuilder
import com.example.mobileprogrammingassignment.database.entity.UserDataEt
import com.example.mobileprogrammingassignment.model.UsersResponse
import com.example.mobileprogrammingassignment.utils.ApiConstant
import com.example.mobileprogrammingassignment.utils.Event
import com.example.mobileprogrammingassignment.utils.NetworkHelper
import com.example.mobileprogrammingassignment.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UsersRepo @Inject constructor(
    val restApi: RestApi,
    var networkHelper: NetworkHelper,
    var database: DatabaseBuilder
) {
    private val _getUsers = MutableLiveData<Event<Resource<UsersResponse>>>()
    val getUsers: LiveData<Event<Resource<UsersResponse>>>
        get() = _getUsers


    suspend fun getUsersData() {
        _getUsers.postValue(Event(Resource.loading(ApiConstant.GET_USERS, null)))
        if (networkHelper.isNetworkConnected()) {
            val apiResponse = restApi.getUsers()
            if (apiResponse.isSuccessful) {
                if (apiResponse.isSuccessful && apiResponse.body() != null) {
                    _getUsers.postValue(
                        Event(
                            Resource.success(
                                ApiConstant.GET_USERS,
                                apiResponse.body()
                            )
                        )
                    )
                } else {
                    _getUsers.postValue(
                        Event(
                            Resource.error(
                                ApiConstant.GET_USERS,
                                apiResponse.code(),
                                apiResponse.errorBody().toString(),
                                null
                            )
                        )
                    )
                }
            }
        } else {
            _getUsers.postValue(
                Event(
                    Resource.requiredResource(
                        ApiConstant.GET_USERS,
                        R.string.err_no_network_available
                    )
                )
            )
        }
    }

    suspend fun insertUsersInDB(usersEntityList: List<UserDataEt>) {
        if (networkHelper.isNetworkConnected()) {
            database.usersDao()!!.insertAll(usersEntityList)
        }
    }
}