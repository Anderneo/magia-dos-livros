package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.assembler.PedidoAssembler;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.repository.PedidoRepository;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final PedidoAssembler pedidoAssembler;


    public Pedido salvar(PedidoRequest pedidoRequest){

        return pedidoRepository.save(pedidoAssembler.toModel(pedidoRequest));

    }

    public List<Pedido> buscarTodos(){
        return pedidoRepository.findAll();
    }
}
