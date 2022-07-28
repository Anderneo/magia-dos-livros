package com.eternos.magiadoslivros.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;

@Entity
@Table(name = "pedido")
@Data
@Builder
public class Pedido {
   @Id
   @Column(name="id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer idVenda;

   @Column(name="valor_de_venda", nullable = false)  
   private Double valorVenda;

   @Column(name="endereco_entrega", nullable = false)
   private String enderecoEntrega;

   @Column(name="formaDePgto", nullable = false) 
   private String formaDePgto;

   @Column(name="parcela", nullable = false)
   private Integer parcela;

   @Column(name="data_venda", nullable = false)
   private Date dataVenda;

   @Column(name="data_pagto", nullable = false)
   private Date dataPgto;

   @Column(name="data_entrega", nullable = false) 
   private Date dataEntrega;

   @ManyToOne
   @JoinColumn(name = "id_usuario")
   private Usuario idUsuario;
   
   @Column(name = "cancelada")@Default
   private Boolean vendaCancelada = false;

   public void cancelarVenda(){
        this.vendaCancelada = true;
   }
}
