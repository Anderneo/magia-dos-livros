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

import com.eternos.magiadoslivros.domain.assembler.PedidoLivroAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.PedidoLivro;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.repository.PedidoLivroRepository;
import com.eternos.magiadoslivros.domain.repository.PedidoRepository;
import com.eternos.magiadoslivros.domain.request.PedidoLivroRequest;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
import com.eternos.magiadoslivros.domain.util.LivroUtil;
import com.eternos.magiadoslivros.domain.util.PedidoUtil;

@SpringBootTest
public class PedidoUtilTeste {

    @InjectMocks
    private PedidoUtil pedidoUtil;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoRequest pedidoRequest;

    @Mock
    private LivroUtil livroUtil;

    @Mock
    private PedidoLivroRepository pedidoLivroRepository;
    
    @Mock
    private LivroRepository livroRepository;
    
    @Mock
    PedidoLivroAssembler pedidoLivroAssembler;
    
    int index = 1;

    @Test
    void testarCriarListaLivroPedido(){
        var objPedidoRequest = pedidoRequestmock();
        var objLivro = livroMock();
        var objPedido = pedidoMock();
        var obj = getListaLivroMock();
        when(livroUtil.buscarId(any())).thenReturn(objLivro);
        //doNothing().when(checarEstoque(any(), any(), any()));
        when(pedidoLivroRepository.save(any()));

        var mock = pedidoUtil.listaLivro(objPedido, objPedidoRequest);
        assertEquals(mock, obj);
        assertNotNull(mock); 
        assertEquals(mock.getClass(), obj.getClass());     
        
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
            pedidoUtil.checarEstoque(getListaLivroMock(), livroMock(), 0);
        });
            
        assertEquals(HttpStatus.BAD_REQUEST, excecao.httpStatus);
        
    }

    private Pedido pedidoMock(){
        Pedido pedido = new Pedido();

        pedido.setIdVenda(1);
        pedido.setParcela(2);
        pedido.setFormaDePgto("á vista");
        pedido.setValorVenda(50.00);

        return pedido;
    }

    private Optional<Pedido> pedidoMockVazio() {
        Optional<Pedido> livro = Optional.empty();
        return livro;
    }

    private ArrayList<PedidoLivroRequest> getListaLivroMock(){


        ArrayList<PedidoLivroRequest> listaLivro = new ArrayList<PedidoLivroRequest>();

        PedidoLivroRequest pedidoLivroRequest = new PedidoLivroRequest();

        pedidoLivroRequest.setQuantidade(2);
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
        

    private PedidoRequest pedidoRequestmock() {
        ArrayList<PedidoLivroRequest> listaLivro = new ArrayList<PedidoLivroRequest>();

        PedidoLivroRequest pedidoLivroRequest = new PedidoLivroRequest();

        pedidoLivroRequest.setQuantidade(1);
        pedidoLivroRequest.setIdLivro(1);

        listaLivro.add(pedidoLivroRequest);

        PedidoRequest pedidoRequest = new PedidoRequest();

        pedidoRequest.setEnderecoEntrega("Teste, 23");
        pedidoRequest.setFormaDePgto("parcelado");
        pedidoRequest.setListaLivro(listaLivro);

        return pedidoRequest;
    }
    
    private  ArrayList<Livro> listaTeste() {
        ArrayList<Livro> listaLivro = new ArrayList<Livro>();

        Livro livro = new Livro();

        livro.setIdLivro(1);
        livro.setDescricao("teste");
        livro.setIsbn("0-6852-3673-0");
        livro.setNome("teste");
        livro.setQuantLivros(9);
        livro.setTagEstoque("teste");
        livro.setValorRecebimento(50.00);
        livro.setValorVenda(50.00);

        listaLivro.add(livro);

        return listaLivro;
    }
    
    private PedidoLivro pedidoLivroMock(){
        PedidoLivro pedidoLivro = new PedidoLivro();

        pedidoLivro.setId_livro(1);
        pedidoLivro.setQuantidade(1);

        return pedidoLivro;
    }
    
}
