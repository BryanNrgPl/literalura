# LITERALURA

Literalura es una aplicación de consola que consume la API Gutendex para buscar
libros, una vez buscados se almacenan en una base de datos PostgreSQL 
(ya creada por el usuario) capaz de realizar consultas sobre autores y sus obras.

---

## Estado del proyecto
Completado

---

## Características
* Búsqueda de libros por título a través de API.
* Persistencia de datos en base de datos relacional.
* Filtrado de autores vivos en años específicos.
* Estadísticas (Top 10 libros más descargados).


## Menú de interaccion con el usuario
```text
1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idiomas
6 - Top 10 libros más descargados
7 - Volver a leer (Libro aleatorio)
0 - Salir
```

## TecnologÍas usadas

* **Java 17**
* **Spring Boot**
* **Spring Data JPA**
* **PostgreSQL**
* **Jackson**

## Configuración / Instalación

1. Clonar el repositorio.
2. Configurar las variables de entorno para la DB
```bash
   DB_HOST=localhost
   DB_PORT=5432
   DB_USER=tu_usuario
   DB_PASSWORD=tu_contrasenia
   ```
3. Crear la Base de Datos **literalura**
4. Ejecutar la aplicación

## Dependencias

* Spring Data JPA
* PostgreSQL Driver
* Jackson

## Demostración de uso

<details>
  <summary>✅ Haz clic aquí para desplegar el log de la consola</summary>

  ```text
  Elija la opcion a traves de su número:
  1 - buscar libro por titulo
  2 - listar libros registrados
  3 - listar autores registrados
  4 - listar autores vivos en un determinado año
  5 - listar libros por idiomas
  6 - top 10 libros mas descargados
  0 - salir
  1
  Ingrese el titulo: 
  quijote
  Hibernate: select l1_0.id,l1_0.idioma,l1_0.numero_descargas,l1_0.titulo from libros l1_0 where upper(l1_0.titulo) like upper(?) escape '\'
  Hibernate: select a1_0.libro_id,a1_0.id,a1_0.fecha_de_muerte,a1_0.fecha_de_nacimiento,a1_0.nombre from autores a1_0 where a1_0.libro_id=?

  El libro: Don Quijote ya se encuentra en la base de datos.

  -------LIBRO------ 
  Titulo: Don Quijote
  Autor: Cervantes Saavedra, Miguel de
  Idioma: es
  Número de descargas: 10926
  -----------------------
  
  Elija la opcion a traves de su número:
  4 - listar autores vivos en un determinado año
  ...
  Ingresa el año: 
  1830
  Hibernate: select a1_0.id,a1_0.fecha_de_muerte,a1_0.fecha_de_nacimiento,a1_0.libro_id,a1_0.nombre from autores a1_0 where a1_0.id in (select min(a2_0.id) from autores a2_0 group by a2_0.nombre) and ?>=a1_0.fecha_de_nacimiento and ?<=a1_0.fecha_de_muerte
  
  -------- AUTORES VIVOS EN 1830 --------
  ---------Autor---------
  Nombre: Coleridge, Samuel Taylor
  Fecha de Nacimiento: 1772
  Fecha de Muerte: 1834
  ...
  
  Elija la opcion a traves de su número:
  6 - top 10 libros mas descargados
  
  ------- TOP 10 LIBROS MAS DESCARGADOS -------
  Titulo: Pride and Prejudice - Descargas: 86747
  Titulo: Sense and Sensibility - Descargas: 15762
  Titulo: Don Quijote - Descargas: 10926
  -----------------------
  ```
</details>

## Autor
**Bryan Naragio Polo** - [BryanNrgPl](https://github.com/BryanNrgPl)