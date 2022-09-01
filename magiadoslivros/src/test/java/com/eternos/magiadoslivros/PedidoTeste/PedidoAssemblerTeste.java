package com.eternos.magiadoslivros.PedidoTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.assembler.PedidoAssembler;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.request.PedidoLivroRequest;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;
import com.eternos.magiadoslivros.domain.util.UsuarioUtil;

@SpringBootTest
public class PedidoAssemblerTeste {
    @InjectMocks
    private PedidoAssembler pedidoAssembler;

    @Mock
    private FornecedorUtil fornecedorUtil;
    
    @Mock
    private UsuarioUtil usuarioUtil;

    @Mock
    private PedidoRequest pedidoRequest;

    @Mock
    private Pedido pedido;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testarToModel(){
        var objUsuario = usuarioMock();
        var objPedido = pedidoMock();
        var objPedidoRequest = pedidoRequestMock();
        
        when(usuarioUtil.buscarId(any())).thenReturn(objUsuario);
        
        when(modelMapper.map(any(), any())).thenReturn(objPedido);
        
        var mock = pedidoAssembler.toModel(objPedidoRequest);
      
        assertNotNull(mock);
        assertEquals(objPedido, mock);
        assertEquals(objPedido.getClass(), mock.getClass());
    }
    
    public PedidoRequest pedidoRequestMock(){
        ArrayList<PedidoLivroRequest> listaLivro = new ArrayList<PedidoLivroRequest>();
        PedidoRequest pedidoRequest = new PedidoRequest(
            50.00,
            "teste",
            "teste",
            1,
            "01/02/2000",
            "01/02/2000",
            "01/02/2000",
            1,
            listaLivro
        );
        return pedidoRequest;
    }
    
    public Pedido pedidoMock(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("01/02/2000", formatter);
        List<Livro> listaLivro = new ArrayList<>();
        Pedido pedidoMock = new Pedido();
        Usuario usuario = new Usuario();
        usuario.setId(1);
        pedidoMock.setValorVenda(50.00);
        pedidoMock.setEnderecoEntrega("teste");
        pedidoMock.setFormaDePgto("teste");
        pedidoMock.setParcela(1);
        pedidoMock.setDataVenda(date);
        pedidoMock.setDataPgto(date);
        pedidoMock.setDataEntrega(date);
        pedidoMock.setIdUsuario(usuario);
        pedidoMock.setListaLivro(listaLivro);
            
        return pedidoMock;
    }
    
    public Usuario usuarioMock(){
        Usuario usuario = new Usuario();
        usuario.setId(1);
        
        return usuario;
    }
}
