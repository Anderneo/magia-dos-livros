package com.eternos.magiadoslivros.domain.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PedidoRequest {
    
    @NotNull(message = "O Campo Valor de Venda não pode estar vazio")
    @PositiveOrZero
    Double valorVenda;
    
    @NotNull
    @NotBlank(message = "O Campo Forma de Pagamento não pode estar vazio")
    String enderecoEntrega;
    
    @NotNull
    @NotBlank(message = "O Campo Forma de Pagamento não pode estar vazio")
    String formaDePgto;
    
    @NotNull(message = "O Campo Quantidade de Parcelas não pode estar vazio")
    @PositiveOrZero
    Integer parcela;
    
    @DateTimeFormat(fallbackPatterns = { "dd/MM/yyyy" })
    LocalDate dataVenda;
    
    @DateTimeFormat(fallbackPatterns = { "dd/MM/yyyy" })
    LocalDate dataPgto;
    
    @DateTimeFormat(fallbackPatterns = { "dd/MM/yyyy" })
    LocalDate dataEntrega;
    
    @NotNull(message = "O idUsuario não pode estar vazio")
    @PositiveOrZero
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
