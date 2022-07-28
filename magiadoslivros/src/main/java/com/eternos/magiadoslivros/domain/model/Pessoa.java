package com.eternos.magiadoslivros.domain.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Id;

import lombok.Data;
import lombok.experimental.SuperBuilder;


@MappedSuperclass
@Data
@SuperBuilder
public abstract class Pessoa {
    @Id
    @Column(name="id")
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
}
