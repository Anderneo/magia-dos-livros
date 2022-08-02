package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.assembler.UsuarioAssembler;
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
}
