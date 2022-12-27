package com.example.restapimethodskotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapimethodskotlin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private var binding: ActivityMainBinding? = null
    private var jsonHolder: jsonPlaceholder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.recyclerView!!.layoutManager = LinearLayoutManager(this)
        jsonHolder = ClientInstance.getRetrofitInstance().create(jsonPlaceholder::class.java)

        binding?.getPost!!.setOnClickListener {
            getPost()
        }

        binding?.getComment!!.setOnClickListener {
            getComments()
        }
        binding?.create!!.setOnClickListener {
            createPost()
        }
        binding?.updateBtn!!.setOnClickListener {
            updatePost()
        }
        binding?.delete!!.setOnClickListener {
            deletePost()
        }
    }

    private fun getPost() {
        val call: Call<List<Post>> = jsonHolder?.getPost()!!
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@MainActivity, response.code(), Toast.LENGTH_SHORT).show()

                }
                val postList: List<Post>? = response.body()!!
                val postadapter = PostAdapter(postList!!)
                binding?.recyclerView!!.adapter = postadapter

            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun getComments() {
        val call: Call<List<Comment?>?> = jsonHolder?.getComments(2)!!
        call.enqueue(object : Callback<List<Comment?>?> {
            override fun onResponse(
                call: Call<List<Comment?>?>,
                response: Response<List<Comment?>?>
            ) {
                if (!response.isSuccessful)
                    Toast.makeText(this@MainActivity, response.code(), Toast.LENGTH_SHORT).show()
                val commentList: List<Comment?> = response.body()!!
                val comment = CommentAdapter(commentList)
                binding?.recyclerView!!.adapter = comment
            }

            override fun onFailure(call: Call<List<Comment?>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun createPost() {
        val post = Post("18", "First Title", "2", "Text")
        val call: Call<Post?>? = jsonHolder?.createPost(post)
        call?.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                if (!response.isSuccessful)
                    Toast.makeText(this@MainActivity, response.code(), Toast.LENGTH_SHORT).show()
                val postList = ArrayList<Post>()
                postList.add(response.body()!!)
                val adapter = PostAdapter(postList)
                binding?.recyclerView!!.adapter = adapter

            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun updatePost() {
        val post = Post("13", "new title", "2", null)
        // val call: Call<Post?>?=jsonHolder?.putPost(2,post)
        val call: Call<Post?>? = jsonHolder?.patchPost(2, post)
        call?.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                if (!response.isSuccessful)
                    Toast.makeText(this@MainActivity, response.code(), Toast.LENGTH_SHORT).show()
                val updateList = ArrayList<Post>()
                updateList.add(response.body()!!)
                val adapter = PostAdapter(updateList)
                binding?.recyclerView!!.adapter = adapter


            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun deletePost() {
        val call: Call<Void>? = jsonHolder?.deletePost(2)
        call?.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (!response.isSuccessful) {
                    Log.d("Android", "onResponse: " + response.code())
                }
                Toast.makeText(
                    this@MainActivity,
                    "Deleted Successfully :" + response.code(),
                    Toast.LENGTH_LONG
                ).show()

            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}
