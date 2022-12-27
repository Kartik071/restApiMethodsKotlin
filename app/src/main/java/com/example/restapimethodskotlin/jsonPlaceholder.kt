package com.example.restapimethodskotlin

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface jsonPlaceholder {
    @GET("posts")
    fun getPost(): Call<List<Post>>

    @GET("comments")
    fun getComments(@Query("postId") postId: Int): Call<List<Comment?>?>

    @POST("posts")
    fun createPost(@Body post: Post) : Call<Post?>

    @PUT("posts/{id}")
    fun putPost(@Path("id") Id: Int, @Body post: Post): Call<Post?>

    @PATCH("posts/{id}")
    fun patchPost(@Path("id") Id: Int, @Body post: Post): Call<Post?>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") Id: Int): Call<Void>
}