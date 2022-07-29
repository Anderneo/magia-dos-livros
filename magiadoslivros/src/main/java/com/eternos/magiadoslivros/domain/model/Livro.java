package com.eternos.magiadoslivros.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "livro")
@Builder
@Data
@AllArgsConstructor
public class Livro implements Serializable{

    private static final long serialVersionUID = 1L;

    public Livro(){}
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivro;

    @Column(name="localizacao_estoque")
    private String tagEstoque;

    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @Column(name="isbn")
    private Integer isbn;

    @Column(name="quantidade_livro")
    private Integer quantLivros;

    @Column(name="valor_recebimento")
    private Double valorRecebimento;
    
    @Column(name="valor_venda")
    private Double valorVenda;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;

    @ManyToMany(mappedBy="listaLivro")
    private List<Pedido> listaPedido;

    //incluirLivro()  void
    //atualizarLivro()  void
    //apagarLivro()  void
    //consultaLivro()  void
}
