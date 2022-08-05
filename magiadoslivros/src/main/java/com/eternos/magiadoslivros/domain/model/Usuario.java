package com.eternos.magiadoslivros.domain.model;

import java.time.LocalDate;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.eternos.magiadoslivros.domain.model.enums.Genero;
import com.eternos.magiadoslivros.domain.model.enums.Perfil;


@Entity
@Table(name = "usuario")
@Data
public class Usuario{

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="endereco", nullable = false)
    private String endereco;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="telefone", nullable = false) 
    private String telefone;
      
    @Column(name="observacao")
    private String observacao;

    @Column(name="nome")
    private String nome;

    @Column(name="rg")
    private String rg;

    @Column(name="cpf")
    private String cpf;

    @Column(name="genero")
    private Genero genero;

    @Column(name="perfil")
    private Perfil perfil;

    @Temporal(TemporalType.DATE)
    @Column(name="data_de_nascimento")
    private LocalDate dataDeNas;
    
    @Temporal(TemporalType.DATE)
    @Column(name="data_de_cadastro")
    private LocalDate dataDeCadastro;
    
}