package com.eternos.magiadoslivros.PedidoTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.assembler.PedidoAssembler;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.repository.PedidoRepository;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
import com.eternos.magiadoslivros.domain.service.PedidoService;
import com.eternos.magiadoslivros.domain.util.PedidoUtil;

@SpringBootTest
public class PedidoServiceTeste {

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

    private Pedido pedidoMock(){

        Pedido pedido = new Pedido();
        pedido.setIdVenda(1);
        pedido.setParcela(2);
        pedido.setFormaDePgto("á vista");
        pedido.setValorVenda(50.00);

        return pedido;
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
    
}
