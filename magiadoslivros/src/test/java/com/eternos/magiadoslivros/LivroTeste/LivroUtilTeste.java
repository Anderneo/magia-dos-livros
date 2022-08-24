package com.eternos.magiadoslivros.LivroTeste;

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
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.util.LivroUtil;

@SpringBootTest
public class LivroUtilTeste {

    @InjectMocks
    private LivroUtil livroUtil;

    @Mock
    private LivroRepository livroRepository;

    @Test
    void testarBuscarPorId(){
        var obj = livroMock();
        when(livroRepository.findById(any())).thenReturn(Optional.of(obj));
        var mock = livroUtil.buscarId(1);
        assertEquals(mock, obj);
        assertNotNull(mock);
    }
    
    @Test
    void testarChecarExcecaoBuscarPorId(){
        var obj = livroMockVazio();
        when(livroRepository.findById(any())).thenReturn(obj);

        var excecao = assertThrows(DefaultException.class, () ->{
            livroUtil.buscarId(2);
        });          
        assertEquals(HttpStatus.NOT_FOUND, excecao.httpStatus);
    }
    
    @Test
    void testarChecarExcecaoChecarIsbn(){
        var obj = livroMock();
        when(livroRepository.findByIsbn(any())).thenReturn(obj);

        var excecao = assertThrows(DefaultException.class, () ->{
            livroUtil.checarIsbn("Teste");
        });          
        assertEquals(HttpStatus.BAD_REQUEST, excecao.httpStatus);
    }

    private Livro livroMock() {
        Livro livro = new Livro();
        livro.setIdLivro(1);
        livro.setIsbn("Teste_Teste");
        livro.setQuantLivros(0);
        return livro;
    }

    private Optional<Livro> livroMockVazio() {
        Optional<Livro> livro = Optional.empty();
        return livro;
    }
}
