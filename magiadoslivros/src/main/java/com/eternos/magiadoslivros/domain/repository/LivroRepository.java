package com.eternos.magiadoslivros.domain.repository;

import com.eternos.magiadoslivros.domain.interfaceRelatorio.RelatorioEstoqueLivro;
import com.eternos.magiadoslivros.domain.model.Livro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
    
public interface LivroRepository extends JpaRepository<Livro, Integer> {
    List<Livro> findByNomeContainingIgnoreCase(String nome);
    Livro findByIsbn(String isbn);

    @Query(value = "SELECT nome as nome_do_livro, descricao, isbn, valor_recebimento as valor_de_compra, valor_venda as valor_de_venda, quantidade_livro as quantidade_no_estoque FROM livro", nativeQuery = true)
    List <RelatorioEstoqueLivro> getRelatorioEstoqueLivros();

}
