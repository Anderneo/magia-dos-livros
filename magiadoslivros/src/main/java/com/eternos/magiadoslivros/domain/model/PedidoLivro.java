package com.eternos.magiadoslivros.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "pedido_livro")
@Data
public class PedidoLivro implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id_pedido")
    private Integer id_pedido;
    @Id
    @Column(name="id_livro")
    private Integer id_livro;
    @Column(name="quantidade")
    private Integer quantidade;
}
