package com.eternos.magiadoslivros.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "livro")
@Data
@Builder
public class Livro {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivro;

    @Column(name="localizacao_estoque")
    private String tagEstoque;

    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @Column(name="isbn")
    private Integer isbn;

    @Column(name="quantidade_livro")
    private Integer quantLivros;

    @Column(name="valor_recebimento")
    private Double valorRecebimento;
    
    @Column(name="valor_venda")
    private Double valorVenda;

    //incluirLivro()  void
    //atualizarLivro()  void
    //apagarLivro()  void
    //consultaLivro()  void
}
