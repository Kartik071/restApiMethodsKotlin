package com.example.restapimethodskotlin

import com.google.gson.annotations.SerializedName

data class Post(
    @field:SerializedName("userId") var userId: String,
    @field:SerializedName("title") var title: String,
    @field:SerializedName("id") var id: String,
    @field:SerializedName("body") var body: String?
)  {
}