package com.eternos.magiadoslivros.domain.request;

import java.time.LocalDate;

import com.eternos.magiadoslivros.domain.model.enums.Genero;
import com.eternos.magiadoslivros.domain.model.enums.Perfil;

import lombok.Data;
@Data
public class UsuarioRequest {

    String endereco;
    String email;
    String telefone;
    String observacao;
    String nome;
    String rg;
    Genero genero;
    Perfil perfil;
    LocalDate dataDeNas;
    LocalDate dataDeCadastro;

}
