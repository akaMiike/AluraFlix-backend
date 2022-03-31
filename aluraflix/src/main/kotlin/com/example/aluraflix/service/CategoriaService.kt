package com.example.aluraflix.service

import com.example.aluraflix.model.Categoria
import com.example.aluraflix.model.CategoriaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CategoriaService @Autowired constructor(val repository: CategoriaRepository) {
    fun criar(categoria: Categoria){
        repository.save(categoria)
    }

    fun atualizar(categoria: Categoria): Categoria {
        categoria.id!!
        return repository.save(categoria)
    }

    fun remover(categoria: Categoria) {
        categoria.id!!
        repository.delete(categoria)
    }

    fun buscar(id: Long): Categoria? {
        return repository.findByIdOrNull(id)
    }

    fun index(): List<Categoria> {
        return repository.findAll()
    }
}