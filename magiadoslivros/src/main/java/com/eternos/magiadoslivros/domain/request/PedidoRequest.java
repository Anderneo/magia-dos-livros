package com.eternos.magiadoslivros.domain.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PedidoRequest {
    
    Double valorVenda;
    String enderecoEntrega;
    String formaDePgto;
    Integer parcela;
    LocalDate dataVenda;
    LocalDate dataPgto;
    LocalDate dataEntrega;
    Integer idUsuario;
    ArrayList<PedidoLivroRequest> listaLivro;

    public PedidoRequest(Double valorVenda, String enderecoEntrega, String formaDePgto, 
                          Integer parcela, String dataVenda, String dataPgto, String dataEntrega,
                          Integer idUsuario){
        this.valorVenda = valorVenda;
        this.enderecoEntrega = enderecoEntrega;
        this.formaDePgto = formaDePgto;
        this.parcela = parcela;
        this.dataVenda = toDate(dataVenda);
        this.dataPgto = toDate(dataPgto);
        this.dataEntrega = toDate(dataEntrega);
        this.idUsuario = idUsuario;
    }

    private final LocalDate toDate(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, formatter);
    }

}
