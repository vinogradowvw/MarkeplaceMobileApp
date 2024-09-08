package com.demo.marketplacemobileapp.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.marketplacemobileapp.config.config
import com.demo.marketplacemobileapp.dataClasses.Post
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import okio.IOException

fun getPostById(id: Long): LiveData<Result<Post>> {

    val liveData = MutableLiveData<Result<Post>>()

    val client = HTTPClient.client

    val request = Request.Builder()
        .url("${config.BASE_URL}/post/$id")
        .get()
        .build()

    client.newCall(request).enqueue(object : Callback {

        override fun onFailure(call: Call, e: IOException) {
            liveData.postValue(Result.failure(e))
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val responseData = response.body?.string()
                val post = parsePost(responseData)
                liveData.postValue(Result.success(post))
            } else {
                liveData.postValue(Result.failure(Exception("Network request failed")))
            }
        }
    })
    return liveData
}

fun parsePost(responseData: String?): Post {
    return Gson().fromJson(responseData, Post::class.java)
}