package com.eternos.magiadoslivros.PedidoTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.eternos.magiadoslivros.domain.assembler.PedidoAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.model.enums.Genero;
import com.eternos.magiadoslivros.domain.model.enums.Perfil;
import com.eternos.magiadoslivros.domain.repository.PedidoRepository;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
import com.eternos.magiadoslivros.domain.service.PedidoService;
import com.eternos.magiadoslivros.domain.util.PedidoUtil;
import com.eternos.magiadoslivros.domain.util.UsuarioUtil;

@SpringBootTest
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoAssembler pedidoAssembler;

    @Mock
    private Pedido pedido;

    @Mock
    private PedidoUtil pedidoUtil;
    
    @Mock
    private UsuarioUtil usuarioUtil;

    @Mock
    private PedidoRequest pedidoRequest;

    @Test
    void testarBuscarTodos(){
        var obj = listaPedidoMocada();

        when(pedidoRepository.findAll()).thenReturn(obj);

        var mock = pedidoService.buscarTodos();
        
        assertEquals(mock, obj);
        assertNotNull(mock); 
        assertEquals(obj.getClass(), mock.getClass());
    }

    @Test
    void testarSalvarLivro(){
        var obj = pedidoMock();
        var objListaLivro = listaLivroMock();

        when(pedidoAssembler.toModel(any())).thenReturn(obj);
        when(pedidoRepository.save(any())).thenReturn(obj);
        when(pedidoUtil.listaLivro(any(), any())).thenReturn(objListaLivro);
        doNothing().when(pedido).setListaLivro(any());

        var mock = pedidoService.salvar(pedidoRequest);

        assertEquals(mock, obj); 
        assertNotNull(mock); 
        assertEquals(obj.getClass(), mock.getClass());        
    }

    @Test
    void cancelarPedido(){
    
        var pedido = pedidoMock();
        var usuario = usuarioMock();
        var objPedidoRequest = pedidoRequestMock();
        
        when(pedidoUtil.buscarId(any())).thenReturn(pedido);
        when(usuarioUtil.buscarId(any())).thenReturn(usuario);
        doNothing().when(usuarioUtil).checarUsuario(any());
        when(pedidoRepository.save(any())).thenReturn(pedido);
        
        var ex = assertThrows(DefaultException.class, () ->{
            pedidoService.cancelarPedido(pedido.getIdVenda(),objPedidoRequest);
        }); 
        
        assertEquals(HttpStatus.ACCEPTED,ex.httpStatus);

    }
    
    @Test
    void TestarExcessaoPedidoJaCancelado(){
    
        var pedido = pedidoMock();
        var usuario = usuarioMock();
        var objPedidoRequest = pedidoRequestMock();
        
        when(pedidoUtil.buscarId(any())).thenReturn(pedido);
        when(usuarioUtil.buscarId(any())).thenReturn(usuario);
        doNothing().when(usuarioUtil).checarUsuario(any());
        when(pedidoRepository.save(any())).thenReturn(pedido);
        
        pedido.setVendaCancelada(true);
        
        var ex = assertThrows(DefaultException.class, () ->{
            pedidoService.cancelarPedido(pedido.getIdVenda(),objPedidoRequest);
        }); 
        
        assertEquals(HttpStatus.ALREADY_REPORTED,ex.httpStatus);
    }

    private PedidoRequest pedidoRequestMock() {
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setIdUsuario(3);
        return pedidoRequest;
    }
    
    private List<Pedido> listaPedidoMocada(){
    
        Pedido pedido = new Pedido();
        pedido.setIdVenda(1);
        pedido.setParcela(2);
        pedido.setFormaDePgto("á vista");
        pedido.setValorVenda(50.00);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);

        return pedidos;
    }

    private ArrayList<Livro> listaLivroMock(){
        ArrayList<Livro> livros = new ArrayList<>();

        Livro livro = new Livro();
        livro.setNome("O diário de Anne Frank");
        livro.setIdLivro(1);
        livro.setIsbn("Teste_Teste");
        livros.add(livro);

        return livros;
    }
    
    private Pedido pedidoMock() {
        Pedido pedido = new Pedido();
        pedido.setIdVenda(1);
        pedido.setParcela(2);
        pedido.setFormaDePgto("á vista");
        pedido.setValorVenda(50.00);
        pedido.setVendaCancelada(false);
        return pedido;
    }
    
    private Usuario usuarioMock() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setEndereco("Teste");
        usuario.setEmail("teste@teste.com");
        usuario.setTelefone("19988776655");
        usuario.setObservacao("teste");
        usuario.setNome("Teste");
        usuario.setRg("44.444.444-88");
        usuario.setCpf("333.333.444.-55");
        usuario.setGenero(Genero.HOMEMCIS);
        usuario.setPerfil(Perfil.ADMINISTRADOR);
        usuario.setDataDeNas(LocalDate.now());
        usuario.setDataDeCadastro(LocalDate.now());
        return usuario;
    }
    
}
