package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.repository.PedidoRepository;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
import com.eternos.magiadoslivros.domain.util.PedidoUtil;
import com.eternos.magiadoslivros.domain.util.UsuarioUtil;
import com.eternos.magiadoslivros.domain.assembler.PedidoAssembler;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;
    private final PedidoAssembler pedidoAssembler;
    private final UsuarioUtil usuarioUtil;
    private final PedidoUtil pedidoUtil;

    /* public PedidoLivro findById(PedidoLivroId pedidoLivroId){

        return pedidoLivroRepository.findById(pedidoLivroId).
                orElseThrow(new DefaultException(HttpStatus.BAD_REQUEST, 
                                        "Pedido ou produto não encontrado"));

    } */


    public Pedido salvar(PedidoRequest pedidoRequest){

        var pedido = pedidoAssembler.toModel(pedidoRequest);

        pedidoRepository.save(pedido);

        pedido.setListaLivro(pedidoUtil.listaLivro(pedido, pedidoRequest));

        return pedido;
        
    }
    
    public List<Pedido> buscarTodos(){

        return pedidoRepository.findAll();

    }

    public void cancelarPedido(Integer idPedido, Integer idUsuario){

        Pedido pedido = pedidoUtil.buscarId(idPedido);
        Usuario usuario = usuarioService.buscarId(idUsuario);

        usuarioUtil.checarUsuario(usuario);

        if (pedido.getVendaCancelada() == true) throw new DefaultException(HttpStatus.ALREADY_REPORTED, "O pedido já foi cancelado!!!");
        
        pedido.setVendaCancelada(true);

        pedidoRepository.save(pedido);

        throw new DefaultException(HttpStatus.ACCEPTED, "O pedido foi cancelado!!!");
    }
}
