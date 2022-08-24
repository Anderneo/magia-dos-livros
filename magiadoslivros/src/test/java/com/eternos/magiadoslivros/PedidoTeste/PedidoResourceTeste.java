package com.eternos.magiadoslivros.PedidoTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.model.Pedido;
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

    private Pedido pedidoMock() {
        Pedido pedido = new Pedido();
        return pedido;
    }

    private List<Pedido> listaPedidoMocada(){

        List<Pedido> pedidos = new ArrayList<>();

        return pedidos;
    }
    
}
