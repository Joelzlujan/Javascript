
package com.ejercicio1.libreriaa.repositorios;

import com.ejercicio1.libreriaa.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,String> {
    
    @Query("SELECT c FROM Cliente c WHERE c.documento = :documento")
    public Cliente buscarPorDocumento(@Param("documento")Long documento);
    
    @Query("SELECT c FROM Cliente c WHERE c.id = :id")
    public Cliente buscarPorId(@Param("id")String id);
}
