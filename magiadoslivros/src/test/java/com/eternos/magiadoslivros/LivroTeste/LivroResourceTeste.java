package com.eternos.magiadoslivros.LivroTeste;

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

import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.request.LivroRequest;
import com.eternos.magiadoslivros.domain.resource.LivroResource;
import com.eternos.magiadoslivros.domain.service.LivroService;

@SpringBootTest
public class LivroResourceTeste {

    private static final Integer idLivro = 1;
    private static final Integer quantLivros = 20;

    @InjectMocks
    private LivroResource livroResource;

    @Mock
    private LivroService livroService;

    @Mock
    private LivroRequest livroRequest;

    @Mock
    private Livro livro;

    @Test
    public void testarSalvarLivro() {
        var obj = livroMock();
        when(livroService.salvar(any())).thenReturn(obj);

        var mock = livroResource.salvar(livroRequest);

        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(Livro.class, mock.getClass());
    }

    @Test
    void testarBuscarTodosLivros(){
        var obj = listaLivroMocada();
        when(livroService.buscarTodos()).thenReturn(obj);

        var mock = livroResource.buscar();
        
        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getClass());
    }

    @Test
    void testarBuscarPorNome(){
        var obj = listaLivroMockPorNome();
        when(livroService.buscarNome(any())).thenReturn(obj);

        var mock = livroResource.buscarNome("Diário de Anne Frank");
        
        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getClass());
        
    }

    @Test
    void testarBuscarIsbn(){
        var obj = livroMock();
        when(livroService.buscarIsbn(any())).thenReturn(obj);

        var mock = livroResource.buscarIsbn("SP-667244404");
        
        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(Livro.class, mock.getClass());
    }

    @Test
    void testarDeletarLivro(){
        doNothing().when(livroService).deletar(any());
        livroResource.deletar(1);

        verify(livroService, times(1)).deletar(any());
    }

    @Test
    void testarAtualizarEstoque(){
        var obj = livroMock();
        when(livroService.atualizarQtdeLivro(any(), any())).thenReturn(obj);
        var mock = livroResource.atualizarEstoque(idLivro, quantLivros);

        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(Livro.class, mock.getClass());

    }

    @Test
    void testarUpdateLivro(){
        var obj = livroMock();
        when(livroService.atualizarLivro(any(), any())).thenReturn(obj);
        var mock = livroResource.update(idLivro, livroRequest);

        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(Livro.class, mock.getClass());
    }
    
    private Livro livroMock() {
        Livro livro = new Livro();
        livro.setIdLivro(1);
        livro.setIsbn("Teste_Teste");
        livro.setQuantLivros(0);
        return livro;
    }

    private List<Livro> listaLivroMocada(){

        List<Livro> livros = new ArrayList<>();

        return livros;
    }

    private List<Livro> listaLivroMockPorNome() {
        List<Livro> livros = new ArrayList<>();

        Livro livro = new Livro();
        livro.setNome("O diário de Anne Frank");
        livro.setIdLivro(1);
        livro.setIsbn("Teste_Teste");
        livros.add(livro);

        return livros;
    }


}
;
