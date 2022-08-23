package com.eternos.magiadoslivros;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.repository.FornecedorRepository;
import com.eternos.magiadoslivros.domain.request.FornecedorRequest;
import com.eternos.magiadoslivros.domain.service.FornecedorService;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;

@SpringBootTest
public class MockFornecedor {

    @InjectMocks
    private FornecedorService fornecedorService;
    
    @InjectMocks
    private FornecedorUtil fornecedorUtil;

    @Mock
    private FornecedorRepository fornecedorRepository;
    

    public Fornecedor mockFornecedor(){
    
        Fornecedor mockFornecedor = new Fornecedor();
        
        mockFornecedor.setEndereco("Rua teste, Nº123");
        mockFornecedor.setEmail("teste@teste.com.br");
        mockFornecedor.setTelefone("91234-5678");
        mockFornecedor.setObservacao("Teste");
        mockFornecedor.setNomeFantasia("Teste");
        mockFornecedor.setRazaoSocial("Teste SA");
        mockFornecedor.setCnpj("25124806000112");
        mockFornecedor.setInsEstadual("468484406893");
        
        return mockFornecedor;
        
    }
    
    public FornecedorRequest mockFornecedorRequest(){
    
        FornecedorRequest mockFornecedorRequest = new FornecedorRequest();
        
        mockFornecedorRequest.setEndereco("Rua teste, Nº123");
        mockFornecedorRequest.setEmail("teste@teste.com.br");
        mockFornecedorRequest.setTelefone("91234-5678");
        mockFornecedorRequest.setObservacao("Teste");
        mockFornecedorRequest.setNomeFantasia("Teste");
        mockFornecedorRequest.setRazaoSocial("Teste SA");
        mockFornecedorRequest.setCnpj("25124806000112");
        mockFornecedorRequest.setInsEstadual("468484406893");
        
        return mockFornecedorRequest();
        
    }
    
    @Test
    @DisplayName("Teste da Funcao buscarFornecedor()")
    public void deveRetornarUmFornecedorComIdInformado(){
    
        var obj = mockFornecedor();
        obj.setId(1);
        when(fornecedorRepository.findById(any())).thenReturn(Optional.of(obj));
        var mock = fornecedorUtil.buscarFornecedor(1);
        assertEquals(obj, mock);
        
    }
    
    @Test
    @DisplayName("Teste da Funcao salvarFornecedor()")
    public void deveRetornarOFornecedorSalvoNoBanco(){
    
        var obj = mockFornecedor();
        
        fornecedorRepository.save(obj);
        
        verify(fornecedorRepository, times(1)).save(obj);
        
    }
    
    // @Test
    // public void (){
    //     buscarFornecedor
    // }

    // @Test
    // public void testarResgistroExistenteNoBancoAoSalvarSAla(){
    //     var obj = mockSala();
    //     when(salaRepository.findById(any())).thenReturn(Optional.of(obj));
    //     when(salaRepository.findByDescricaoAndTurma(any(),any())).thenReturn(mockSalaDescricaoETurmaExistente());

    //     var ex = assertThrows(DefaultException.class, () -> {
    //         salaService.atualizarSala(1l,mockSalaRequest());
    //     });

    //     assertEquals(HttpStatus.BAD_REQUEST,ex.httpStatus);
    // }

    

    // public Optional<Sala> mockSalaDescricaoETurmaExistente(){
    //     Sala sala = new Sala();
    //     sala.setDescricao("teste");
    //     sala.setTurma("teste");
    //     sala.setId(2l);
    //     return Optional.of(sala);
    // }

    // public SalaRequest mockSalaRequest(){
    //     SalaRequest salaRequest = new SalaRequest();
    //     salaRequest.setDescricao("teste");
    //     return salaRequest;
    // }
    



}
