package com.ejercicio1.libreriaa.servicio;

import com.ejercicio1.libreriaa.entidades.Autor;
import com.ejercicio1.libreriaa.entidades.Editorial;
import com.ejercicio1.libreriaa.entidades.Libro;
import com.ejercicio1.libreriaa.errores.ErrorServicio;
import com.ejercicio1.libreriaa.repositorios.LibroRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    @Autowired
    LibroRepositorio lr;

    @Autowired
    AutorServicio as;

    @Autowired
    EditorialServicio es;

    @Transactional
    public Libro crearLibro(String titulo, Long ISBN, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, String autor, String editorial) throws ErrorServicio {
        Libro libro = new Libro();
        validarLibro(titulo, anio, ejemplares);
        libro.setTitulo(titulo);
        libro.setISBN(ISBN);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplaresRestantes);      
        Autor a = as.buscarAutorPorNombre(autor);
        if (a == null) {
            a = as.CrearAutor(autor);
            libro.setAutor(a);
        } else {
            libro.setAutor(a);
        }
        Editorial e = es.buscarEditorialPorNombre(editorial);
        if (e == null) {
            e = es.crearEditorial(editorial);
            libro.setEditorial(e);
        } else {
            libro.setEditorial(e);
        }
        libro.setAlta(Boolean.TRUE);
        return lr.save(libro);
    }

    @Transactional
    public void darBajaLibro(String id) throws ErrorServicio {
        Libro l = buscarLibroPorId(id);
        if (l != null) {
            l.setAlta(Boolean.FALSE);
            lr.save(l);
        } else {
            throw new ErrorServicio("No se encontro el libro");
        }
    }

    @Transactional
    public void darAltaLibro(String id) throws ErrorServicio {
        Libro l = buscarLibroPorId(id);
        if (l != null) {
            l.setAlta(Boolean.TRUE);
            lr.save(l);
        } else {
            throw new ErrorServicio("No se encontro el libro");
        }
    }

    @Transactional
    public Libro modificarLibro(String id, String titulo, Long ISBN, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, String autor, String editorial) throws ErrorServicio {
        Libro l = buscarLibroPorId(id);
        if (l != null) {
            validarLibro(titulo, anio, ejemplares);
            l.setTitulo(titulo);
            l.setISBN(ISBN);
            l.setAnio(anio);
            l.setEjemplares(ejemplares);
            l.setEjemplaresPrestados(ejemplaresPrestados);
            l.setEjemplaresRestantes(ejemplaresRestantes);
            Autor a = as.buscarAutorPorNombre(autor);
            if (a == null) {
                a = as.CrearAutor(autor);
                l.setAutor(a);
            } else {
                l.setAutor(a);
            }
            Editorial e = es.buscarEditorialPorNombre(editorial);
            if (e == null) {
                e = es.crearEditorial(editorial);
                l.setEditorial(e);
            } else {
                l.setEditorial(e);
            }
            l.setAlta(Boolean.TRUE);
            return lr.save(l);
        } else {
            throw new ErrorServicio("El libro ingresado no ha sido encontrado");
        }
    }

    @Transactional(readOnly = true)
    public Libro buscarLibroPorId(String id) {
        return lr.buscarLibroPorId(id);
    }
    @Transactional(readOnly = true)
    public Libro buscarLibroPorTitulo(String titulo)throws ErrorServicio{
        return lr.buscarLibroPorTitulo(titulo);
    }

    @Transactional(readOnly = true)
    public List<Libro> cargarListaLibros() throws ErrorServicio {
        return lr.findAll();
    }

    @Transactional(readOnly = true)
    public List<Libro> buscarLibrosPorAutor(String autor) throws ErrorServicio {
        return lr.buscarLibrosPorAutor(autor);
    }

    @Transactional(readOnly = true)
    public List<Libro> buscarLibrosPorEditorial(String editorial) throws ErrorServicio {
        return lr.buscarLibrosPorEditorial(editorial);
    }

    public void validarLibro(String titulo, Integer anio, Integer ejemplares) throws ErrorServicio {
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo del libro no puede ser nulo");
        }
        if (anio == null || anio < 0) {
            throw new ErrorServicio("El año no puede ser nulo o menor q 0");
        }
        if (ejemplares == null || ejemplares <= 0) {
            throw new ErrorServicio("Tiene que existir algun ejemplar");
        }
    }

}
