package com.example.aluraflix

import com.example.aluraflix.dto.VideoCreationDTO
import com.example.aluraflix.dto.VideoUpdateDTO
import com.example.aluraflix.model.Categoria
import com.example.aluraflix.model.Video
import com.example.aluraflix.service.CategoriaService
import com.example.aluraflix.service.VideoService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.random.Random.Default.nextInt

@SpringBootTest
@AutoConfigureMockMvc
class TestesDeStatusCode @Autowired constructor(val mvc: MockMvc, val serviceVideo: VideoService, val serviceCategoria: CategoriaService ,val objectMapper: ObjectMapper){

    val categoria = Categoria(1,"LIVRE","azul")

    @BeforeEach
    fun initCategoria(){
        serviceCategoria.criar(categoria)
    }

    @Test
    fun testarStatusCodeRetornarVideos(){
        mvc.perform(MockMvcRequestBuilders.get("/videos"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun testarStatusCodeRetornarVideoPorIdInexistente(){
        mvc.perform(MockMvcRequestBuilders.get("/videos/" + nextInt(10,100)))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun testarStatusCodeRetornarVideoPorIdExistente(){
        val video = Video(null, categoria,"titulo1","descricao1","www.video1.com")
        serviceVideo.criar(video)

        mvc.perform(MockMvcRequestBuilders.get("/videos/"+ video.id.toString()))
            .andExpect(MockMvcResultMatchers.status().isOk)

    }

    @Test
    fun testarStatusCodeRemoverVideoPorIdInexistente(){
        mvc.perform(MockMvcRequestBuilders.delete("/videos/" + nextInt(10,100)))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun testarStatusCodeRemoverVideoPorIdExistente(){
        val video = Video(1, categoria,"titulo1","descricao1","www.video1.com")
        serviceVideo.criar(video)

        mvc.perform(MockMvcRequestBuilders.delete("/videos/" + video.id.toString()))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun testarStatusCodeCriarVideo(){
        val video = VideoCreationDTO(categoria.id,"titulo1","descricao1","www.video1.com")

        mvc.perform(MockMvcRequestBuilders.post("/videos")
            .content(objectMapper.writeValueAsString(video))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun testarStatusCodeAtualizarVideo(){
        val videoAntigo = Video(null, categoria,"titulo1","descricao1","www.video1.com")
        serviceVideo.criar(videoAntigo)

        mvc.perform(MockMvcRequestBuilders.put("/videos/" + videoAntigo.id.toString())
            .content(objectMapper.writeValueAsString(VideoUpdateDTO(categoria.id,"titulo2","descricao2","www.video2.com")))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)

        serviceVideo.remover(videoAntigo)
    }

}