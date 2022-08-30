package com.eternos.magiadoslivros.PedidoTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.model.enums.Genero;
import com.eternos.magiadoslivros.domain.model.enums.Perfil;
import com.eternos.magiadoslivros.domain.repository.PedidoRepository;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
import com.eternos.magiadoslivros.domain.resource.PedidoResource;
import com.eternos.magiadoslivros.domain.service.PedidoService;
import com.eternos.magiadoslivros.domain.util.PedidoUtil;
import com.eternos.magiadoslivros.domain.util.UsuarioUtil;

@SpringBootTest
public class PedidoResourceTeste {

    @InjectMocks
    private PedidoResource pedidoResource;
    
    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoService pedidoService;
    
    @Mock
    private PedidoRequest pedidoRequest;
    
    @Mock
    private PedidoUtil pedidoUtil;
    
    @Mock
    private UsuarioUtil usuarioUtil;

    @Test
    void testarSalvarPedido(){
        var obj = pedidoMock();
        when(pedidoService.salvar(any())).thenReturn(obj);

        var mock = pedidoResource.salvar(pedidoRequest);
        
        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getClass());
    }

    @Test
    void testarBuscarTodosPedidos(){
        var obj = listaPedidoMocada();
        when(pedidoService.buscarTodos()).thenReturn(obj);

        var mock = pedidoResource.buscar();
        
        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getClass());
    }
    
    @Test
    void cancelarPedido(){
        var pedido = pedidoMock();
        var usuario = usuarioMock();
        
        //doNothing().when(pedidoService).cancelarPedido(any(),any());
        
        var ex = assertThrows(DefaultException.class, () ->{
            pedidoResource.cancelarPedido(pedido.getIdVenda(),usuario.getId());
        }); 
        
        assertEquals(HttpStatus.ACCEPTED,ex.httpStatus);

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
    
}
