package com.eternos.magiadoslivros.domain.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Usuario;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UsuarioUtil {

    public void checarUsuario(Usuario usuario){

        if (usuario.getPerfil().toString().compareToIgnoreCase("administrador") != 0) 
                throw new DefaultException(HttpStatus.FORBIDDEN,
                 "Apenas o administrador pode cancelar um pedido!!");

    }
    
}
