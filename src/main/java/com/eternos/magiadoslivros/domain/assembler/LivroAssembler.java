package com.eternos.magiadoslivros.domain.assembler;

import org.modelmapper.ModelMapper;
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

        var livroRequestModel = modelMapper.map(livroRequest, Livro.class);

        livroRequestModel.setIdFornecedor(fornecedor);

        return livroRequestModel;
    }
}