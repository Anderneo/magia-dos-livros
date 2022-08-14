package com.eternos.magiadoslivros.domain.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LivroUtil {

    private final LivroRepository livroRepository;

    public Livro buscarId(Integer id){

        return livroRepository.findById(id)
            .orElseThrow(new DefaultException(
            HttpStatus.BAD_REQUEST,"Não foi encontrado nenhum liro com id: " + id));

    }

    public void checarIsbn(String isbn){

        if(livroRepository.findByIsbn(isbn) != null) throw new DefaultException(
                                                                 HttpStatus.BAD_REQUEST,
                                                        "ISBN já existe!!");
                                                        
    }

}
