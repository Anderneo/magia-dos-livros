package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.assembler.FornecedorAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.repository.FornecedorRepository;
import com.eternos.magiadoslivros.domain.request.FornecedorRequest;
import org.springframework.beans.BeanUtils;

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

    public Fornecedor buscarRazaoSocial(String razaosocial){
            
        return fornecedorRepository.findByRazaoSocial(razaosocial)
            .orElseThrow(new DefaultException(HttpStatus.NOT_FOUND, 
                    "Não foi possivel encontrar nenhum registro !!!"));

    }

    public Fornecedor buscarCnpj(String cnpj){
            
        return fornecedorRepository.findByCnpj(cnpj)
            .orElseThrow(new DefaultException(HttpStatus.NOT_FOUND, 
                    "Não foi possivel encontrar nenhum registro !!!"));

    }

    public Fornecedor buscarPorIdOuFalhar(Integer id){
        return fornecedorRepository.findById(id)
        .orElseThrow(new DefaultException(HttpStatus.BAD_REQUEST,"O fornecedor informado não existe"));  
    }
    
    public void deletar(Integer id){
        var objeto = buscarPorIdOuFalhar(id);
        fornecedorRepository.delete(objeto);
    }

    public Fornecedor atualizarFornecedor(Integer id, FornecedorRequest fornecedorRequest){

        var entity = buscarPorIdOuFalhar(id);         
        BeanUtils.copyProperties(fornecedorRequest, entity, "id");

        return fornecedorRepository.save(entity);


    }

}
