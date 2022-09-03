package com.eternos.magiadoslivros.LivroTeste;

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

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.eternos.magiadoslivros.domain.assembler.LivroAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.request.LivroRequest;
import com.eternos.magiadoslivros.domain.service.LivroService;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;
import com.eternos.magiadoslivros.domain.util.LivroUtil;

@SpringBootTest
public class LivroServiceTest {
    
    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private LivroUtil livroUtil;

    @Mock
    private FornecedorUtil fornecedorUtil;

    @Mock
    private LivroAssembler livroAssembler;

    @Mock
    private LivroRequest livroRequest;

    @Test
    void testarBuscarTodos(){
        var obj = listaLivroMocada();
        when(livroRepository.findAll()).thenReturn(obj);
        var mock = livroService.buscarTodos();
        assertEquals(mock, obj);
        assertNotNull(mock); 
    }

    @Test
    void testarSalvarLivro(){
        var obj = livroMock();
        when(livroUtil.buscarId(any())).thenReturn(obj);
        when(livroAssembler.toModel(any())).thenReturn(obj);
        when(livroRepository.save(any())).thenReturn(obj);
        var mock = livroService.salvar(livroRequest);
        assertEquals(mock, obj); 
        assertNotNull(mock);         
    }

    @Test
    void testarBuscarPorNome(){
        var obj = listaLivroMockPorNome();
        when(livroRepository.findByNomeContainingIgnoreCase(any())).thenReturn(obj);
        var mock = livroService.buscarNome("O diário de Anne Frank");
        assertEquals(mock, obj);
        assertNotNull(mock); 
    }

    @Test
    void testarChecarExcecaBuscarPorNome(){
        var obj = listaLivroMocada();
        when(livroRepository.findByNomeContainingIgnoreCase(any())).thenReturn(obj);

        var excecao = assertThrows(DefaultException.class, () ->{
            livroService.buscarNome("O diário de Anne Frank");
        });
            
        assertEquals(HttpStatus.NOT_FOUND, excecao.httpStatus);

    }

    @Test
    void testarBuscarPorIsbn(){
        var obj = livroMock();
        when(livroRepository.findByIsbn(any())).thenReturn(obj);
        var mock = livroService.buscarIsbn("Teste_Teste");
        assertEquals(mock, obj);
        assertNotNull(mock); 
        assertEquals(obj.getClass(), mock.getClass());
    }
    
    @Test
    void testarChecarExcecaoBuscarPorIsbn(){
        when(livroRepository.findByIsbn(any())).thenReturn(null);

        var excecao = assertThrows(DefaultException.class, () ->{
            livroService.buscarIsbn("O diário de Anne Frank");
        });
            
        assertEquals(HttpStatus.NOT_FOUND, excecao.httpStatus);

    }

    @Test
    void testarDeletarLivro(){
        var obj = livroMock();
        when(livroUtil.buscarId(any())).thenReturn(obj);
        doNothing().when(livroRepository).deleteById(any());
        var excecao = assertThrows(DefaultException.class, () ->{
            livroService.deletar(1);
        });
        verify(livroRepository, times(1)).delete(any());
        assertEquals(HttpStatus.ACCEPTED, excecao.httpStatus);
    }

    @Test
    void testarAtualizarQuantEstoqueLivro(){
        var obj = livroMock();
        var objAtualizado = livroMockComMaisCinco();
        var objLivroRequest = livroRequestMock();

        when(livroUtil.buscarId(any())).thenReturn(obj);
        when(livroRepository.save(any())).thenReturn(objAtualizado);

        var mock = livroService.atualizarQtdeLivro(1, objLivroRequest);
      
        assertNotNull(mock);
        assertEquals(Livro.class, mock.getClass());
        assertEquals(mock.getQuantLivros(), objAtualizado.getQuantLivros() );
    }

    @Test
    void testarAtualizarLivro(){
        var obj = livroMock();
        var objFornecedor = fornecedorMock();
        var objAtualizado = livroMockFornecedor();
        when(livroUtil.buscarId(any())).thenReturn(obj);
        when(fornecedorUtil.buscarFornecedor(any())).thenReturn(objFornecedor);
        when(livroRepository.save(any())).thenReturn(objAtualizado);
        
        var mock = livroService.atualizarLivro(1, livroRequest);
      
        assertNotNull(mock);
        assertEquals(Livro.class, mock.getClass());
        assertEquals(mock, objAtualizado);
    }


    private Livro livroMock() {
        Livro livro = new Livro();
        livro.setIdLivro(1);
        livro.setIsbn("Teste_Teste");
        livro.setQuantLivros(0);
        return livro;
    }

    private LivroRequest livroRequestMock() {
        LivroRequest livroRequest = new LivroRequest();
        livroRequest.setQuantLivros(10);
        return livroRequest;
    }

    private Livro livroMockComMaisCinco() {
        Livro livro = new Livro();
        livro.setIdLivro(1);
        livro.setIsbn("Teste_Teste");
        livro.setQuantLivros(5);
        return livro;
    }

    private Livro livroMockFornecedor() {
        Livro livro = new Livro();
        livro.setIdLivro(1);
        livro.setIsbn("Teste_Teste");
        livro.setQuantLivros(5);
        livro.setIdFornecedor(fornecedorMock());
        return livro;
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

    private List<Livro> listaLivroMocada(){

        List<Livro> livros = new ArrayList<>();

        return livros;
    } 

    private Fornecedor fornecedorMock(){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1);
        fornecedor.setNomeFantasia("Teste");
        fornecedor.setRazaoSocial("teste");
        fornecedor.setCnpj("56.235.789/0001-52");
        fornecedor.setEmail("teste@teste.com");
        return fornecedor;
    }
}
