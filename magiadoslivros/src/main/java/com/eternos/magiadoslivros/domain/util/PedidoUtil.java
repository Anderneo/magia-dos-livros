package com.eternos.magiadoslivros.domain.util;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.assembler.PedidoLivroAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.repository.PedidoLivroRepository;
import com.eternos.magiadoslivros.domain.repository.PedidoRepository;
import com.eternos.magiadoslivros.domain.request.PedidoLivroRequest;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PedidoUtil {

    private final PedidoLivroAssembler pedidoLivroAssembler;
    private final LivroUtil livroUtil;
    private final LivroRepository livroRepository;
    private final PedidoLivroRepository pedidoLivroRepository;
    private final PedidoRepository pedidoRepository;
    

    public ArrayList<Livro> listaLivro(Pedido pedido, PedidoRequest pedidoRequest){

        ArrayList<PedidoLivroRequest>  getListaLivro = pedidoRequest.getListaLivro();

        ArrayList<Livro> listaLivro = new ArrayList<Livro>();

        for( int i = 0; i < getListaLivro.size(); i++) {
      
            Livro livro = livroUtil.buscarId(getListaLivro.get(i).getIdLivro());

            checarEstoque(getListaLivro, livro, i);

            pedidoLivroRepository.save(pedidoLivroAssembler.toModel( getListaLivro.get(i), pedido.getIdVenda()));
            
            livro.setQuantLivros(livro.getQuantLivros() - getListaLivro.get(i).getQuantidade());
                    
            livroRepository.save(livro); 
            
            listaLivro.add(livro);

        }

        return listaLivro;
    }

    public Pedido buscarId(Integer id){

        return pedidoRepository.findById(id)
            .orElseThrow(new DefaultException(
            HttpStatus.BAD_REQUEST,"Não foi encontrado pedido com esse id!!"));

    }

    public void checarEstoque(ArrayList<PedidoLivroRequest>  getListaLivro, Livro livro, int index){

        if( getListaLivro.get(index).getQuantidade() > livro.getQuantLivros())
            throw new DefaultException(HttpStatus.BAD_REQUEST, "Sem estoque suficiente");
        

    }

}
