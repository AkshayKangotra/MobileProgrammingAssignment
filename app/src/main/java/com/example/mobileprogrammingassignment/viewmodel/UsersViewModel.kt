package com.example.mobileprogrammingassignment.viewmodel

import androidx.lifecycle.*
import com.example.mobileprogrammingassignment.database.entity.UserDataEt
import com.example.mobileprogrammingassignment.repo.UsersRepo
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import androidx.lifecycle.ViewModel
import com.example.mobileprogrammingassignment.database.DatabaseBuilder
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    val repo: UsersRepo,
    var database: DatabaseBuilder
) : ViewModel() {

    fun getUsers() {
        viewModelScope.launch {
            repo.getUsersData()
        }
    }

    /*Used Coroutines and Scope*/
    suspend fun getUsersFromDB(): Flow<List<UserDataEt>> {
        val list=viewModelScope.async {
             database.usersDao()!!.getOfflineUsers()
        }
        return list.await()
    }

    fun insertUsersInDB(usersEntityList: List<UserDataEt>) {
        viewModelScope.launch {
            repo.insertUsersInDB(usersEntityList)
        }
    }

    fun getUsersData() = repo.getUsers
}

class UsersVMFactory(private val repo: UsersRepo,private val  databaseBuilder: DatabaseBuilder) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            @Suppress("Unchecked_cast")
            return UsersViewModel(repo,databaseBuilder) as T
        }
        throw IllegalArgumentException("Unknown viewmodel exception")
    }
}