package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.assembler.UsuarioAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Usuario;
import com.eternos.magiadoslivros.domain.repository.UsuarioRepository;
import com.eternos.magiadoslivros.domain.request.UsuarioRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final UsuarioAssembler usuarioAssembler;


    public Usuario salvar(UsuarioRequest usuarioRequest){

        Usuario usuario = usuarioAssembler.toModel(usuarioRequest);

        checarConstraintUsuario(usuario);

        return usuarioRepository.save(usuario);

    }

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    public void checarConstraintUsuario(Usuario usuario){

        if (usuarioRepository.findByRg(usuario.getRg()).isPresent())
            throw new DefaultException(HttpStatus.FOUND, 
                                            "Já existe um registro com RG: " 
                                            + usuario.getRg());
                            
        if (usuarioRepository.findByCpf(usuario.getCpf()).isPresent())
            throw new DefaultException(HttpStatus.FOUND, 
                                            "Já existe um registro com CPF: " 
                                            + usuario.getCpf());

    }

    public Usuario buscarId(Integer id){

        return usuarioRepository.findById(id)
            .orElseThrow(new DefaultException(
            HttpStatus.BAD_REQUEST,"O registro informado não existe!!"));

    }

    public Usuario  atualizarUsuario(Integer id, UsuarioRequest usuarioRequest){

        Usuario usuario = buscarId(id);

        Usuario request = usuarioAssembler.toModel(usuarioRequest);
     
        BeanUtils.copyProperties(request, usuario, "id");

        return usuarioRepository.save(usuario);

    }

    public Usuario buscarCpf(String cpf){
            
        return usuarioRepository.findByCpf(cpf)
            .orElseThrow(new DefaultException(HttpStatus.NOT_FOUND, 
                    "Não foi possivel encontrar nenhum registro com esse CPF!!"));

    }

    public List<Usuario> buscarNome(String nome){

        List<Usuario> usuario = usuarioRepository.findByNomeContainingIgnoreCase(nome);

        if(usuario.isEmpty()) throw new DefaultException(HttpStatus.NOT_FOUND, 
                     "Não foi possivel encontrar nenhum registro com esse NOME!!");

        return usuario;

    }

    public void deletar(Integer id){

        var objecto = buscarId(id);

        usuarioRepository.delete(objecto);

        throw new DefaultException(
                                    HttpStatus.ACCEPTED,
                                    "Registro " + id + " deletado com sucesso!!");
    }

}
