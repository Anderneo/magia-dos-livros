package com.eternos.magiadoslivros.domain.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.request.FornecedorRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FornecedorAssembler {
    private final ModelMapper modelMapper;

    public Fornecedor toModel(FornecedorRequest fornecedorRequest){
        return modelMapper.map(fornecedorRequest, Fornecedor.class);
    }
}
