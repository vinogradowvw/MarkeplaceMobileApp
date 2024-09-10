package com.demo.marketplacemobileapp.dataClasses

class PostConverter {
    companion object{
        fun toEntity(postDto: PostDTO): Post {
            return Post(
                id = postDto.id,
                description = postDto.description,
                name = postDto.name,
                views = postDto.views,
                images = postDto.images
            )
        }
    }
}
