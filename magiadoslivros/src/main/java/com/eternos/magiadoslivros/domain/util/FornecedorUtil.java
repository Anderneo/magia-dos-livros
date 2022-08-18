package com.eternos.magiadoslivros.domain.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.repository.FornecedorRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FornecedorUtil {

    private final FornecedorRepository fornecedorRepository;
    

    public void checarConstraintFornecedor(Fornecedor fornecedor){

        if (fornecedorRepository.findByCnpj(fornecedor.getCnpj()).isPresent())
            throw new DefaultException(HttpStatus.FOUND, 
                                            "Já existe um registro com CNPJ: " 
                                            + fornecedor.getCnpj());
                            
        if (fornecedorRepository.findByRazaoSocial(fornecedor.getRazaoSocial()).isPresent())
            throw new DefaultException(HttpStatus.FOUND, 
                                            "Já existe um registro com razao social: " 
                                            + fornecedor.getRazaoSocial());

    }

    public Fornecedor buscarFornecedor(Integer id){

        return fornecedorRepository.findById(id)
            .orElseThrow(new DefaultException(HttpStatus.NOT_FOUND,
                                             "Não foi encontrado nenhum fornecedor com id: " + id));  

    }

}
