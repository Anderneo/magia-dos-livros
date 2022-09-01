package com.eternos.magiadoslivros.domain.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.model.PedidoLivro;
import com.eternos.magiadoslivros.domain.model.PedidoLivroId;
import com.eternos.magiadoslivros.domain.request.PedidoLivroRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PedidoLivroAssembler {
    private final ModelMapper modelMapper;

    public PedidoLivro toModel(PedidoLivroRequest pedidoLivroRequest, Integer pedidoIdVenda){

        PedidoLivroId pedidoLivroId = new PedidoLivroId(pedidoLivroRequest.getIdLivro(), pedidoIdVenda);

        var pedidoRequestModel = modelMapper.map(pedidoLivroRequest, PedidoLivro.class);

        pedidoRequestModel.setId_pedido(pedidoIdVenda);

        pedidoRequestModel.setPedidoLivroId(pedidoLivroId);

        return pedidoRequestModel;
    }
}
