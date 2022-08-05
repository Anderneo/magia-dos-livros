package com.eternos.magiadoslivros.domain.model.enums;

public enum Perfil {
    ADMINISTRADOR("Administrador"),
    USUARIO("Usuário");

    private String descricao;

    Perfil (String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

}

