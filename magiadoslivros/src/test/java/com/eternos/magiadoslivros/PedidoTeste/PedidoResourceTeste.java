package com.eternos.magiadoslivros.PedidoTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.model.enums.Genero;
import com.eternos.magiadoslivros.domain.model.enums.Perfil;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
import com.eternos.magiadoslivros.domain.resource.PedidoResource;
import com.eternos.magiadoslivros.domain.service.PedidoService;

@SpringBootTest
public class PedidoResourceTeste {

    @InjectMocks
    private PedidoResource pedidoResource;

    @Mock
    private PedidoService pedidoService;
    
    @Mock
    private PedidoRequest pedidoRequest;

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
    
    void cancelarPedido(){
        

    }

    private Pedido pedidoMock() {
        Pedido pedido = new Pedido();
        pedido.setIdVenda(1);
        pedido.setParcela(2);
        pedido.setFormaDePgto("á vista");
        pedido.setValorVenda(50.00);
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
