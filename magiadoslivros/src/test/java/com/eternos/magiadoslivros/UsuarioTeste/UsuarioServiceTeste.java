package com.eternos.magiadoslivros.UsuarioTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.eternos.magiadoslivros.domain.assembler.UsuarioAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.model.enums.Genero;
import com.eternos.magiadoslivros.domain.model.enums.Perfil;
import com.eternos.magiadoslivros.domain.repository.UsuarioRepository;
import com.eternos.magiadoslivros.domain.request.UsuarioRequest;
import com.eternos.magiadoslivros.domain.service.UsuarioService;
import com.eternos.magiadoslivros.domain.util.UsuarioUtil;
 

@SpringBootTest
public class UsuarioServiceTeste {
    
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioUtil usuarioUtil;
 
    @Mock    
    private UsuarioAssembler usuarioAssembler;

    @Mock
    private UsuarioRequest usuarioRequest;

    @Test
    void testarBuscarTodos(){
        var obj = listaUsuarioMocada();
        when(usuarioRepository.findAll()).thenReturn(obj);
        var mock = usuarioService.buscarTodos();
        assertEquals(mock, obj);
        assertNotNull(mock); 
    }

    @Test
    void testarSalvarUsuario(){
        var obj = usuarioMock();
        when(usuarioUtil.buscarId(any())).thenReturn(obj);
        when(usuarioAssembler.toModel(any())).thenReturn(obj);
        when(usuarioRepository.save(any())).thenReturn(obj);
        var mock = usuarioService.salvar(usuarioRequest);
        assertEquals(mock, obj); 
        assertNotNull(mock);         
    }

    @Test
    void testarBuscarPorNome(){
        var obj = listaUsuarioMockPorNome();
        when(usuarioRepository.findByNomeContainingIgnoreCase(any())).thenReturn(obj);
        var mock = usuarioService.buscarNome("Ana Maria Roncada");
        assertEquals(mock, obj);
        assertNotNull(mock); 
    }

    @Test
    void testarChecarExcecaBuscarPorNome(){
        var obj = listaUsuarioMocada();
        when(usuarioRepository.findByNomeContainingIgnoreCase(any())).thenReturn(obj);

        var excecao = assertThrows(DefaultException.class, () ->{
            usuarioService.buscarNome("Ana Maria Roncada");
        });
            
        assertEquals(HttpStatus.NOT_FOUND, excecao.httpStatus);

    }

    @Test
    void testarBuscarPorCpf(){
        var obj = usuarioMock();      
       when(usuarioRepository.findByCpf(any())).thenReturn(Optional.of(obj));
  
        var mock = usuarioService.buscarCpf("4565624525");
        assertEquals(mock, obj);
        assertNotNull(mock); 
        assertEquals(obj.getClass(), mock.getClass());
    }
    
    @Test
    void testarChecarExcecaoBuscarPorCpf(){
        when(usuarioRepository.findByCpf(any())).thenReturn(null);

        var excecao = assertThrows(DefaultException.class, () ->{
            usuarioService.buscarCpf("27845909");
        });
            
        assertEquals(HttpStatus.NOT_FOUND, excecao.httpStatus);

    }

    @Test
    void testarDeletarUsuario(){
        var obj = usuarioMock();
        when(usuarioUtil.buscarId(any())).thenReturn(obj);
        doNothing().when(usuarioRepository).deleteById(any());
        var excecao = assertThrows(DefaultException.class, () ->{
            usuarioService.deletar(1);
        });
        verify(usuarioRepository, times(1)).delete(any());
        assertEquals(HttpStatus.ACCEPTED, excecao.httpStatus);
    }

    @Test
    void testarAtualizarUsuario(){
        var obj = usuarioMockID();    
        var objAtualizado = usuarioMockAtualizado();    
        var requestMock =  usuarioMockRequest() ;
        when(usuarioUtil.buscarId(any())).thenReturn(obj);       
        when(usuarioRepository.save(any())).thenReturn(objAtualizado);

        var mock = usuarioService.atualizarUsuario(1, requestMock);
      
        assertNotNull(mock);
        assertEquals(Usuario.class, mock.getClass());       
    }


    private Usuario usuarioMock() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
      // usuario.setNome("Maira Almeida");
     //  usuario.setEndereco("Rua Girassol, 353");
      //  usuario.setEmail("Teste_Teste");
      //  usuario.setTelefone("345345345");
     //  usuario.setRg("523523252522");
        usuario.setCpf("778.992.342-12");
      // usuario.setDataDeNas(LocalDate.of(1980,01,01));
      //  usuario.setGenero(Genero.AGENERO);
      //  usuario.setDataDeCadastro(LocalDate.of(2020,05,05));
       // usuario.setPerfil(Perfil.ADMINISTRADOR);
        //usuario.setObservacao("Teste Camila");
        return usuario;
    }

    private Usuario usuarioMockAtualizado() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Maira Almeida");
        usuario.setEndereco("Rua Girassol, 353");
        usuario.setEmail("Teste_Teste");
        usuario.setTelefone("345345345");
        usuario.setRg("523523252522");
        usuario.setCpf("778.992.342-12");
        usuario.setDataDeNas(LocalDate.of(1980,01,01));
        usuario.setGenero(Genero.AGENERO);
         usuario.setDataDeCadastro(LocalDate.of(2020,05,05));
        usuario.setPerfil(Perfil.ADMINISTRADOR);
         usuario.setObservacao("Teste Camila");
        return usuario;
    }

    private UsuarioRequest usuarioMockRequest() {
        UsuarioRequest usuariorequest = new UsuarioRequest();     
          
        usuariorequest.setNome("Aline Soares");
        usuariorequest.setEndereco("Rua Doutor Candido Espinheira, 353");
        usuariorequest.setEmail("aline_soares@gmail.com");
        usuariorequest.setTelefone("11133323");
        usuariorequest.setRg("523523252522");
        usuariorequest.setCpf("778.992.342-12");
        usuariorequest.setDataDeNas(LocalDate.of(1980,01,01));
        usuariorequest.setGenero(Genero.AGENERO);
        usuariorequest.setDataDeCadastro(LocalDate.of(2020,05,05));
        usuariorequest.setPerfil(Perfil.ADMINISTRADOR);
        usuariorequest.setObservacao("Teste Camila");
        return usuariorequest;
    }

      private List<Usuario> listaUsuarioMockPorNome() {
        List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Renato Barcelos");
        usuarios.add(usuario);

        return usuarios;
    }

    private List<Usuario> listaUsuarioMocada(){

        List<Usuario> usuarios = new ArrayList<>();

        return usuarios;
    } 

    private Usuario usuarioMockID() {
        Usuario usuario = new Usuario();
        usuario.setId(1);      
        return usuario;
    }
   
}
