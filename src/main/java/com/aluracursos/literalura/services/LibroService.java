package com.aluracursos.literalura.services;

import com.aluracursos.literalura.models.ApiDatosRespuesta;
import com.aluracursos.literalura.models.DatosLibro;
import com.aluracursos.literalura.models.Libro;

import java.util.Optional;

public class LibroService {
    private LibroRepository repositorio;
    private ApiService apiService = new ApiService();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public LibroService(LibroRepository repositorio){
        this.repositorio = repositorio;
    }

    public void obtenerLibro(String titulo){
        // Obtener json
        String json = apiService.obtenerDatos(titulo);

        // Convertir datos de la respuesta api
        ApiDatosRespuesta datos = convierteDatos.obtnerDatos(json, ApiDatosRespuesta.class);

        // Extraer el primer libro
        Optional<DatosLibro> primerLibroEcontrado = datos.resultados()
                .stream()
                .findFirst();

        // verificar si el el libro existe
        if (primerLibroEcontrado.isPresent()){

            // extrayendo el Record
            DatosLibro datosLibro = primerLibroEcontrado.get();

            //Verificar si el libro existe en la base de datos
            Optional<Libro> libroEnDB = repositorio.findByTituloContainsIgnoreCase(datosLibro.titulo());

            if (libroEnDB.isPresent()){
                Libro libroExistente = libroEnDB.get();
                System.out.println( "\n"  + "El libro: " + datosLibro.titulo() + " ya se encuentra en la base de datos.");
                System.out.println(libroExistente);
            }else {

                // usando el Record extraido para crear un Libro
                Libro libroEncontrado = new Libro(datosLibro);

                // Guardando libro en la base de datos
                repositorio.save(libroEncontrado);

                //System.out.println("Libro guardado: " + libroEncontrado.getTitulo());
                System.out.println("Libro guardado: ");
                System.out.println(libroEncontrado);
            }
        }else {
            System.out.println("No se encontro el libro, intenta escribir el nombre correctamente.");
        }


    }

}
