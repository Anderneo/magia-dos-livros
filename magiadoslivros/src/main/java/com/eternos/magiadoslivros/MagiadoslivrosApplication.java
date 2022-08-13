package com.eternos.magiadoslivros;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class MagiadoslivrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagiadoslivrosApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){

		var modelMapper =  new ModelMapper();

		// modelMapper.createTypeMap(LivroRequest.class, Livro.class)
		// .addMapping(LivroRequest::getIdFornecedor, Livro::setIdFornecedor);
		

		return modelMapper;

	}
}
