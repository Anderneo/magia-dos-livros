package com.eternos.magiadoslivros.domain.model;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Builder
@Entity
@Table(name = "fornecedor")
public class Fornecedor{
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name="endereco", nullable = false)
    protected String endereco;

    @Column(name="email", nullable = false)
    protected String email;

    @Column(name="telefone", nullable = false) 
    protected String telefone;
      
    @Column(name="observacao")
    protected String observacao;

    @Column(name="nome_fantasia")
    private String nomeFantasia;

    @Column(name="razao_social", nullable = false)
    private String razaoSocial;

    @Column(name="cnpj", nullable = false)
    private String cnpj;

    @Column(name="ins_estadual", nullable = false)
    private String insEstadual;

    //incluirFornecedor()  void
    //atualizarFornecedor()  void
    //consultaFornecedor()  void
    //apagarFornecedor()  void
}
