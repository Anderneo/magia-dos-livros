package com.eternos.magiadoslivros.LivroTeste;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.assembler.LivroAssembler;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.request.LivroRequest;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;

@SpringBootTest
public class LivroAssemblerTeste {

    @InjectMocks
    private LivroAssembler livroAssembler;

    @Mock
    private FornecedorUtil fornecedorUtil;

    @Mock
    private LivroRequest livroRequest;

    @Mock
    private Livro livro;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testarToModel(){
        var objFornecedor = fornecedorMock();
        var objTypeMap = typeMapMock();

        when(fornecedorUtil.buscarFornecedor(any())).thenReturn(objFornecedor);
        modelMapper.createTypeMap(LivroRequest.class, Livro.class);

        when(modelMapper.map(any(), any())).thenReturn(livro);
        
        var mock = livroAssembler.toModel(livroRequest);
      
        assertNotNull(mock);

    }


    @Test
    void testarToCollectionModel(){
    }

    private Fornecedor fornecedorMock(){
        Fornecedor fornecedor = new Fornecedor();
        return fornecedor;
    }

    private TypeMap<LivroRequest, Livro> typeMapMock(){
        TypeMap<LivroRequest, Livro> typeMap = modelMapper.getTypeMap(LivroRequest.class, 
       Livro.class);
            
       return typeMap;
    }
    
}
