package com.eternos.magiadoslivros.domain.model;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "pedido_livro")
@Builder
@AllArgsConstructor
@Data
public class PedidoLivro{
    
    @Builder.Default
    @EmbeddedId
    private PedidoLivroId pedidoLivroId = new PedidoLivroId();
    
    @Transient
    private Integer id_pedido;
    
    @Transient
    private Integer id_livro;
   
    @Column(name="quantidade")
    private Integer quantidade;

    public PedidoLivro(){}
}
