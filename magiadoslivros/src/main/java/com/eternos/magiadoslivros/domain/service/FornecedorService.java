package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.assembler.FornecedorAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.repository.FornecedorRepository;
import com.eternos.magiadoslivros.domain.request.FornecedorRequest;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;

import org.springframework.beans.BeanUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FornecedorService {
  
    private final FornecedorRepository fornecedorRepository;
    private final FornecedorAssembler fornecedorAssembler;
    private final FornecedorUtil fornecedorUtil;


    public Fornecedor salvar(FornecedorRequest fornecedorRequest){

        Fornecedor fornecedor = fornecedorAssembler.toModel(fornecedorRequest);

        fornecedorUtil.checarConstraintFornecedor(fornecedor);       

        return fornecedorRepository.save(fornecedor);

    }

    public List<Fornecedor> buscarTodos(){

        return fornecedorRepository.findAll();

    }

    public Fornecedor buscarRazaoSocial(String razaosocial){
            
        return fornecedorRepository.findByRazaoSocial(razaosocial)
            .orElseThrow(new DefaultException(HttpStatus.NOT_FOUND, 
                    "Não foi possivel encontrar nenhum fornecedor com Razão Social: " + razaosocial));

    }

    public Fornecedor buscarCnpj(String cnpj){
            
        return fornecedorRepository.findByCnpj(cnpj)
            .orElseThrow(new DefaultException(HttpStatus.NOT_FOUND, 
            "Não foi possivel encontrar nenhum fornecedor com CNPJ: " + cnpj));

    }

    
    public void deletar(Integer id){

        var objeto = fornecedorUtil.buscarFornecedor(id);

        fornecedorRepository.delete(objeto);

    }

    public Fornecedor atualizarFornecedor(Integer id, FornecedorRequest fornecedorRequest){

        var entity = fornecedorUtil.buscarFornecedor(id);

        BeanUtils.copyProperties(fornecedorRequest, entity, "id");

        return fornecedorRepository.save(entity);

    }

}
