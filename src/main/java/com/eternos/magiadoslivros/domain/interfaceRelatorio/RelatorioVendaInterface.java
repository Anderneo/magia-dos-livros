package com.eternos.magiadoslivros.domain.interfaceRelatorio;

import java.time.LocalDate;

public interface RelatorioVendaInterface {
    Integer getNumero_do_pedido();
    String getNome_do_livro();
    Double getValor();
    Integer getQuantidade_de_livros();
    String getEndereco_de_entrega();
    String getForma_de_pagamento();
    String getQuantidade_de_parcelas();
    LocalDate getData_da_venda();
    LocalDate getData_do_pagamento();
    LocalDate getData_da_entrega();

}
