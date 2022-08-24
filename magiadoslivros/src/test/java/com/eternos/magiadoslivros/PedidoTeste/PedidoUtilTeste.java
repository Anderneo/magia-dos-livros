package com.eternos.magiadoslivros.PedidoTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.repository.PedidoRepository;
import com.eternos.magiadoslivros.domain.request.PedidoLivroRequest;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
import com.eternos.magiadoslivros.domain.util.PedidoUtil;

@SpringBootTest
public class PedidoUtilTeste {

    @InjectMocks
    private PedidoUtil pedidoUtil;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoRequest pedidoRequest;

    int index = 1;

    @Test
    void testarCriarListaLivroPedido(){
        
    }

    @Test
    void testarBuscarIdPedido(){
        var obj = pedidoMock();
        when(pedidoRepository.findById(any())).thenReturn(Optional.of(obj));
        var mock = pedidoUtil.buscarId(1);
        assertEquals(mock, obj);
        assertNotNull(mock); 
        assertEquals(mock.getClass(), obj.getClass());
    }

    @Test
    void testarExcecaoBuscarIdPedido(){

        var obj = pedidoMockVazio();
        when(pedidoRepository.findById(any())).thenReturn(obj);

        var excecao = assertThrows(DefaultException.class, () ->{
            pedidoUtil.buscarId(22);
        });
            
        assertEquals(HttpStatus.NOT_FOUND, excecao.httpStatus);
        
    }


    @Test
    void testarExcecaoChecarEstoqueLivro(){

        var excecao = assertThrows(DefaultException.class, () ->{
            pedidoUtil.checarEstoque(getListaLivroMock(), livroMock(), index);
        });
            
        assertEquals(HttpStatus.BAD_REQUEST, excecao.httpStatus);
        
    }

    private Pedido pedidoMock(){
        Pedido pedido = new Pedido();

        pedido.setIdVenda(1);

        return pedido;
    }

    private Optional<Pedido> pedidoMockVazio() {
        Optional<Pedido> livro = Optional.empty();
        return livro;
    }

    private ArrayList<PedidoLivroRequest> getListaLivroMock(){


        ArrayList<PedidoLivroRequest> listaLivro = new ArrayList<PedidoLivroRequest>();

        PedidoLivroRequest pedidoLivroRequest = new PedidoLivroRequest();

        pedidoLivroRequest.setQuantidade(3);
        pedidoLivroRequest.setIdLivro(1);

        listaLivro.add(pedidoLivroRequest);

        return listaLivro;
    }  

    private Livro livroMock(){
        Livro livro = new Livro();
        livro.setIdLivro(1);
        livro.setQuantLivros(1);
        return livro;
    }
    
}
