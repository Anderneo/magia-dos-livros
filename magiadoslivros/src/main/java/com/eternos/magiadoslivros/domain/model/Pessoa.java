package com.eternos.magiadoslivros.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;


//@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@SuperBuilder
public class Pessoa {
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
