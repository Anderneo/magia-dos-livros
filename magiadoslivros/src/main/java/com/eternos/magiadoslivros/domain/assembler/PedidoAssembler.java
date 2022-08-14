package com.eternos.magiadoslivros.domain.assembler;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.util.UsuarioUtil;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PedidoAssembler {
    private final ModelMapper modelMapper;
    private final UsuarioUtil usuarioUtil;

    public Pedido toModel(PedidoRequest pedidoRequest){

        Usuario usuario = usuarioUtil.buscarId(pedidoRequest.getIdUsuario());

        TypeMap<PedidoRequest, Pedido> typeMap = modelMapper.getTypeMap(
                                                            PedidoRequest.class, 
                                                       Pedido.class);
        
        if (typeMap == null) {

		    modelMapper.createTypeMap(PedidoRequest.class, Pedido.class)
                .addMappings(mapper-> mapper.skip(Pedido::setIdVenda))
                .addMappings(mapper-> mapper.skip(Pedido::setListaLivro))
		        .addMapping(PedidoRequest::getIdUsuario, Pedido::setIdUsuario);

        }

        var pedidoRequestModel = modelMapper.map(pedidoRequest, Pedido.class);

        pedidoRequestModel.setIdUsuario(usuario);;

        return modelMapper.map(pedidoRequestModel, Pedido.class);
    }

    public List<Pedido> toCollectionModel(List<PedidoRequest> pedidoRequest){
        //lambda functions JAVA 8
        return  pedidoRequest.stream()
                             .map(pedido -> toModel(pedido))
                             .collect(Collectors.toList());                             
    }
}
