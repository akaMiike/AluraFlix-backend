package com.example.aluraflix.controller

import com.example.aluraflix.dto.VideoCreationDTO
import com.example.aluraflix.dto.VideoUpdateDTO
import com.example.aluraflix.model.Video
import com.example.aluraflix.service.CategoriaService
import com.example.aluraflix.service.VideoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
class VideoController @Autowired constructor(val serviceVideo: VideoService, val serviceCategoria: CategoriaService) {

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
    fun criarVideo(@Valid @RequestBody video: VideoCreationDTO){
        val categoria = serviceCategoria.buscar(video.idCategoria ?: 1) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,"Categoria não encontrada.")
        val novoVideo = Video(
            categoria = categoria,
            titulo = video.titulo!!,
            descricao = video.descricao!!,
            url = video.url!!

        )
        serviceVideo.criar(novoVideo)
    }

    @DeleteMapping("videos/{idVideo}")
    fun deletarVideo(@PathVariable("idVideo") idVideo: Long){
        val video = serviceVideo.buscar(idVideo) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,"Video nao encontrado")
        serviceVideo.remover(video)
    }

    @PutMapping("videos/{idVideo}")
    fun atualizarVideo(@PathVariable("idVideo") idVideo: Long, @RequestBody video: VideoUpdateDTO){
        val videoAntigo = serviceVideo.buscar(idVideo) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,"Video nao encontrado")
        val categoriaAtualizada =
            if (video.idCategoria != null) serviceCategoria.buscar(video.idCategoria) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,"Categoria nao encontrada")
            else videoAntigo.categoria

        val videoAtualizado = Video(
                id = videoAntigo.id,
                categoria = categoriaAtualizada,
                titulo = video.titulo!!,
                descricao = video.descricao!!,
                url = video.url!!
        )

        serviceVideo.atualizar(videoAtualizado)
    }

}