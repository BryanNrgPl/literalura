package com.aluracursos.literalura.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;
    private String idioma;
    private int numeroDescargas;


    public Libro(){}


    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.numeroDescargas = datosLibro.numeroDescargas();

        if (datosLibro.idioma() != null && !datosLibro.idioma().isEmpty()){
            this.idioma = datosLibro.idioma().get(0);
        }

        if (datosLibro.autor() != null){
            this.autores = datosLibro.autor().stream()
                    .map(a -> new Autor(a))
                    .peek(a -> a.setLibro(this))
                    .toList();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString(){
        String autoresNombres = autores.stream()
                .map(a -> a.getNombre())
                .collect(Collectors.joining(" - "));

        String autorFormato = (autores.size() > 1) ? "Autores: " : "Autor: ";

        return "\n" + "-------LIBRO------ " + "\n" +
                "Titulo: " + titulo + "\n" +
                autorFormato + autoresNombres + "\n" +
                "Idioma: " + idioma +  "\n" +
                "Número de descargas: " + numeroDescargas;


    }
}