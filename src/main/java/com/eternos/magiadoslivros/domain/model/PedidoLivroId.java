package com.eternos.magiadoslivros.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoLivroId implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id_livro;
    private Integer id_pedido;
}
