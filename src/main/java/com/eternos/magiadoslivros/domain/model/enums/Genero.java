package com.eternos.magiadoslivros.domain.model.enums;

public enum Genero {
    HOMEMCIS("Homem cisgênero"),
    MULHERCIS("Mulher cisgênero"),
    HOMEMTRANS("Homem transgênero"),
    MULHERTRANS("Mulher transgênero"),
    NAOBINARIO("Não-binário"),
    INTERSEXO("Intersexo"),
    AGENERO("Agênero"),
    OUTRO("Outro gênero não listado");

    private String descricao;

    Genero (String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}