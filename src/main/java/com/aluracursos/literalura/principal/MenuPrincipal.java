package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.services.LibroRepository;
import com.aluracursos.literalura.services.LibroService;

import java.util.Scanner;

public class MenuPrincipal {
    private boolean isNum = false;
    private LibroRepository repositorio;
    private Scanner scanner = new Scanner(System.in);
    private LibroService libroService;

    public MenuPrincipal(LibroRepository repositorio){
        this.repositorio = repositorio;
        this.libroService = new LibroService(repositorio);
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
                    System.out.println("Elegiste 2");
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
        repositorio.findByTitulo(titulo);
    }
}