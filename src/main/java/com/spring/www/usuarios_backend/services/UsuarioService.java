package com.spring.www.usuarios_backend.services;

import com.spring.www.usuarios_backend.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioService {

    Page<Usuario> listar (Pageable pageable);

    Optional<Usuario> buscarUsuarioPorId(Long id);

    Optional<Usuario> buscarUsuarioPorCorreo (String correo);

    Optional<Usuario> buscarUsuarioPorAlcaldia (String alcaldia);

    Optional<Usuario> buscarUsuarioPorNombre (String nombre, String apellidoPaterno, String apellidoMaterno);

    Usuario crearUsuario (Usuario usuario);

    void eliminarUsuario (Long id);
}
