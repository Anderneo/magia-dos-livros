package com.eternos.magiadoslivros.domain.request;

import lombok.Data;

@Data
public class PedidoLivroRequest {
    private Integer idLivro;
    private Integer quantidade;
}
