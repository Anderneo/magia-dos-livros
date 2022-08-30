package com.eternos.magiadoslivros.UsuarioTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.repository.UsuarioRepository;
import com.eternos.magiadoslivros.domain.util.UsuarioUtil;

@SpringBootTest
public class UsuarioUtilTeste {

    @Mock
    private Usuario usuario; 

    @InjectMocks
    private UsuarioUtil usuarioUtil;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void testarBuscarPorId(){
        var obj = usuarioMock();
        when(usuarioRepository.findById(any())).thenReturn(Optional.of(obj));
        var mock = usuarioUtil.buscarId(1);
        assertEquals(mock, obj);
        assertNotNull(mock);
    }
    
    @Test
    void testarChecarExcecaoBuscarPorId(){
        var obj = usuarioMockVazio();
        when(usuarioRepository.findById(any())).thenReturn(obj);

        var excecao = assertThrows(DefaultException.class, () ->{
            usuarioUtil.buscarId(2);
        });          
        assertEquals(HttpStatus.BAD_REQUEST, excecao.httpStatus);
    }
    
    @Test
    void testarChecarExcecaoChecarCpf(){

        var obj = usuarioMockCPF();
        when(usuarioRepository.findByCpf(any())).thenReturn(Optional.of(obj));

        var excecao = assertThrows(DefaultException.class, () ->{
            usuarioUtil.checarCpf("2548512655");
        });          
        assertEquals(HttpStatus.BAD_REQUEST, excecao.httpStatus);
    }

    private Usuario usuarioMock() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setCpf("2342342342");         
        return usuario;
    }

    private Usuario usuarioMockCPF() {
        Usuario usuario = new Usuario();        
        usuario.setCpf("2342342342");         
        return usuario;
    }
    private Optional<Usuario> usuarioMockVazio() {
        Optional<Usuario> usuario = Optional.empty();
        return usuario;
    }
}
