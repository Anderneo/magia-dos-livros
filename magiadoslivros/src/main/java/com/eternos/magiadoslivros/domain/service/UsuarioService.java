package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.assembler.UsuarioAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.repository.UsuarioRepository;
import com.eternos.magiadoslivros.domain.request.UsuarioRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final UsuarioAssembler usuarioAssembler;


    public Usuario salvar(UsuarioRequest usuarioRequest){

        return usuarioRepository.save(usuarioAssembler.toModel(usuarioRequest));

    }

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarId(Integer id){

        return usuarioRepository.findById(id)
            .orElseThrow(new DefaultException(
            HttpStatus.BAD_REQUEST,"O registro informado não existe!!"));

    }

    public Usuario atualizarUsuario(Integer id, UsuarioRequest usuarioRequest){

        Usuario usuario = buscarId(id);

        Usuario request = usuarioAssembler.toModel(usuarioRequest);
     
        BeanUtils.copyProperties(request, usuario, "id");

        return usuarioRepository.save(usuario);

    }

    public Usuario atualizarAtributoUsuario(Integer id, Object atributo){
        var usuario = buscarId(id);

        return usuario;
    }

}
