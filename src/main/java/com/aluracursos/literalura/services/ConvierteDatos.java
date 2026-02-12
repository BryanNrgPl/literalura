package com.aluracursos.literalura.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos {

    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T obtnerDatos(String json, Class<T> tclass){
        try{
            return objectMapper.readValue(json, tclass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir Json " + e);
        }
    }

}
