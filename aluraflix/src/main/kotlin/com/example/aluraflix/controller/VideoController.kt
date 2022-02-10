package com.example.aluraflix.controller

import com.example.aluraflix.model.Video
import com.example.aluraflix.service.VideoService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class VideoController @Autowired constructor(val serviceVideo: VideoService) {

    @GetMapping("videos/{idVideo}")
    fun retornarVideo(@PathVariable("idVideo") idVideo: Long): ResponseEntity<Video> {
        val video = serviceVideo.buscar(idVideo) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,"Video nao encontrado")
        return ResponseEntity.ok(video)
    }

    @GetMapping("videos")
    fun retornarTodosVideos(): ResponseEntity<List<Video>> {
        return ResponseEntity.ok(serviceVideo.index())
    }

    @PostMapping("videos")
    fun criarVideo(@Valid @RequestBody video: Video){
        serviceVideo.criar(video)
    }

    @DeleteMapping("videos/{idVideo}")
    fun deletarVideo(@PathVariable("idVideo") idVideo: Long){
        val video = serviceVideo.buscar(idVideo) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,"Video nao encontrado")
        serviceVideo.remover(video)
    }

}