package com.aluracursos.literalura.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {


    public String obtenerDatos(String nombreLibro) {

        //url
        URI direccion = URI.create("https://gutendex.com/books/?search=" + nombreLibro.replace(" ", "+"));

        //cliente
        HttpClient client = HttpClient.newHttpClient();

        //request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        //response
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("No se pudo obtener la información del libro", e);
        }
    }
}
