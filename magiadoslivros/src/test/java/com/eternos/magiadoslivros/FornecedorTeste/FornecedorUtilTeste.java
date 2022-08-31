package com.eternos.magiadoslivros.FornecedorTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.repository.FornecedorRepository;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;

@SpringBootTest
public class FornecedorUtilTeste {

    @InjectMocks
    private FornecedorUtil fornecedorUtil;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Test
    void testarBuscarFornecedor(){
        var obj = fornecedorMock();
        when(fornecedorRepository.findById(any())).thenReturn(Optional.of(obj));
        var mock = fornecedorUtil.buscarFornecedor(1);
        assertEquals(mock, obj);
        assertNotNull(mock);
    }
    
    @Test
    void testarChecarExcecaoBuscarPorId(){
        var obj = fornecedorMockVazio();
        when(fornecedorRepository.findById(any())).thenReturn(obj);

        var excecao = assertThrows(DefaultException.class, () ->{
            fornecedorUtil.buscarFornecedor(2);
        });          
        assertEquals(HttpStatus.NOT_FOUND, excecao.httpStatus);
    }
    
    @Test
    void testarChecarConstraintFornecedor(){
        var obj = fornecedorMock();
        when(fornecedorRepository.findByCnpj(any())).thenReturn(Optional.of(obj));
        
        var excecao = assertThrows(DefaultException.class, () ->{
            fornecedorUtil.checarConstraintFornecedor(obj);
        });          
        assertEquals(HttpStatus.FOUND, excecao.httpStatus);
        
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

    private Optional<Fornecedor> fornecedorMockVazio() {
        Optional<Fornecedor> fornecedor = Optional.empty();
        return fornecedor;
    }
}
