package com.eternos.magiadoslivros.domain.request;

import java.time.LocalDate;

import com.eternos.magiadoslivros.domain.model.enums.Genero;
import com.eternos.magiadoslivros.domain.model.enums.Perfil;

import lombok.Data;
@Data
public class UsuarioRequest {

    private String endereco;
    private String email;
    private String telefone;
    private String observacao;
    private String nome;
    private String rg;
    private Genero genero;
    private Perfil perfil;
    private LocalDate dataDeNas;
    private LocalDate dataDeCadastro;

}
