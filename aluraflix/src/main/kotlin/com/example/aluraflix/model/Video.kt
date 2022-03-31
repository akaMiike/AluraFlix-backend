package com.example.aluraflix.model

import javax.persistence.*

@Entity
@Table(name="video")
class Video (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    var id:Long? = null,

    @ManyToOne
    @JoinColumn(name="categoria")
    var categoria: Categoria,

    @Column(name="titulo")
    var titulo: String,

    @Column(name="descricao")
    var descricao: String,

    @Column(name="url")
    var url: String
)