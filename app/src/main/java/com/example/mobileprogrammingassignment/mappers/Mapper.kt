package com.example.mobileprogrammingassignment.mappers

import com.example.mobileprogrammingassignment.database.entity.UserDataEt
import com.example.mobileprogrammingassignment.model.UsersResponse


object Mapper {

    fun convertUserDataModelToUserEntity(data: UsersResponse.Data): UserDataEt {
        return UserDataEt(
            id = data.id,
            name = data.name,
            email = data.email,
            gender = data.gender,
            status = data.status,
        )
    }
}