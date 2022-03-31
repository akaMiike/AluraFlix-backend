package com.example.aluraflix.model

import org.springframework.data.jpa.repository.JpaRepository

interface VideoRepository: JpaRepository<Video, Long>

interface CategoriaRepository: JpaRepository<Categoria, Long>