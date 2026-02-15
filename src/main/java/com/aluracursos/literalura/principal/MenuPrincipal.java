package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.models.Autor;
import com.aluracursos.literalura.models.Libro;
import com.aluracursos.literalura.services.AutorRepository;
import com.aluracursos.literalura.services.LibroRepository;
import com.aluracursos.literalura.services.LibroService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuPrincipal {
    private boolean isNum = false;
    private LibroRepository repositorioLibros;
    private AutorRepository repositorioAutores;
    private Scanner scanner = new Scanner(System.in);
    private LibroService libroService;

    public MenuPrincipal(LibroRepository repositorioLibros, AutorRepository repositorioAutores){
        this.repositorioLibros = repositorioLibros;
        this.repositorioAutores = repositorioAutores;
        this.libroService = new LibroService(repositorioLibros);
    }


    public void startMenu() {


        while (true) {
            System.out.println("-----------------------");
            mostrarOpciones();

            int input = convertInput(scanner.nextLine());

            if (input == -1) {
                continue;
            }

            switch (input) {
                case 0:
                    System.out.println("Saliendo...");
                    return;
                case 1:
                    buscarPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAnio();
                    break;
                case 5:
                    listarLibrosPorIdiomas();
                    break;
                case 6:
                    top10();
                    break;
                case 7:
                    libroRandom();
                    break;
                case -1:
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }
        }
    }

    public void mostrarOpciones(){
        System.out.println("Elija la opcion a traves de su número:");
        System.out.println("1 - buscar libro por titulo");
        System.out.println("2 - listar libros registrados");
        System.out.println("3 - listar autores registrados");
        System.out.println("4 - listar autores vivos en un determinado año");
        System.out.println("5 - listar libros por idiomas");
        System.out.println("6 - top 10 libros mas descargados");
        System.out.println("7 - volver a leer (Libro aleatorio)");
        System.out.println("0 - salir");
    }

    public int convertInput(String input){
        try{
            int num = Integer.parseInt(input);
            return num;
        }catch (NumberFormatException e){
            System.out.println("Escribe un número valido");
        }
        return -1;

    }

    //OPCIONES

    public void buscarPorTitulo(){
        System.out.println("Ingrese el titulo: ");
        String titulo = scanner.nextLine();
        libroService.obtenerLibro(titulo);
    }

    private void listarLibrosRegistrados() {
        //System.out.println(repositorio.findAll());
        List<Libro> libros = repositorioLibros.findAll();

        if (libros.isEmpty()){
            System.out.println("No hay libros para mostrar:");
        }else{
            libros.stream().forEach(System.out::println);
        }

        libros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados(){
        List<Autor> autores = repositorioAutores.distinctAutores();

        if (autores.isEmpty()){
            System.out.println("No hay autores para mostrar.");
        }else{
            autores.stream().forEach(System.out::println);
        }

    }

    private void listarAutoresVivosPorAnio(){
        while (true){
            System.out.println("0 - Atras");
            System.out.println("Ingresa el año: ");
            String input = scanner.nextLine();

            if (input.equals("0")){
                break;
            }

            int conversion = convertInput(input);

            if (conversion == -1){
                System.out.println("Escribe un número valido.");
                continue;
            }else {
                int rangoAnio = conversion;
                List<Autor> autoresVivos = repositorioAutores.buscarAutoresVivosPorAnio(rangoAnio);

                if (autoresVivos.isEmpty()){
                    System.out.println("No hay autores para mostrar.");
                }else{
                    /*old
                    List<Autor> autoresVivos = autores.stream()
                            .filter(a -> a.getFechaDeNacimiento() != null && a.getFechaDeMuerte() != null)
                            .filter(a -> rangoAnio >= a.getFechaDeNacimiento() && rangoAnio <= a.getFechaDeMuerte())
                            .collect(Collectors.toList());
                    */
                    System.out.println("-------- AUTORES VIVOS EN " + rangoAnio + " --------");
                    autoresVivos.forEach(System.out::println);
                }
            }
        }

    }

    private void listarLibrosPorIdiomas(){
        System.out.println("""
                Ingresa alguno de los siguientes codigos o cualquier otro: 
                es - Español
                en - Inglés
                fr - Francés 
                """);
        String codigo = scanner.nextLine().toLowerCase();

        List<Libro> libros = repositorioLibros.findByIdioma(codigo);

        if (libros.isEmpty()){
            System.out.println("No se encontraron libros en ese idioma. Codigo: " + codigo);
        }else{
            System.out.println("------ Libros en el idioma encontrado ------");
            libros.forEach(System.out::println);
        }
    }

    private void top10(){
        List<Libro> libros = repositorioLibros.findTop10ByOrderByNumeroDescargasDesc();

        if (libros.isEmpty()){
            System.out.println("La lista de libros esta vacia, busca algunos para añadirlos.");
        }else{
            System.out.println("------- TOP 10 LIBROS MAS DESCARGADOS -------");
            libros.forEach(l -> System.out.println("Titulo: " + l.getTitulo() + " - Descargas: " + l.getNumeroDescargas()));
        }

    }

    private void libroRandom(){
        Optional<Libro> libroRandom = repositorioLibros.obtenerLibroRandom();


        if (libroRandom.isPresent()){
            Libro libro = libroRandom.get();
            String autorEtiqueta = libro.getAutores().size() > 1 ? "Autores: " : "Autor: ";
            String autores = libro.getAutores().stream()
                            .map(a -> a.getNombre())
                            .collect(Collectors.joining(" - "));

            System.out.println("------ LIBRO ALEATORIO ------");
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println(autorEtiqueta + autores);
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("-------------------------------");
        }else {
            System.out.println("No hay libros registrados en la base de datos");
        }
    }

}