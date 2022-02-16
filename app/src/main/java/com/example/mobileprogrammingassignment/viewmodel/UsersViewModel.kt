package com.example.mobileprogrammingassignment.viewmodel

import androidx.lifecycle.*
import com.example.mobileprogrammingassignment.database.entity.UserDataEt
import com.example.mobileprogrammingassignment.repo.UsersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class UsersViewModel @Inject constructor(val repo: UsersRepo) : ViewModel() {

    fun getUsers() {
        viewModelScope.launch {
            repo.getUsersData()
        }
    }

    fun getUsersFromDB() {
        viewModelScope.launch {
            repo.getUsersDataFromDB()
        }
    }

    fun insertUsersInDB(usersEntityList: List<UserDataEt>) {
        viewModelScope.launch {
            repo.insertUsersInDB(usersEntityList)
        }
    }

    fun getUsersData() = repo.getUsers

    fun getUsersDataDB() = repo.getUsersFromDB
}

class UsersVMFactory(private val repo: UsersRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            @Suppress("Unchecked_cast")
            return UsersViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown viewmodel exception")
    }
}