package com.eternos.magiadoslivros.UsuarioTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.assembler.UsuarioAssembler;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.model.enums.Genero;
import com.eternos.magiadoslivros.domain.model.enums.Perfil;
import com.eternos.magiadoslivros.domain.request.UsuarioRequest;
import com.eternos.magiadoslivros.domain.util.UsuarioUtil;
@SpringBootTest
public class UsuarioAssemblerTest {

    @InjectMocks
    private UsuarioAssembler usuarioAssembler;

    @Mock
    private UsuarioUtil usuarioUtil;

    @Mock
    private UsuarioRequest usuarioRequest;

    @Mock
    private Usuario usuario;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testarToModel(){
        var objUsuario = usuarioMock();
        var objUsuarioRequest = usuarioRequestMock();
        
        when(modelMapper.map(any(), any())).thenReturn(objUsuario);
        
        var mock = usuarioAssembler.toModel(objUsuarioRequest);
      
        assertNotNull(mock);
        assertEquals(objUsuario, mock);
        assertEquals(objUsuario.getClass(), mock.getClass());
        
    }
    
    private UsuarioRequest usuarioRequestMock() {       
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setNome("Camila Lima");
        usuarioRequest.setEndereco("Rua Moema, 342");
        usuarioRequest.setEmail( "camila.lima@hotmail.com");
        usuarioRequest.setTelefone( "342342342");
        usuarioRequest.setRg("22342342342");
        usuarioRequest.setCpf("345232342224");       
        usuarioRequest.setDataDeNas(LocalDate.of(1980,01,01));
        usuarioRequest.setGenero(Genero.NAOBINARIO);
        usuarioRequest.setObservacao( "sdfsdfsdf");      
        usuarioRequest.setDataDeCadastro(LocalDate.of(1990,3,15));
        usuarioRequest.setPerfil(Perfil.ADMINISTRADOR);
        return usuarioRequest;
    }
    
    private Usuario usuarioMock() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Camila Lima");
        usuario.setEndereco("Rua Moema, 342");
        usuario.setEmail( "camila.lima@hotmail.com");
        usuario.setTelefone( "342342342");
        usuario.setRg("22342342342");
        usuario.setCpf("345232342224");       
        usuario.setDataDeNas(LocalDate.of(1980,01,01));
        usuario.setGenero(Genero.NAOBINARIO);
        usuario.setObservacao( "sdfsdfsdf");      
        usuario.setDataDeCadastro(LocalDate.of(1990,3,15));
        usuario.setPerfil(Perfil.ADMINISTRADOR);
        return usuario;
    }
    
}
