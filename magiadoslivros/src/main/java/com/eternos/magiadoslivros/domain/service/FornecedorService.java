package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.repository.FornecedorRepository;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRepository fornecedorRepository;

    public Fornecedor buscarPorIdOuFalhar(Integer id){

        var teamu =  fornecedorRepository.findById(id);
        return teamu.get();
        // .orElseThrow(new DefaultException(HttpStatus.BAD_REQUEST,"O fornecedor informado não existe"));

    }

    public List<Fornecedor> buscarTodos(){
        return fornecedorRepository.findAll();
    }
    
    public FornecedorService(){}
}
