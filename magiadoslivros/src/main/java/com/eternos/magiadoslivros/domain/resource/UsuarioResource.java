package com.eternos.magiadoslivros.domain.resource;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.request.UsuarioRequest;
import com.eternos.magiadoslivros.domain.service.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "usuario")
@AllArgsConstructor
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @PostMapping
    public Usuario salvar(@RequestBody @Valid UsuarioRequest usuarioRequest){
        return usuarioService.salvar(usuarioRequest);
    }

    @GetMapping(path = "todos")
    public List<Usuario> buscar() {
        return usuarioService.buscarTodos();
    }

    @PutMapping(path = "{id}")
    public Usuario update(@PathVariable Integer id, @RequestBody UsuarioRequest usuarioRequest){
        return usuarioService.atualizarUsuario(id, usuarioRequest);
    }

    @PatchMapping(path = "atributo/{id}")
    public Usuario atualizarUnico(@PathVariable @RequestBody Integer id, Object atributo ){
        return usuarioService.atualizarAtributoUsuario(id, atributo);
    }
}
