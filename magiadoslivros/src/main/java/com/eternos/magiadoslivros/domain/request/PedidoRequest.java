package com.eternos.magiadoslivros.domain.request;

import java.time.LocalDate;

import com.eternos.magiadoslivros.domain.model.Usuario;

import lombok.Data;

@Data
public class PedidoRequest {
    private Double valorVenda;
    private String enderecoEntrega;
    private String formaDePgto;
    private Integer parcela;
    private LocalDate dataVenda;
    private LocalDate dataPgto;
    private LocalDate dataEntrega;
    private Boolean vendaCancelada;
    private Usuario idUsuario;

}
