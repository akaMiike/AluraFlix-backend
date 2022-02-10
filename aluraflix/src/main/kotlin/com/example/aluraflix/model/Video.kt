package com.example.aluraflix.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank


@Entity
@Table(name="video")
open class Video (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    var id:Long? = null,

    @Column(name="titulo")
    @NotBlank
    var titulo: String = "",

    @Column(name="descricao")
    @NotBlank
    var descricao: String = "",

    @Column(name="url")
    @NotBlank
    var url: String = ""
)