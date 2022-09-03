package com.eternos.magiadoslivros.domain.assembler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
import com.eternos.magiadoslivros.domain.util.UsuarioUtil;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PedidoAssembler {
    private final ModelMapper modelMapper;
    private final UsuarioUtil usuarioUtil;

    public Pedido toModel(PedidoRequest pedidoRequest){

        Usuario usuario = usuarioUtil.buscarId(pedidoRequest.getIdUsuario());
        
        var pedidoRequestModel = modelMapper.map(pedidoRequest, Pedido.class);

        pedidoRequestModel.setIdUsuario(usuario);

        return pedidoRequestModel;
    }

}
