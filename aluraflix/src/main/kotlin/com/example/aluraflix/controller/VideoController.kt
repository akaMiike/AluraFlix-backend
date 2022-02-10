package com.example.aluraflix.controller

import com.example.aluraflix.model.Video
import com.example.aluraflix.service.VideoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

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

    @PutMapping("videos/{idVideo}")
    fun atualizarVideo(@PathVariable("idVideo") idVideo: Long, @RequestBody video: Video){
        val videoAntigo = serviceVideo.buscar(idVideo) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,"Video nao encontrado")
        val videoAtualizado = Video(
                videoAntigo.id,
                video.titulo,
                video.descricao,
                video.url
        )

        serviceVideo.atualizar(videoAtualizado)
    }

}