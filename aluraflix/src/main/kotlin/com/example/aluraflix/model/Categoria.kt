package com.example.aluraflix.model

import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity
@Table(name="categoria")
class Categoria (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    var id:Long? = null,

    @field:NotBlank(message = "titulo é obrigatório")
    @Column(name="titulo")
    var titulo: String,

    @field:NotBlank(message = "cor é obrigatória")
    @Column(name="cor")
    var cor: String,

)