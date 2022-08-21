package com.eternos.magiadoslivros.domain.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.eternos.magiadoslivros.domain.model.enums.Genero;
import com.eternos.magiadoslivros.domain.model.enums.Perfil;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioRequest {

    String endereco;
    
    @Email(message = "O Campo Email é inválido")
    String email;
    
    @Pattern(regexp="(^$|[0-9]{9,11})", message = "O Campo telefone é inválido")
    String telefone;
    
    String observacao;
    
    @NotNull
    @NotBlank(message = "O Campo Nome não pode estar vazio")
    String nome;
    
    @Pattern(regexp="(^$|[0-9.-]{9})", message = "O Campo RG é inválido")
    String rg;
    
    @CPF(message = "O Campo CPF é inválido")
    String cpf;
    
    @NotNull(message = "O Campo Perfil não pode estar vazio")
    Genero genero;
    
    @NotNull(message = "O Campo Perfil não pode estar vazio")
    Perfil perfil;
    
    @DateTimeFormat(fallbackPatterns = { "dd/MM/yyyy" })
    LocalDate dataDeNas;
    
    @DateTimeFormat(fallbackPatterns = { "dd/MM/yyyy" })
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



