package com.eternos.magiadoslivros.domain.assembler;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PedidoAssembler {
    private final ModelMapper modelMapper;

    public Pedido toModel(PedidoRequest pedidoRequest){
        return modelMapper.map(pedidoRequest, Pedido.class);
    }

    public List<Pedido> toCollectionModel(List<PedidoRequest> pedidoRequest){
        //lambda functions JAVA 8
        return  pedidoRequest.stream()
                             .map(pedido -> toModel(pedido))
                             .collect(Collectors.toList());                             
    }
}
