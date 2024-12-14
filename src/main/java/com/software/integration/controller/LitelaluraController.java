package com.software.integration.controller;

import com.software.integration.model.Author;
import com.software.integration.model.Book;
import com.software.integration.repository.AuthorRepository;
import com.software.integration.repository.BookRepository;
import com.software.integration.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class LitelaluraController implements CommandLineRunner {
    private final GutendexService gutendexService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public LitelaluraController(GutendexService gutendexService,
                           BookRepository bookRepository,
                           AuthorRepository authorRepository) {
        this.gutendexService = gutendexService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    buscarLibro(scanner);
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos(scanner);
                    break;
                case 5:
                    listarLiborsPorLenguaje(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n" +
                "********************--- Catalogo LitelAlura ---********************");
        System.out.println("1. Buscar Libro");
        System.out.println("2. Listar Libros");
        System.out.println("3. Listar Autores ");
        System.out.println("4. Lista de Autores Vivos");
        System.out.println("5. Lista de libros por idioma");
        System.out.println("0. Salir");
        System.out.print("Escoge una opcion: ");
    }

    private void buscarLibro(Scanner scanner) {
        System.out.print("Escribe el Titulo del Libro: ");
        String title = scanner.nextLine();

        Optional<Book> book = gutendexService.searchBookByTitle(title);
        book.ifPresentOrElse(
                b -> {
                    bookRepository.save(b);
                    System.out.println("Libro Guardado: " + b);
                },
                () -> System.out.println("Libro no encontrado.")
        );
    }

    private void listarLibros() {
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    private void listarAutoresVivos(Scanner scanner) {
        System.out.print("Introduzca el año para consultar los autores vivos: ");
        int year = scanner.nextInt();

        List<Author> livingAuthors = authorRepository.findAll().stream()
                .filter(author -> author.getBirthYear() <= year &&
                        (author.getDeathYear() == null || author.getDeathYear() > year))
                .toList();

        if (livingAuthors.isEmpty()) {
            System.out.println("\n" +
                    "**************************************\n" +
                    "No hay autores vivos en el año: " + year);
        } else {
            livingAuthors.forEach(System.out::println);
        }
    }

    private void listarLiborsPorLenguaje(Scanner scanner) {
        System.out.print("Ingresa en codigo tu lenguaje (e.g., en, es): ");
        String language = scanner.nextLine();

        List<Book> books = bookRepository.findByLanguages(language);
        books.forEach(System.out::println);
    }
}