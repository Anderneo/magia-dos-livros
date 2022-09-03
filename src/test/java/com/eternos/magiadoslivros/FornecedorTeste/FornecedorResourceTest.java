package com.eternos.magiadoslivros.FornecedorTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.request.FornecedorRequest;
import com.eternos.magiadoslivros.domain.resource.FornecedorResource;
import com.eternos.magiadoslivros.domain.service.FornecedorService;

@SpringBootTest
public class FornecedorResourceTest {

    private static final Integer idFornecedor = 1;

    @InjectMocks
    private FornecedorResource fornecedorResource;

    @Mock
    private FornecedorService fornecedorService;

    @Mock
    private FornecedorRequest fornecedorRequest;

    @Mock
    private Fornecedor fornecedor;

    @Test
    void testarSalvarFornecedor() {
        var obj = fornecedorMock();
        when(fornecedorService.salvar(any())).thenReturn(obj);

        var mock = fornecedorResource.salvar(fornecedorRequest);

        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(Fornecedor.class, mock.getClass());
    }

    @Test
    void testarBuscarTodosFornecedors(){
        var obj = listaFornecedorMocada();
        when(fornecedorService.buscarTodos()).thenReturn(obj);

        var mock = fornecedorResource.buscar();
        
        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getClass());
    }

    @Test
    void testarBuscarPorRazaoSocial(){
        var obj = fornecedorMock();
        when(fornecedorService.buscarRazaoSocial(any())).thenReturn(obj);

        var mock = fornecedorResource.buscarRazaoSocial("Teste");
        
        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getClass());
        
    }

    @Test
    void testarBuscarPorCnpj(){
        var obj = fornecedorMock();
        when(fornecedorService.buscarCnpj(any())).thenReturn(obj);

        var mock = fornecedorResource.buscarCnpj("60.575.973/0001-80");
        
        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(Fornecedor.class, mock.getClass());
    }

    @Test
    void testarDeletarFornecedor(){
        doNothing().when(fornecedorService).deletar(any());
        fornecedorResource.deletar(1);

        verify(fornecedorService, times(1)).deletar(any());
    }

    @Test
    void testarAtualizarFornecedor(){
        var obj = fornecedorMock();
        when(fornecedorService.atualizarFornecedor(any(), any())).thenReturn(obj);
        var mock = fornecedorResource.atualizarFornecedor(idFornecedor, fornecedorRequest);

        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(Fornecedor.class, mock.getClass());
    }
    
    private Fornecedor fornecedorMock() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setEndereco("teste");
        fornecedor.setEmail("teste");
        fornecedor.setTelefone("19988776655");
        fornecedor.setObservacao("fds");
        fornecedor.setNomeFantasia("Teste");
        fornecedor.setRazaoSocial("Teste");
        fornecedor.setCnpj("60.575.973/0001-80");
        return fornecedor;
    }

    private List<Fornecedor> listaFornecedorMocada(){
        Fornecedor fornecedor = fornecedorMock();

        List<Fornecedor> fornecedors = new ArrayList<>();

        fornecedors.add(fornecedor);

        return fornecedors;
    }


}
