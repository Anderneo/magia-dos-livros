package com.eternos.magiadoslivros.domain.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.eternos.magiadoslivros.domain.model.enums.Genero;
import com.eternos.magiadoslivros.domain.model.enums.Perfil;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioRequest {

    String endereco;
    String email;
    String telefone;
    String observacao;
    String nome;
    String rg;
    String cpf;
    Genero genero;
    Perfil perfil;
    LocalDate dataDeNas;
    LocalDate dataDeCadastro;

    public UsuarioRequest(String endereco, String email, String telefone, 
                      String observacao, String nome, String rg, String cpf,Genero genero, 
                      Perfil perfil, String dataDeNas, String dataDeCadastro){
    this.endereco = endereco;
    this.email = email;
    this.telefone = telefone;
    this.observacao = observacao;
    this.nome = nome;
    this.rg = rg;
    this.cpf = cpf;
    this.genero = genero;
    this.perfil = perfil;
    this.dataDeNas = toDate(dataDeNas);
    this.dataDeCadastro = toDate(dataDeCadastro);
    }

    private final LocalDate toDate(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, formatter);
    }

}



