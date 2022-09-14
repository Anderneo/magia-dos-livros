package com.eternos.magiadoslivros.domain.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarrinhoDeCompras {
    private Integer idCarrinho;
    private Integer idLivro;
    private Integer qtLivro;

    //addItemCar()  void
    //atualizarQuant()  void
    //verDetalheCarrinho()  void
    //prosseguirCompra()  void
}
