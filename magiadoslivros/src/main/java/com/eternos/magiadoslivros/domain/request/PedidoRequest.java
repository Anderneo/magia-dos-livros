package com.eternos.magiadoslivros.domain.request;

import java.time.LocalDate;

import com.eternos.magiadoslivros.domain.model.Usuario;

import lombok.Data;

@Data
public class PedidoRequest {
    
    Double valorVenda;
    String enderecoEntrega;
    String formaDePgto;
    Integer parcela;
    LocalDate dataVenda;
    LocalDate dataPgto;
    LocalDate dataEntrega;
    Boolean vendaCancelada;
    Usuario idUsuario;

}
