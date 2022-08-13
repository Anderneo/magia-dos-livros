package com.eternos.magiadoslivros.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.PedidoLivro;
import com.eternos.magiadoslivros.domain.model.PedidoLivroId;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.repository.PedidoLivroRepository;
import com.eternos.magiadoslivros.domain.repository.PedidoRepository;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
import com.eternos.magiadoslivros.domain.assembler.PedidoAssembler;
import com.eternos.magiadoslivros.domain.assembler.PedidoLivroAssembler;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;
    private final LivroService livroService;
    private final LivroRepository livroRepository;
    private final PedidoLivroRepository pedidoLivroRepository;
    private final PedidoAssembler pedidoAssembler;
    private final PedidoLivroAssembler pedidoLivroAssembler;

    public PedidoLivro findById(PedidoLivroId pedidoLivroId){

        return pedidoLivroRepository.findById(pedidoLivroId).
                orElseThrow(new DefaultException(HttpStatus.BAD_REQUEST, 
                                        "Pedido ou produto não encontrado"));

    }


    public Pedido salvar(PedidoRequest pedidoRequest){

        var pedido = pedidoAssembler.toModel(pedidoRequest);

        pedidoRepository.save(pedido);

        ArrayList<Livro> listaLivro = new ArrayList<Livro>();

        for( int i = 0; i < pedidoRequest.getListaLivro().size(); i++) {
      
            Livro livro = livroService.buscarId(pedidoRequest.getListaLivro().get(i).getIdLivro());

            if( pedidoRequest.getListaLivro().get(i).getQuantidade() > livro.getQuantLivros()){
                throw new DefaultException(HttpStatus.BAD_REQUEST, "Sem estoque suficiente");
            }
                
            var pedidoLivroRequest =  pedidoRequest.getListaLivro().get(i);
            var pedidoLivro = pedidoLivroAssembler.toModel( pedidoLivroRequest, pedido.getIdVenda());

            pedidoLivroRepository.save(pedidoLivro);
            
            livro.setQuantLivros(livro.getQuantLivros() - pedidoRequest.getListaLivro().get(i).getQuantidade());
                    
            livroRepository.save(livro); 
            
            listaLivro.add(livro);

        }

        pedido.setListaLivro(listaLivro);

       return pedido;

    }

    public List<Pedido> buscarTodos(){
        return pedidoRepository.findAll();
    }

    public Pedido buscarId(Integer id){

        return pedidoRepository.findById(id)
            .orElseThrow(new DefaultException(
            HttpStatus.BAD_REQUEST,"Não foi encontrado pedido com esse id!!"));

    }

    public void cancelarPedido(Integer idPedido, Integer idUsuario){

        Pedido pedido = buscarId(idPedido);
        Usuario usuario = usuarioService.buscarId(idUsuario);

        if (usuario.getPerfil().toString().compareToIgnoreCase("administrador") != 0) 
                throw new DefaultException(HttpStatus.FORBIDDEN,
                 "Apenas o administrador pode cancelar um pedido!!");

        pedido.setVendaCancelada(true);

        pedidoRepository.save(pedido);

        throw new DefaultException(HttpStatus.ACCEPTED, "O pedido foi cancelado!!!");
    }
}
