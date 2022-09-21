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
import com.eternos.magiadoslivros.domain.model.enums.Perfil;
import com.eternos.magiadoslivros.domain.repository.UsuarioRepository;
import com.eternos.magiadoslivros.domain.util.UsuarioUtil;

@SpringBootTest
public class UsuarioUtilTest {

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
    void testarChecarExcecaoConstraintUsuarioRg(){
        var obj = usuarioMockConstraintRg();
        when(usuarioRepository.findByCpf(any())).thenReturn(Optional.of(obj));
        when(usuarioRepository.findByRg(any())).thenReturn(Optional.of(obj));

        var excecao = assertThrows(DefaultException.class, () ->{
            usuarioUtil.checarConstraintUsuario(obj);
        });          
        assertEquals(HttpStatus.FOUND, excecao.httpStatus);
    }

    @Test
    void testarChecarExcecaoConstraintUsuarioCpf(){
        var obj = usuarioMockVazio();
        var objCpf = usuarioMockCPF();        
        when(usuarioRepository.findByRg(any())).thenReturn(obj);
        when(usuarioRepository.findByCpf(any())).thenReturn(Optional.of(objCpf));

        var excecao = assertThrows(DefaultException.class, () ->{
            usuarioUtil.checarConstraintUsuario(objCpf);
        });          
        assertEquals(HttpStatus.FOUND, excecao.httpStatus);
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

    @Test
    void testarChecarExcecaoUsuario(){

        var obj = usuarioMock();
        obj.setPerfil(Perfil.USUARIO);
        //when(usuario.getPerfil().toString().compareToIgnoreCase(any())).thenReturn(1);

        var excecao = assertThrows(DefaultException.class, () ->{
            usuarioUtil.checarUsuario(obj);
        });          
        assertEquals(HttpStatus.FORBIDDEN, excecao.httpStatus);
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

    private Usuario usuarioMockConstraintRg() {
        Usuario usuario =  new Usuario();  
        usuario.setRg("4555555544");
        usuario.setCpf("2342342342");  
        return usuario;
    }

}
