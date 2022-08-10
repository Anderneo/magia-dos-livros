package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.assembler.PedidoAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.repository.PedidoRepository;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final PedidoAssembler pedidoAssembler;
    private final UsuarioService usuarioService;


    public Pedido salvar(PedidoRequest pedidoRequest){

        return pedidoRepository.save(pedidoAssembler.toModel(pedidoRequest));

    }

    public List<Pedido> buscarTodos(){
        return pedidoRepository.findAll();
    }

    public Pedido buscarId(Integer id){

        return pedidoRepository.findById(id)
            .orElseThrow(new DefaultException(
            HttpStatus.BAD_REQUEST,"O registro informado não existe!!"));

    }

    public void cancelarPedido(Integer idPedido, Integer idUsuario){

        Pedido pedido = buscarId(idPedido);
        Usuario usuario = usuarioService.buscarId(idUsuario);


        if (usuario.getPerfil().toString().compareToIgnoreCase("administrador") != 0) 
                throw new DefaultException(HttpStatus.FORBIDDEN,
                 "Apenas o administrador pode cancelar um pedido!!");

        pedido.setVendaCancelada(true);

        throw new DefaultException(HttpStatus.ACCEPTED, "O pedido foi cancelado!!!");
    }
}
