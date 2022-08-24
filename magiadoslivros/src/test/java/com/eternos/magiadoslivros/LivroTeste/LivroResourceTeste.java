package com.eternos.magiadoslivros.LivroTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.request.LivroRequest;
import com.eternos.magiadoslivros.domain.resource.LivroResource;
import com.eternos.magiadoslivros.domain.service.LivroService;

@SpringBootTest
public class LivroResourceTeste {

    private static final Integer idLivro = 1;
    private static final String descricao = "Livro 5";
    private static final String isbn = "SP-667244404";
    private static final String nome = "Diário de Anne Frank";
    private static final Integer quantLivros = 20;
    private static final String tagEstoque = "2b3";
    private static final Double valorRecebimento = 30.15;
    private static final Double valorVenda = 50.00;
    //private static final Integer idFornecedor = 1;

    @InjectMocks
    private LivroResource livroResource;

    @Mock
    private LivroService livroService;

    @Mock
    private LivroRequest livroRequest;

    @Mock
    private Livro livro;

    @BeforeEach
    void setup() {
        startLivro();
    }

    @Test
    public void testarSalvarLivro() {
        when(livroService.salvar(any())).thenReturn(livro);

        Livro response = livroResource.salvar(livroRequest);

        assertEquals(livro, response);
        assertNotNull(response);
        assertEquals(Livro.class, response.getClass());

    }

    @Test
    void testarBuscarTodosLivros(){
        when(livroService.buscarTodos()).thenReturn(List.of(livro));

        List<Livro> response = livroResource.buscar();
        assertNotNull(response);
    }

    @Test
    void testarBuscarPorNome(){
        when(livroService.buscarNome(any())).thenReturn(List.of(livro));

        List<Livro> response = livroResource.buscarNome("Diário de Anne Frank");
        assertNotNull(response);
        
    }

    @Test
    void testarBuscarIsbn(){
        when(livroService.buscarIsbn(any())).thenReturn(livro);

        Livro response = livroResource.buscarIsbn("SP-667244404");
        assertEquals(livro, response);
        assertNotNull(response);
        assertEquals(Livro.class, response.getClass());
    }

    // @Test
    // void testarDeletarLivro(){
    //       doNothing().when(livroService).deleteById(any());
    // }

    @Test
    void testaraAualizarEstoque(){
        when(livroService.atualizarQtdeLivro(any(), any())).thenReturn(livro);
        var response = livroResource.atualizarEstoque(idLivro, quantLivros);

        assertEquals(livro, response);
        assertNotNull(response);
        assertEquals(Livro.class, response.getClass());

    }

    @Test
    void testarUpdateLivro(){
        when(livroService.atualizarLivro(any(), any())).thenReturn(livro);
        var response = livroResource.update(idLivro, livroRequest);

        
    }
    
    public void startLivro(){
        LivroRequest livroRequest = new LivroRequest();
        Fornecedor fornecedor = new Fornecedor();
        livro = new Livro(idLivro, tagEstoque, nome, descricao, isbn, quantLivros, valorRecebimento, valorVenda, fornecedor);
     
    }

}
;
