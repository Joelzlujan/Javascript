
package com.ejercicio1.libreriaa.repositorios;

import com.ejercicio1.libreriaa.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository <Libro,String>{
    
    @Query("SELECT l FROM Libro l")
    public List<Libro> cargarListaLibros(@Param("id") String id);
    
    @Query("SELECT l FROM Libro l WHERE l.id = :id")
    public Libro buscarLibroPorId(@Param("id") String id);
    
    @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")
    public List<Libro> buscarLibrosPorAutor(@Param("nombre") String nombre);
    
    @Query("SELECT l FROM Libro l WHERE l.editorial.nombre = :nombre")
    public List<Libro> buscarLibrosPorEditorial(@Param("nombre") String nombre);
    
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro buscarLibroPorTitulo(@Param("titulo") String titulo);
}
