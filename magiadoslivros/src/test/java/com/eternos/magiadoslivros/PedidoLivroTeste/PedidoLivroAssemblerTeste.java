package com.eternos.magiadoslivros.PedidoLivroTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.assembler.PedidoLivroAssembler;
import com.eternos.magiadoslivros.domain.model.PedidoLivro;
import com.eternos.magiadoslivros.domain.request.PedidoLivroRequest;

@SpringBootTest
public class PedidoLivroAssemblerTeste {

    @InjectMocks
    private PedidoLivroAssembler pedidoLivroAssembler; 

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testarToModel(){
        var objPedidoLivro = PedidoLivroMock();
        var objPedidoLivroRequestMock = PedidoLivroRequestMock();
        
        when(modelMapper.map(any(), any())).thenReturn(objPedidoLivro);
        
        var mock = pedidoLivroAssembler.toModel(objPedidoLivroRequestMock, objPedidoLivro.getId_pedido());
      
        assertNotNull(mock);
        assertEquals(objPedidoLivro, mock);
        assertEquals(objPedidoLivro.getClass(), mock.getClass());
    }
    
    public PedidoLivroRequest PedidoLivroRequestMock() {
        PedidoLivroRequest pedidoLivroRequest = new PedidoLivroRequest();
        pedidoLivroRequest.setIdLivro(1);
        pedidoLivroRequest.setQuantidade(1);
        
        return pedidoLivroRequest;
    }
    
    public PedidoLivro PedidoLivroMock(){
        PedidoLivro pedidoLivro = new PedidoLivro();
    
        pedidoLivro.setId_pedido(1);
        pedidoLivro.setId_livro(1);
        pedidoLivro.setQuantidade(1);
        
        return  pedidoLivro;
        
    }

}
