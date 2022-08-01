package com.eternos.magiadoslivros.domain.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.repository.FornecedorRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Service
@AllArgsConstructor
@Builder
public class FornecedorService {

    private FornecedorRepository fornecedorRepository;

    public Fornecedor buscarPorIdOuFalhar(Integer id){

        return fornecedorRepository.findById(id).get();

        // .orElseThrow(new DefaultException(HttpStatus.BAD_REQUEST,"O fornecedor informado não existe"));

    }

    public FornecedorService(){}
}
