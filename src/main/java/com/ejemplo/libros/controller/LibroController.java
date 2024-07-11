package com.ejemplo.libros.controller;

import com.ejemplo.libros.model.Libro;
import com.ejemplo.libros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Libro> obtenerPorId(@PathVariable Long id) {
        return libroRepository.findById(id);
    }

    @PostMapping
    public Libro agregarLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    @DeleteMapping("/{id}")
    public void borrarLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }

    // Inicializar los libros en el constructor
    @Autowired
    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
        inicializarLibros();
    }

    private void inicializarLibros() {
        libroRepository.save(new Libro(null, "Cien Años de Soledad", "Gabriel García Márquez", "Novela", LocalDate.of(1967, 6, 5)));
        libroRepository.save(new Libro(null, "1984", "George Orwell", "Distopía", LocalDate.of(1949, 6, 8)));
        libroRepository.save(new Libro(null, "Don Quijote de la Mancha", "Miguel de Cervantes", "Novela", LocalDate.of(1605, 1, 16)));
        libroRepository.save(new Libro(null, "El Gran Gatsby", "F. Scott Fitzgerald", "Novela", LocalDate.of(1925, 4, 10)));
        libroRepository.save(new Libro(null, "Orgullo y Prejuicio", "Jane Austen", "Novela", LocalDate.of(1813, 1, 28)));
    }
}
