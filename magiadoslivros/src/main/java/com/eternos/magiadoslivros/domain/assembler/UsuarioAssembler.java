package com.eternos.magiadoslivros.domain.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.request.UsuarioRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UsuarioAssembler {
    private final ModelMapper modelMapper;

    public Usuario toModel(UsuarioRequest usuarioRequest){
        return modelMapper.map(usuarioRequest, Usuario.class);
    }
}
