package com.example.aluraflix

import com.example.aluraflix.model.Video
import com.example.aluraflix.service.VideoService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity.post
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*
import kotlin.random.Random.Default.nextInt

@SpringBootTest
@AutoConfigureMockMvc
class TestesDeStatusCode @Autowired constructor(val mvc: MockMvc, val serviceVideo: VideoService, val objectMapper: ObjectMapper){

    @Test
    fun testarStatusCodeRetornarVideos(){
        mvc.perform(MockMvcRequestBuilders.get("/videos"))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }


}