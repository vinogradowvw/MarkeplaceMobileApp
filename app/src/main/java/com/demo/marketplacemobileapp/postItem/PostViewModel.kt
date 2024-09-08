package com.demo.marketplacemobileapp.postItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.marketplacemobileapp.dataClasses.Post
import com.demo.marketplacemobileapp.requests.getPostById
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()

    val posts: LiveData<List<Post>> get() = _posts

    // Функция для загрузки постов
    fun loadPosts(evenPosts: List<Long>) {
        viewModelScope.launch {
            val postsList = mutableListOf<Post>()

            for (id in evenPosts) {
                val result = getPostById(id)
                result.observeForever { response ->
                    when {
                        response.isSuccess -> postsList.add(response.getOrNull() ?: return@observeForever)
                        response.isFailure -> {

                        }
                    }
                    _posts.postValue(postsList)
                }
            }
        }
    }
}
