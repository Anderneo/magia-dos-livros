package com.eternos.magiadoslivros.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "pedido")
@Data
@Builder
@AllArgsConstructor
public class Pedido{

   @Id
   @Column(name="id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer idVenda;

   @Column(name="valor_de_venda", nullable = false)  
   private Double valorVenda;

   @Column(name="endereco_entrega", nullable = false)
   private String enderecoEntrega;

   @Column(name="forma_de_pagto", nullable = false) 
   private String formaDePgto;

   @Column(name="parcela", nullable = false)
   private Integer parcela;

   @Column(name="data_venda", nullable = false)
   private LocalDate dataVenda;

   @Column(name="data_pagto", nullable = false)
   private LocalDate dataPgto;

   @Column(name="data_entrega", nullable = false) 
   private LocalDate dataEntrega;

   @ManyToOne
   @JoinColumn(name = "id_usuario")
   private Usuario idUsuario;
   
   @Builder.Default
   @Column(name = "cancelada", columnDefinition="boolean default false")
   private Boolean vendaCancelada = false;

   @Builder.Default
   @ManyToMany
   @JoinTable(name="pedido_livro",
   joinColumns = {@JoinColumn(name="id_pedido")},
   inverseJoinColumns = {@JoinColumn(name="id_livro")})
   private List<Livro> listaLivro = new ArrayList<>();

   public void cancelarVenda(){
      this.vendaCancelada = true;
   }

}
