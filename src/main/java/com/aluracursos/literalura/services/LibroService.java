package com.aluracursos.literalura.services;

public class LibroService {
    private LibroRepository repositorio;
    private ApiService apiService = new ApiService();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public LibroService(LibroRepository repositorio){
        this.repositorio = repositorio;
    }

    public void obtenerLibro(String titulo){

    }

}
