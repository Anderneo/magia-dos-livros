package com.eternos.magiadoslivros.domain.request;

import java.time.LocalDate;

import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.Usuario;


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

    public Pedido converterClasse(){
        return Pedido.builder()
        .valorVenda(this.valorVenda)
        .enderecoEntrega(this.enderecoEntrega)
        .formaDePgto(this.formaDePgto)
        .parcela(this.parcela)
        .dataVenda(this.dataVenda)
        .dataPgto(this.dataPgto)
        .dataEntrega(this.dataEntrega)
        .vendaCancelada(this.vendaCancelada)
        .idUsuario(this.idUsuario)
        .build();
    }
}
