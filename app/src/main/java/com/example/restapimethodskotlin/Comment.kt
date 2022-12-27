package com.example.restapimethodskotlin

import com.google.gson.annotations.SerializedName

data class Comment(
    @field:SerializedName("postId") var postId: String,
    @field:SerializedName("name") var name: String,
    @field:SerializedName("id") var id: String,
    @field:SerializedName("body") var commentText: String,
    @field:SerializedName("email") var email: String

)