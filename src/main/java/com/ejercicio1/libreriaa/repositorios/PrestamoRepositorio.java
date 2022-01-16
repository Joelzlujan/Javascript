
package com.ejercicio1.libreriaa.repositorios;

import com.ejercicio1.libreriaa.entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo,String> {
    
    @Query("SELECT p FROM Prestamo p WHERE p.id = :id")
    public Prestamo buscarPorId(@Param("id") String id);
    
    
}
