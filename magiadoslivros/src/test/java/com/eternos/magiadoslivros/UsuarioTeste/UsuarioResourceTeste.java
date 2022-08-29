package com.eternos.magiadoslivros.UsuarioTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.model.enums.Genero;
import com.eternos.magiadoslivros.domain.model.enums.Perfil;
import com.eternos.magiadoslivros.domain.request.UsuarioRequest;
import com.eternos.magiadoslivros.domain.resource.UsuarioResource;
import com.eternos.magiadoslivros.domain.service.UsuarioService;

@SpringBootTest
public class UsuarioResourceTeste {
    private static final Integer id = 1;
   
    @InjectMocks
    private UsuarioResource usuarioResource;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRequest usuarioRequest;

    @Mock
    private Usuario usuario ;

    @Test
    void testarSalvarUsuario() {
        var obj = usuarioMock();
        when(usuarioService.salvar(any())).thenReturn(obj);

        var mock = usuarioResource.salvar(usuarioRequest);

        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(Usuario.class, mock.getClass());
    }
   
    @Test
    void testarBuscarTodosUsuarios(){
        var obj = listaUsuarioMocada();
        when(usuarioService.buscarTodos()).thenReturn(obj);

        var mock = usuarioResource.buscar();
        
        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getClass());
    }

    @Test
    void testarBuscarPorNome(){
        var obj = listaUsuarioMockPorNome();
        when(usuarioService.buscarNome(any())).thenReturn(obj);

        var mock = usuarioResource.buscarNome("Camila Lima");
        
        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getClass());
        
    }
     

    @Test
    void testarBuscarCpf(){
        var obj = usuarioMock();
        when(usuarioService.buscarCpf(any())).thenReturn(obj);

        var mock = usuarioResource.buscarCpf("23667244404");
        
        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(Usuario.class, mock.getClass());
    }

    @Test
    void testarDeletarUsuario(){
        doNothing().when(usuarioService).deletar(any());
        usuarioResource.deletar(1);

        verify(usuarioService, times(1)).deletar(any());
    }
    
    @Test
    void testarUpdateUsuario(){
        var obj = usuarioMock();
        when(usuarioService.atualizarUsuario(any(), any())).thenReturn(obj);
        var mock = usuarioResource.update(id, usuarioRequest);

        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(Usuario.class, mock.getClass());
    }
    
    private Usuario usuarioMock() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
      //  usuario.setNome("Camila Lima");
      //  usuario.setEndereco("Rua Moema, 342");
      //  usuario.setEmail( "camila.lima@hotmail.com");
      //  usuario.setTelefone( "342342342");
      //  usuario.setRg("22342342342");
        usuario.setCpf("345232342224");       
     //   usuario.setDataDeNas(LocalDate.of(1980,01,01));
      //  usuario.setGenero(Genero.NAOBINARIO);
      //  usuario.setObservacao( "sdfsdfsdf");      
      //  usuario.setDataDeCadastro(LocalDate.of(1990,3,15));
      //  usuario.setPerfil(Perfil.ADMINISTRADOR);
        return usuario;
    }

    private List<Usuario> listaUsuarioMocada(){

        List<Usuario> usuarios = new ArrayList<>();

        return usuarios;
    }

    private List<Usuario> listaUsuarioMockPorNome() {
        List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario = new Usuario();
        usuario.setNome("Adriana Moreira");
        usuario.setEndereco("Rua Teste, 653");
        usuario.setEmail("adriamamoreira@gmail.com");
        usuario.setTelefone("24033142");
        usuario.setRg("45253235252");
        usuario.setCpf("65624234222");
        usuario.setDataDeNas(LocalDate.of(1980,01,01));
        usuario.setGenero(Genero.HOMEMCIS);
        usuario.setDataDeCadastro(LocalDate.of(1990,3,15));
        usuarios.add(usuario);
        return usuarios;
    }

     

}

