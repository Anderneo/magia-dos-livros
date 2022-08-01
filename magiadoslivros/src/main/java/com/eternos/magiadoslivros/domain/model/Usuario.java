package com.eternos.magiadoslivros.domain.model;

import java.time.LocalDate;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Entity
@Table(name = "usuario")
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

    @Column(name="genero")
    private Genero genero;

    @Column(name="perfil")
    private Perfil perfil;

    @Column(name="data_de_nascimento")
    private LocalDate dataDeNas;
    
    @Column(name="data_de_cadastro")
    private LocalDate dataDeCadastro;
    

    //incluirUsuario()  void
    //atualizarUsuario()  void
    //consultaUsuario()  void
    //apagarUsuario()  void
}
