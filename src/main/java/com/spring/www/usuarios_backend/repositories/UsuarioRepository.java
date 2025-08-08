package com.spring.www.usuarios_backend.repositories;

import com.spring.www.usuarios_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByAlcaldiaIgnoreCase(String alcadia);

    Optional<Usuario> findUsuarioByCorreoIgnoreCase(String correo);

    @Query("""
          SELECT u FROM Usuario u 
          WHERE (:nombre IS NULL OR LOWER(u.nombre) LIKE LOWER (CONCAT('%', :nombre, '%')))
          AND (:apellidoPaterno IS NULL OR LOWER(u.apellidoPaterno) LIKE LOWER (CONCAT('%', :apellidoPaterno, '%')))
          AND (:apellidoMaterno IS NULL OR LOWER(u.apellidoMaterno) LIKE LOWER (CONCAT('%', :apellidoMaterno, '%')))                              
          """)
    Optional<Usuario> findUsuarioByFullName
            (@Param("nombre") String nombre, @Param("apellidoPaterno") String apellidoPaterno, @Param("apellidoMaterno") String apellidoMaterno);
}
