package com.example.aluraflix.dto

import javax.validation.constraints.NotBlank

data class VideoCreationDTO(
    val idCategoria: Long?,
    @field:NotBlank(message = "titulo é obrigatório") val titulo: String?,
    @field:NotBlank(message = "descricao é obrigatório") val descricao: String?,
    @field:NotBlank(message = "url é obrigatória") val url: String?
)

data class VideoUpdateDTO(
    val idCategoria: Long?,
    val titulo: String?,
    val descricao: String?,
    val url: String?,
)