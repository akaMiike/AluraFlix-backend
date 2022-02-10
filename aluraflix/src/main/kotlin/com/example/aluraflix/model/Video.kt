package com.example.aluraflix.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank


@Entity
@Table(name="video")
class Video (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    var id:Long? = null,

    @field:NotBlank(message = "titulo é obrigatório")
    @Column(name="titulo")
    var titulo: String = "",

    @field:NotBlank(message = "descricao é obrigatório")
    @Column(name="descricao")
    var descricao: String = "",

    @field:NotBlank(message = "url é obrigatória")
    @Column(name="url")
    var url: String = ""
)