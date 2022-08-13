package com.eternos.magiadoslivros.domain.assembler;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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

        TypeMap<PedidoLivroRequest, PedidoLivro> typeMap = modelMapper.getTypeMap(
                                                            PedidoLivroRequest.class, 
                                                       PedidoLivro.class);

        if (typeMap == null) {

            modelMapper.createTypeMap(PedidoLivroRequest.class, PedidoLivro.class)
                .addMappings(mapper-> mapper.skip(PedidoLivro::setId_pedido))
                .addMapping(PedidoLivroRequest::getIdLivro, PedidoLivro::setId_livro)
                .addMapping(PedidoLivroRequest::getQuantidade, PedidoLivro::setQuantidade);

        }

        var pedidoRequestModel = modelMapper.map(pedidoLivroRequest, PedidoLivro.class);

        pedidoRequestModel.setId_pedido(pedidoIdVenda);

        pedidoRequestModel.setPedidoLivroId(pedidoLivroId);

        return pedidoRequestModel;
    }
}
