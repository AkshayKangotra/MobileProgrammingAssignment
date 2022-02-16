package com.example.mobileprogrammingassignment.model

import com.google.gson.annotations.SerializedName

data class UsersResponse (
  @SerializedName("meta" ) var meta : Meta?= Meta(),
  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()
){
  data class Data (
    @SerializedName("id"     ) var id     : Int?    = null,
    @SerializedName("name"   ) var name   : String? = null,
    @SerializedName("email"  ) var email  : String? = null,
    @SerializedName("gender" ) var gender : String? = null,
    @SerializedName("status" ) var status : String? = null
  )
  data class Meta (
    @SerializedName("pagination" ) var pagination : Pagination? = Pagination()
  ){
    data class Pagination (
      @SerializedName("total" ) var total : Int?   = null,
      @SerializedName("pages" ) var pages : Int?   = null,
      @SerializedName("page"  ) var page  : Int?   = null,
      @SerializedName("limit" ) var limit : Int?   = null,
      @SerializedName("links" ) var links : Links? = Links()
    ){
      data class Links (
        @SerializedName("previous" ) var previous : String? = null,
        @SerializedName("current"  ) var current  : String? = null,
        @SerializedName("next"     ) var next     : String? = null
      )
    }
  }
}