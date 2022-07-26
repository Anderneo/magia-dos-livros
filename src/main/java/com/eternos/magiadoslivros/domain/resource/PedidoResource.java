package com.eternos.magiadoslivros.domain.resource;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
import com.eternos.magiadoslivros.domain.service.PedidoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "pedido")
@AllArgsConstructor
public class PedidoResource {

    private final PedidoService pedidoService;

    @PostMapping
    public Pedido salvar(@RequestBody @Valid PedidoRequest pedidoRequest){
        return pedidoService.salvar(pedidoRequest);
    }

    @PatchMapping(path = "cancelar/pedido/{idPedido}")
    public void cancelarPedido(@Valid @PathVariable("idPedido") Integer idPedido, 
                               @RequestBody PedidoRequest idUsuario){

        pedidoService.cancelarPedido(idPedido, idUsuario);

    }

    @GetMapping(path = "todos")
    public List<Pedido> buscar() {
        return pedidoService.buscarTodos();
    }


}
