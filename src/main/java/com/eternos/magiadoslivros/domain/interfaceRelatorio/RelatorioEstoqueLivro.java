package com.eternos.magiadoslivros.domain.interfaceRelatorio;

public interface RelatorioEstoqueLivro {
    String getNome_do_livro();
    String getDescricao();
    String getIsbn(); 
    Double getValor_de_compra();
    Double getValor_de_venda();
    Integer getQuantidade_no_estoque();
}
