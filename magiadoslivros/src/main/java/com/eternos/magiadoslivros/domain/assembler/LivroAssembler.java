package com.eternos.magiadoslivros.domain.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.request.LivroRequest;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LivroAssembler {
    private final ModelMapper modelMapper;
    private final FornecedorUtil fornecedorUtil;
    

    public Livro toModel(LivroRequest livroRequest){

        Fornecedor fornecedor = fornecedorUtil.buscarFornecedor(livroRequest.getIdFornecedor());

        // TypeMap<LivroRequest, Livro> typeMap = modelMapper.getTypeMap(
        //                                                     LivroRequest.class, 
        //                                                Livro.class);
        
        // if (typeMap == null) {

		//     modelMapper.createTypeMap(LivroRequest.class, Livro.class)
        //         .addMappings(mapper-> mapper.skip(Livro::setIdLivro))
		//         .addMapping(LivroRequest::getIdFornecedor, Livro::setIdFornecedor);

        // }

        var livroRequestModel = modelMapper.map(livroRequest, Livro.class);

        livroRequestModel.setIdFornecedor(fornecedor);

        return livroRequestModel;
    }

    public List<Livro> toCollectionModel(List<LivroRequest> livroRequest){
        //lambda functions JAVA 8
        return  livroRequest.stream()
                             .map(livro -> toModel(livro))
                             .collect(Collectors.toList());                             
    }
}