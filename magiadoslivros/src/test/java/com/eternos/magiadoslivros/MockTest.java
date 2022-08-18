package com.eternos.magiadoslivros;


import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.repository.FornecedorRepository;
import com.eternos.magiadoslivros.domain.service.FornecedorService;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MockTest {

    @InjectMocks
    private FornecedorUtil fornecedorUtil;

    @Mock
    private FornecedorRepository fornecedorRepository;

    @Test
    public void testarBuscaPorId(){
        var obj = mockFornecedor();
        when(fornecedorRepository.findById(any())).thenReturn(Optional.of(obj));
        var mock = fornecedorUtil.buscarFornecedor(50);
        assertEquals(obj, mock);
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

    public Fornecedor mockFornecedor(){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(2);
        return fornecedor;
    }

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
