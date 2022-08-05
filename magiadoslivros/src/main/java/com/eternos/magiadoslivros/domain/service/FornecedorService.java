package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.assembler.FornecedorAssembler;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.repository.FornecedorRepository;
import com.eternos.magiadoslivros.domain.request.FornecedorRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FornecedorService {
  
    private final FornecedorRepository fornecedorRepository;
    private final FornecedorAssembler fornecedorAssembler;


    public Fornecedor salvar(FornecedorRequest fornecedorRequest){

        return fornecedorRepository.save(fornecedorAssembler.toModel(fornecedorRequest));

    }

    public List<Fornecedor> buscarTodos(){
        return fornecedorRepository.findAll();
    }

}
