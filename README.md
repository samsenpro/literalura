# Literalura

Literalura desafío Literalura

## Características principales

1. **Buscar libros por título**: Permite buscar libros por título en la base de datos de libros, y si el libro no está registrado, lo agrega automáticamente desde la API Gutendex.
2. **Listar todos los libros**: Muestra una lista de todos los libros registrados en la base de datos.
3. **Listar todos los autores**: Muestra una lista de todos los autores registrados en la base de datos.
4. **Listar autores vivos**: Permite consultar los autores vivos en un año específico, basándose en la fecha de nacimiento y fallecimiento de los autores.
5. **Listar libros por idioma**: Permite listar los libros según el idioma especificado por el usuario.

## Estructura del Proyecto

El proyecto está estructurado en varias capas que incluyen controladores, modelos, repositorios y servicios. A continuación se describe cada componente del proyecto:

### 1. **Controlador (`LitelaluraController`)**
El controlador maneja las interacciones con el usuario y ejecuta las opciones seleccionadas en el menú. Utiliza los servicios `GutendexService`, `BookRepository` y `AuthorRepository` para realizar las operaciones correspondientes.

### 2. **Modelos (`Book` y `Author`)**
- **`Book`**: Representa los libros en el sistema, con información sobre el título, el autor, el idioma y el número de descargas.
- **`Author`**: Representa a los autores, con información sobre su nombre, año de nacimiento, año de fallecimiento (si corresponde) y los libros asociados.

### 3. **Repositorios (`BookRepository` y `AuthorRepository`)**
Los repositorios gestionan las operaciones CRUD para los modelos `Book` y `Author`, interactuando con la base de datos.

### 4. **Servicio (`GutendexService`)**
El servicio `GutendexService` se encarga de realizar solicitudes a la API de Gutendex para obtener datos sobre los libros, basándose en un título dado. También se asegura de procesar la información de la respuesta JSON de la API y crear objetos `Book` y `Author` a partir de esos datos.

## Requisitos

- **Java 17 o superior**: Este proyecto está desarrollado con Spring Boot y requiere una versión compatible de Java.
- **Spring Boot**: El proyecto está basado en el marco de trabajo Spring Boot para facilitar la creación de aplicaciones Java basadas en microservicios.
- **Base de datos**: El proyecto utiliza JPA y Postgres para interactuar con una base de datos, donde se almacenan los libros y autores.

# Creado y desarrollado por Samuel Martinez  
[LinkedIn: Samuel Martinez](https://www.linkedin.com/in/samuel-martinez-beleno/)
