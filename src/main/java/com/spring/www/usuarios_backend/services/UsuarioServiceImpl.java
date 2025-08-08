package com.spring.www.usuarios_backend.services;

import com.spring.www.usuarios_backend.entity.Usuario;
import com.spring.www.usuarios_backend.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Usuario> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorCorreo(String correo) {
        return repository.findUsuarioByCorreoIgnoreCase(correo);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorAlcaldia(String alcaldia) {
        return repository.findUsuarioByAlcaldiaIgnoreCase(alcaldia);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorNombre(String nombre, String apellidoPaterno, String apellidoMaterno) {
        return repository.findUsuarioByFullName(nombre, apellidoPaterno, apellidoMaterno);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
         repository.deleteById(id);
    }
}
