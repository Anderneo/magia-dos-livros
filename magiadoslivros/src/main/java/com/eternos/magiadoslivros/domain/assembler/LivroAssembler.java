package com.eternos.magiadoslivros.domain.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.request.LivroRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LivroAssembler {
    private final ModelMapper modelMapper;

    public Livro toModel(LivroRequest livroRequest){
        
        return modelMapper.map(livroRequest, Livro.class);
        
    }

    public List<Livro> toCollectionModel(List<LivroRequest> livroRequest){
        //lambda functions JAVA 8
        return  livroRequest.stream()
                             .map(livro -> toModel(livro))
                             .collect(Collectors.toList());                             
    }
}