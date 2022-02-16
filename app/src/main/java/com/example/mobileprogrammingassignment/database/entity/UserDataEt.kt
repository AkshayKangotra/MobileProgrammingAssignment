package com.example.mobileprogrammingassignment.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USERS")
data class UserDataEt (
    @PrimaryKey
    var id     : Int?    = null,
    var name   : String? = null,
    var email  : String? = null,
    var gender : String? = null,
    var status : String? = null
  )