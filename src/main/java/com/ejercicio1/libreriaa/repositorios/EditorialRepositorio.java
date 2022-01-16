
package com.ejercicio1.libreriaa.repositorios;

import com.ejercicio1.libreriaa.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial,String>{

@Query("SELECT e FROM Editorial e WHERE e.id = :id")
public List<Editorial> buscarEditorialPorId(@Param("id") String id);

    
@Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
public Editorial buscarEditorialPorNombre (@Param("nombre") String nombre);
}
