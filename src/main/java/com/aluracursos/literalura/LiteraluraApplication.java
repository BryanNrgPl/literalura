package com.aluracursos.literalura;

import com.aluracursos.literalura.principal.MenuPrincipal;
import com.aluracursos.literalura.services.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    //Repositoriio
    @Autowired
    private LibroRepository repositorio;


	public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {
        MenuPrincipal menu = new MenuPrincipal(repositorio);
        menu.startMenu();
    }
}
