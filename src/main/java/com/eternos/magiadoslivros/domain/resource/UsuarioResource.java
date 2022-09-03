package com.eternos.magiadoslivros.domain.resource;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(path = "busca/cpf")
    public Usuario buscarCpf(@RequestParam String cpf ){
        return usuarioService.buscarCpf(cpf);
    }

    @GetMapping(path = "busca/nome")
    public List<Usuario> buscarNome(@RequestParam String nome ){
        return usuarioService.buscarNome(nome);
    }

    @PutMapping(path = "{id}")
    public Usuario update(@Validated @PathVariable Integer id, @RequestBody UsuarioRequest usuarioRequest){
        return usuarioService.atualizarUsuario(id, usuarioRequest);
    }

    @DeleteMapping(path = "{id}")
    public void deletar(@PathVariable Integer id){
        usuarioService.deletar(id);
    }

}
