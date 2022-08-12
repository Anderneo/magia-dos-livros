package com.eternos.magiadoslivros.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eternos.magiadoslivros.domain.interfaceRelatorio.RelatorioVendaInterface;
import com.eternos.magiadoslivros.domain.model.PedidoLivro;
import com.eternos.magiadoslivros.domain.model.PedidoLivroId;

public interface PedidoLivroRepository extends JpaRepository<PedidoLivro, PedidoLivroId>{
    @Query(value = "SELECT p.id as Numero_do_Pedido, l.nome as nome_do_livro, l.valor_venda as valor, pl.quantidade as quantidade_de_livros, p.endereco_entrega as endereco_de_entrega, p.forma_de_pagto as forma_de_pagamento, p.parcela as quantidade_de_parcelas, p.data_venda as data_da_venda, p.data_pagto as data_do_pagamento, p.data_entrega as data_da_entrega FROM ((pedido_livro pl INNER JOIN livro l on pl.id_livro = l.id) INNER JOIN pedido p on pl.id_pedido = p.id) ORDER BY pl.id_pedido", nativeQuery = true)
    List <RelatorioVendaInterface> getRelatorioVenda();

}
