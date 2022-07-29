package com.eternos.magiadoslivros.domain.request;

import java.time.LocalDate;

import com.eternos.magiadoslivros.domain.model.Genero;
import com.eternos.magiadoslivros.domain.model.Perfil;
import com.eternos.magiadoslivros.domain.model.Usuario;

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

    public Usuario converterClasse(){
        return Usuario.builder()
        .endereco(this.endereco)
        .email(this.email)
        .telefone(this.telefone)
        .observacao(this.observacao)
        .nome(this.nome)
        .rg(this.rg)
        .genero(this.genero)
        .perfil(this.perfil)
        .dataDeNas(this.dataDeNas)
        .dataDeCadastro(this.dataDeCadastro)
        .build();
    }
}
