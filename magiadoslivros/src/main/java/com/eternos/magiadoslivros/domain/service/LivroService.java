package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.assembler.LivroAssembler;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.request.LivroRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LivroService {
    
    private final LivroRepository livroRepository;
    private final LivroAssembler livroAssembler;


    public Livro salvar(LivroRequest livroRequest){

        return livroRepository.save(livroAssembler.toModel(livroRequest));

    }

    public List<Livro> buscarTodos(){
        return livroRepository.findAll();
    }
}
