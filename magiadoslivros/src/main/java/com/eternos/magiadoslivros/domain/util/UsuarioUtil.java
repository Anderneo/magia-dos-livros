package com.eternos.magiadoslivros.domain.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UsuarioUtil {

    private final UsuarioRepository usuarioRepository;

    public Usuario buscarId(Integer id){

        return usuarioRepository.findById(id)
            .orElseThrow(new DefaultException(
            HttpStatus.BAD_REQUEST,"O registro informado não existe!!"));

    }

    public void checarUsuario(Usuario usuario){

        if (usuario.getPerfil().toString().compareToIgnoreCase("administrador") != 0) 
                throw new DefaultException(HttpStatus.FORBIDDEN,
                 "Apenas o administrador pode cancelar um pedido!!");

    }

    public void checarConstraintUsuario(Usuario usuario){

        if (usuarioRepository.findByRg(usuario.getRg()).isPresent())
            throw new DefaultException(HttpStatus.FOUND, 
                                            "Já existe um registro com RG: " 
                                            + usuario.getRg());
                            
        if (usuarioRepository.findByCpf(usuario.getCpf()).isPresent())
            throw new DefaultException(HttpStatus.FOUND, 
                                            "Já existe um registro com CPF: " 
                                            + usuario.getCpf());

    }
    
}
