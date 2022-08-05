package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.assembler.LivroAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
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

        if(livroRequest.getIdLivro() != null){

            throw new DefaultException(
                HttpStatus.BAD_REQUEST,
                 "Esse registro já existe!!");

        }

        return livroRepository.save(livroAssembler.toModel(livroRequest));

    }

    public List<Livro> buscarTodos(){

        return livroRepository.findAll();

    }

    public Livro buscarId(Integer id){

        return livroRepository.findById(id)
            .orElseThrow(new DefaultException(
            HttpStatus.BAD_REQUEST,"O registro informado não existe!!"));

    }

    public void deletar(Integer id){

        var objecto = buscarId(id);
        livroRepository.delete(objecto);
        throw new DefaultException(
                HttpStatus.ACCEPTED,
                 "Registro " + id + " deletado com sucesso!!");

    }
}
