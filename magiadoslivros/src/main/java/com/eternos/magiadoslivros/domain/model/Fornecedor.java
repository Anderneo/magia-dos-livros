package com.eternos.magiadoslivros.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Builder
@Entity
@Table(name = "fornecedor")
@AllArgsConstructor
@Data
public class Fornecedor{

    public Fornecedor(){}

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="endereco")
    private String endereco;

    @Column(name="email")
    private String email;

    @Column(name="telefone") 
    private String telefone;
      
    @Column(name="observacao")
    private String observacao;

    @Column(name="nome_fantasia")
    private String nomeFantasia;

    @Column(name="razao_social")
    private String razaoSocial;

    @Column(name="cnpj")
    private String cnpj;

    @Column(name="ins_estadual")
    private String insEstadual;

    //incluirFornecedor()  void
    //atualizarFornecedor()  void
    //consultaFornecedor()  void
    //apagarFornecedor()  void
}
