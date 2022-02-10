package com.example.aluraflix.service

import com.example.aluraflix.model.Video
import com.example.aluraflix.model.VideoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class VideoService @Autowired constructor(val repository: VideoRepository) {
    fun criar(video: Video){
        repository.save(video)
    }

    fun atualizar(video: Video): Video {
        video.id!!
        return repository.save(video)
    }

    fun remover(video: Video) {
        video.id!!
        repository.delete(video)
    }

    fun buscar(id: Long): Video? {
        return repository.findByIdOrNull(id)
    }

    fun index(): List<Video> {
        return repository.findAll()
    }
}