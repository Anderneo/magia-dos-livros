package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
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

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;
    private final LivroService livroService;
    private final LivroRepository livroRepository;
    private final PedidoLivroRepository pedidoLivroRepository;

    public PedidoLivro findById(PedidoLivroId pedidoLivroId){

        return pedidoLivroRepository.findById(pedidoLivroId).
                orElseThrow(new DefaultException(HttpStatus.BAD_REQUEST, 
                                        "Pedido ou produto não encontrado"));

    }


    public Pedido salvar(PedidoRequest pedidoRequest){

        Usuario usuario = usuarioService.buscarId(pedidoRequest.getIdUsuario());

        Pedido pedido = Pedido.builder()
                              .valorVenda(pedidoRequest.getValorVenda())
                              .enderecoEntrega(pedidoRequest.getEnderecoEntrega())
                              .formaDePgto(pedidoRequest.getFormaDePgto())
                              .parcela(pedidoRequest.getParcela())
                              .dataVenda(pedidoRequest.getDataVenda())
                              .dataPgto(pedidoRequest.getDataPgto())
                              .dataEntrega(pedidoRequest.getDataEntrega())
                              .vendaCancelada(pedidoRequest.getVendaCancelada())
                              .idUsuario(usuario)
                              .build();

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        JSONObject obj = new JSONObject(pedidoRequest);
        JSONArray listaLivroArray = obj.getJSONArray("listaLivro");

        for(int i = 0; i < listaLivroArray.length(); i++) {

            JSONObject livroPedidoJSON = listaLivroArray.getJSONObject(i);
            Integer idLivro = livroPedidoJSON.getInt("idLivro");
            Integer quantidade = livroPedidoJSON.getInt("quantidade");               
            Livro livro = livroService.buscarId(idLivro);

            if( quantidade > livro.getQuantLivros()){
                throw new DefaultException(HttpStatus.BAD_REQUEST, "Sem estoque suficiente");
            }
            else {

                PedidoLivroId pedidoLivroId = new PedidoLivroId(livro.getIdLivro(), 
                                                                pedidoSalvo.getIdVenda());

                PedidoLivro pedidoLivro = PedidoLivro.builder()
                                                     .pedidoLivroId(pedidoLivroId)
                                                     .id_livro(idLivro)
                                                     .id_pedido(pedidoSalvo.getIdVenda())
                                                     .quantidade(quantidade)
                                                     .build();
                
                pedidoLivroRepository.save(pedidoLivro);
                livro.setQuantLivros(livro.getQuantLivros() - quantidade);
                livroRepository.save(livro); 
                
            }     
        
        }

       return pedidoSalvo;

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
