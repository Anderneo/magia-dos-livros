package com.eternos.magiadoslivros.domain.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eternos.magiadoslivros.domain.interfaceRelatorio.RelatorioEstoqueLivro;
import com.eternos.magiadoslivros.domain.interfaceRelatorio.RelatorioVendaInterface;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.repository.PedidoLivroRepository;

@RestController
@RequestMapping(value = "relatorio")
public class RelatorioResource {

    @Autowired
    private PedidoLivroRepository pedidoLivroRepository;

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping(path = "vendas")
    public List <RelatorioVendaInterface> relatorioVenda() {
        return pedidoLivroRepository.getRelatorioVenda();
    }

    @GetMapping(path = "estoquelivro")
    public List <RelatorioEstoqueLivro> relatorioEstoque() {
        return livroRepository.getRelatorioEstoqueLivros();
    }
    
}
