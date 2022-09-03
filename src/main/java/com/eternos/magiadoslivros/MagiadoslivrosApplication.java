package com.eternos.magiadoslivros;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.model.Pedido;
import com.eternos.magiadoslivros.domain.model.PedidoLivro;
import com.eternos.magiadoslivros.domain.request.LivroRequest;
import com.eternos.magiadoslivros.domain.request.PedidoLivroRequest;
import com.eternos.magiadoslivros.domain.request.PedidoRequest;
@SpringBootApplication
public class MagiadoslivrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagiadoslivrosApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){

		var modelMapper =  new ModelMapper();

		modelMapper.createTypeMap(LivroRequest.class, Livro.class)
				.addMappings(mapper-> mapper.skip(Livro::setIdLivro))
		        .addMapping(LivroRequest::getIdFornecedor, Livro::setIdFornecedor);
		
		modelMapper.createTypeMap(PedidoRequest.class, Pedido.class)
                .addMappings(mapper-> mapper.skip(Pedido::setIdVenda))
                .addMappings(mapper-> mapper.skip(Pedido::setListaLivro))
		        .addMapping(PedidoRequest::getIdUsuario, Pedido::setIdUsuario);
		
		modelMapper.createTypeMap(PedidoLivroRequest.class, PedidoLivro.class)
                .addMappings(mapper-> mapper.skip(PedidoLivro::setId_pedido))
                .addMapping(PedidoLivroRequest::getIdLivro, PedidoLivro::setId_livro)
                .addMapping(PedidoLivroRequest::getQuantidade, PedidoLivro::setQuantidade);

		return modelMapper;

	}
}
