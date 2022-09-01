package com.eternos.magiadoslivros.LivroTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.assembler.LivroAssembler;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.request.LivroRequest;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;

@SpringBootTest
public class LivroAssemblerTeste {
    
    @InjectMocks
    private LivroAssembler livroAssembler;

    @Mock
    private FornecedorUtil fornecedorUtil;

    @Mock
    private LivroRequest livroRequest;

    @Mock
    private Livro livro;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testarToModel(){

        var objLivro = livroMock();
        var objLivroRequest = livroRequestMock();
        
        when(modelMapper.map(any(), any())).thenReturn(objLivro);
        
        var mock = livroAssembler.toModel(objLivroRequest);
      
        assertNotNull(mock);
        assertEquals(objLivro, mock);
        assertEquals(objLivro.getClass(), mock.getClass());

    }

    private LivroRequest livroRequestMock() {
        LivroRequest livroRequest = new LivroRequest();
        livroRequest.setDescricao("teste");
        livroRequest.setIsbn("teste");
        livroRequest.setNome("O diário de Anne Frank");
        livroRequest.setTagEstoque("fds");
        livroRequest.setQuantLivros(2);
        livroRequest.setValorRecebimento(50.00);
        livroRequest.setValorVenda(55.00);
        return livroRequest;
    }
    
    private Livro livroMock() {
        Livro livro = new Livro();
        livro.setDescricao("teste");
        livro.setIsbn("teste");
        livro.setNome("O diário de Anne Frank");
        livro.setTagEstoque("fds");
        livro.setQuantLivros(2);
        livro.setValorRecebimento(50.00);
        livro.setValorVenda(55.00);
        return livro;
    }
}
