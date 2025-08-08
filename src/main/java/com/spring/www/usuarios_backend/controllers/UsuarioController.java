package com.spring.www.usuarios_backend.controllers;

import com.spring.www.usuarios_backend.entity.Usuario;
import com.spring.www.usuarios_backend.exceptions.HandlerHttpExceptions;
import com.spring.www.usuarios_backend.services.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl service;

    public UsuarioController(UsuarioServiceImpl service) {
        this.service = service;
    }

    @GetMapping("listar")
    public ResponseEntity<?> listar (Pageable pageable){
        Page<Usuario> listaUsuarios = service.listar(pageable);
        if (listaUsuarios.isEmpty()){
            return HandlerHttpExceptions.notFound("error 404 not found : la lista no tiene elementos");
        } else {
            return ResponseEntity.ok(listaUsuarios.getContent()); // 200 OK
        }
    }

    @GetMapping("/buscar/id/{id}")
    public ResponseEntity<?> buscarPorId (@PathVariable Long id){
        Optional<Usuario> usuarioId = service.buscarUsuarioPorId(id);
        if (usuarioId.isEmpty()){
            return HandlerHttpExceptions.notFound("error 404 not found : usuario no encontrado");
        } else {
            return ResponseEntity.ok(usuarioId.get()); // 200 OK
        }
    }

    @GetMapping("/buscar/correo")
    public ResponseEntity<?> buscarPorCorreo (@RequestParam String correo){
        Optional<Usuario> usuarioCorreo = service.buscarUsuarioPorCorreo(correo);
        if (usuarioCorreo.isEmpty()){
            return HandlerHttpExceptions.notFound("error 404 not found : correo no encontrado");
        } else {
            return ResponseEntity.ok(usuarioCorreo.get()); // 200 OK
        }
    }

    @GetMapping("/buscar/alcladia")
    public ResponseEntity<?> buscarPorAlcaldia (@RequestParam String alcaldia){
        Optional<Usuario> usuarioAlcaldia = service.buscarUsuarioPorAlcaldia(alcaldia);
        if (usuarioAlcaldia.isEmpty()){
            return HandlerHttpExceptions.notFound("error 404 not found");
        } else {
            return ResponseEntity.ok(usuarioAlcaldia.get()); // 200 OK
        }
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<?> buscarPorNombre (@RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno ){
        Optional<Usuario> usuarioNombre = service.buscarUsuarioPorNombre(nombre, apellidoPaterno, apellidoMaterno);
        if (usuarioNombre.isEmpty()){
            return HandlerHttpExceptions.notFound("error 404 not found");
        } else {
            return ResponseEntity.ok(usuarioNombre.get()); // 200 OK
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario (@Valid @RequestBody Usuario usuario, BindingResult result){
        if (result.hasErrors()){
            Map<String, Object> errors = new HashMap<>();
            result.getFieldErrors().forEach(e -> {
                errors.put(e.getField(), "el campo "+e.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        } else {
            try {
                service.crearUsuario(usuario);
                return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message","201 created")); // 201 created
            } catch (Exception e){
                return HandlerHttpExceptions.badRequest("error 400 bad request"); // 400 bad request
            }
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editarUsuarios (@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            Map<String, Object> errors = new HashMap<>();
            result.getFieldErrors().forEach(e -> {
                errors.put(e.getField()," el campo"+e.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        } else {
            Optional<Usuario> usuarioId = service.buscarUsuarioPorId(id);
                Usuario newUsuario = usuarioId.get();

            if (usuarioId.isEmpty()){
                    return HandlerHttpExceptions.notFound("error 404 not found");
            } else {
                newUsuario.setNombre(usuario.getNombre());
                newUsuario.setApellidoPaterno(usuario.getApellidoPaterno());
                newUsuario.setApellidoMaterno(usuario.getApellidoMaterno());
                newUsuario.setCorreo(usuario.getCorreo());
                newUsuario.setNumero(usuario.getNumero());
                newUsuario.setAlcaldia(usuario.getAlcaldia());

                Usuario usuarioEdited = newUsuario;

                try {
                    service.crearUsuario(usuarioEdited);
                    return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","200 OK"));
                } catch (Exception e){
                    return HandlerHttpExceptions.badRequest("error 400 bad request");
                }
            }
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario (@PathVariable Long id){
        Optional<Usuario> usuarioId = service.buscarUsuarioPorId(id);
        if(usuarioId.isEmpty()){
            return HandlerHttpExceptions.notFound("error 404 not found");
        } else {
            try {
                service.eliminarUsuario(id);
                return ResponseEntity.ok().body(Map.of("message","usuario borrado con exito"));
            } catch (Exception e){
                return HandlerHttpExceptions.badRequest("error 400 bad request");
            }
        }
    }

}
