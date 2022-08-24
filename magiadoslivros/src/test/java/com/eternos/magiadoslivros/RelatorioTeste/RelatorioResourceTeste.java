package com.eternos.magiadoslivros.RelatorioTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.interfaceRelatorio.RelatorioEstoqueLivro;
import com.eternos.magiadoslivros.domain.interfaceRelatorio.RelatorioVendaInterface;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.repository.PedidoLivroRepository;
import com.eternos.magiadoslivros.domain.resource.RelatorioResource;

@SpringBootTest
public class RelatorioResourceTeste {

    @InjectMocks
    private RelatorioResource relatorioResource;

    @Mock
    private RelatorioEstoqueLivro relatorioEstoqueLivro;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private PedidoLivroRepository pedidoLivroRepository;

    @Test
    void testarRelatorioVenda() {
        var obj = listaRelatorioVendaMocada();

        when(pedidoLivroRepository.getRelatorioVenda()).thenReturn(obj);

        var mock = relatorioResource.relatorioVenda();

        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getClass());
        
    }

    @Test
    void testarRelatorioEstoque() {
        var obj = listaRelatorioEstoqueMocada();

        when(livroRepository.getRelatorioEstoqueLivros()).thenReturn(obj);

        var mock = relatorioResource.relatorioEstoque();

        assertEquals(mock, obj);
        assertNotNull(mock);
        assertEquals(obj.getClass(), mock.getClass());
        
    }

    private List<RelatorioEstoqueLivro> listaRelatorioEstoqueMocada(){

        List<RelatorioEstoqueLivro> listaRelatorioEstoque = new ArrayList<>();

        return listaRelatorioEstoque;
    }

    private List<RelatorioVendaInterface> listaRelatorioVendaMocada(){

        List<RelatorioVendaInterface> listaRelatorioVenda = new ArrayList<>();

        return listaRelatorioVenda;
    }
    
}
