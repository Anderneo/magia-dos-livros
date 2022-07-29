package com.eternos.magiadoslivros.domain.model;

import java.time.LocalDate;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder
@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa{

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
