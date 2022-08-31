package com.eternos.magiadoslivros.FornecedorTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.eternos.magiadoslivros.domain.assembler.FornecedorAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.repository.FornecedorRepository;
import com.eternos.magiadoslivros.domain.request.FornecedorRequest;
import com.eternos.magiadoslivros.domain.service.FornecedorService;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;


@SpringBootTest
public class FornecedorServiceTeste {
    
    @InjectMocks
    private FornecedorService fornecedorService;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Mock
    private FornecedorUtil fornecedorUtil;

    @Mock
    private FornecedorAssembler fornecedorAssembler;

    @Mock
    private FornecedorRequest fornecedorRequest;

    @Test
    void testarBuscarTodos(){
        var obj = listaFornecedorMocada();
        when(fornecedorRepository.findAll()).thenReturn(obj);
        var mock = fornecedorService.buscarTodos();
        assertEquals(mock, obj);
        assertNotNull(mock); 
    }

    @Test
    void testarSalvarFornecedor(){
        var obj = fornecedorMock();
        when(fornecedorUtil.buscarFornecedor(any())).thenReturn(obj);
        when(fornecedorAssembler.toModel(any())).thenReturn(obj);
        when(fornecedorRepository.save(any())).thenReturn(obj);
        var mock = fornecedorService.salvar(fornecedorRequest);
        assertEquals(mock, obj); 
        assertNotNull(mock);         
    }

    @Test
    void testarBuscarPorRazaoSocial(){
        var obj = fornecedorMock();
        when(fornecedorRepository.findByRazaoSocial(any())).thenReturn(Optional.of(obj));
        var mock = fornecedorService.buscarRazaoSocial("Teste");
        assertEquals(mock, obj);
        assertNotNull(mock); 
    }

    @Test
    void testarBuscarPorCnpj(){
        var obj = fornecedorMock();
        when(fornecedorRepository.findByCnpj(any())).thenReturn(Optional.of(obj));
        var mock = fornecedorService.buscarCnpj("60.575.973/0001-80");
        assertEquals(mock, obj);
        assertNotNull(mock); 
        assertEquals(obj.getClass(), mock.getClass());
    }

    @Test
    void testarDeletarFornecedor(){
        var obj = fornecedorMock();
        when(fornecedorUtil.buscarFornecedor(any())).thenReturn(obj);
        doNothing().when(fornecedorRepository).delete(any());
        var excecao = assertThrows(DefaultException.class, () ->{
            fornecedorService.deletar(1);
        });
        verify(fornecedorRepository, times(1)).delete(any());
        assertEquals(HttpStatus.ACCEPTED, excecao.httpStatus);
    }

    @Test
    void testarAtualizarFornecedor(){
        var obj = fornecedorMock();
        var objAtualizado = fornecedorMock();
        objAtualizado.setEmail("alterado@teste.com");
        when(fornecedorUtil.buscarFornecedor(any())).thenReturn(obj);
        when(fornecedorRepository.save(any())).thenReturn(objAtualizado);
        
        var mock = fornecedorService.atualizarFornecedor(1, fornecedorRequest);
      
        assertNotNull(mock);
        assertEquals(Fornecedor.class, mock.getClass());
        assertEquals(mock, objAtualizado);
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
        fornecedor.setInsEstadual("746.850.397.742");
        return fornecedor;
    }

    private List<Fornecedor> listaFornecedorMocada(){
        Fornecedor fornecedor = fornecedorMock();

        List<Fornecedor> fornecedors = new ArrayList<>();

        fornecedors.add(fornecedor);

        return fornecedors;
    }
}
