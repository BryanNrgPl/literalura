package com.aluracursos.literalura.services;

import com.aluracursos.literalura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    //@Query("SELECT DISTINCT a FROM Autor a")
    @Query("SELECT a FROM Autor a WHERE a.id IN (SELECT MIN(a2.id) FROM Autor a2 GROUP BY a2.nombre)")
    List<Autor> distinctAutores();

}
