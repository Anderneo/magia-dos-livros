package com.eternos.magiadoslivros.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "fornecedor")
@Data
public class Fornecedor{

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

    @Column(name="razao_social", unique=true)
    private String razaoSocial;

    @Column(name="cnpj", unique=true)
    private String cnpj;

    @Column(name="ins_estadual")
    private String insEstadual;

}
