package com.eternos.magiadoslivros.domain.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder
//@Entity
@Table(name = "Usuario")
public class Usuario extends Pessoa{

    @Column(name="nome")
    private String nome;

    @Column(name="rg")
    private String rg;

    @Column(name="cpf")
    protected String cpf; 

    @Column(name="genero")
    private Genero genero;

    @Column(name="perfil")
    private Perfil perfil;

    @Column(name="doador")
    private Boolean doador;

    @Column(name="dataDeNas")
    private Date dataDeNas;
    
    @Column(name="dataDeCadastro")
    private Date dataDeCadastro;
    

    //incluirUsuario()  void
    //atualizarUsuario()  void
    //consultaUsuario()  void
    //apagarUsuario()  void
}
