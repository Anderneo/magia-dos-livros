package com.eternos.magiadoslivros.FornecedorTeste;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.assembler.FornecedorAssembler;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.request.FornecedorRequest;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;

@SpringBootTest
public class FornecedorAssemblerTeste {

    @InjectMocks
    private FornecedorAssembler fornecedorAssembler;

    @Mock
    private FornecedorUtil fornecedorUtil;

    @Mock
    private FornecedorRequest fornecedorRequest;

    @Mock
    private Fornecedor fornecedor;

    @Mock
    private ModelMapper modelMapper;

    // @Test
    // void testarToModel(){
    //     var objFornecedor = fornecedorMock();
    //     TypeMap<Object, Object> objTypeMap = typeMapMock();
    //     var objFornecedorRequest = fornecedorRequestMock();


    //     when(fornecedorUtil.buscarFornecedor(any())).thenReturn(objFornecedor);

    //     when(modelMapper.getTypeMap(any(), any())).thenReturn(objTypeMap);
        
        

    //     // when(modelMapper.map(any(), any())).thenReturn(fornecedor);
        
    //     var mock = fornecedorAssembler.toModel(objFornecedorRequest);
      
    //     assertNotNull(mock);

        

    // }

    private TypeMap<FornecedorRequest, Fornecedor> typeMapMock(){
        TypeMap<FornecedorRequest, Fornecedor> typeMap = modelMapper.getTypeMap(FornecedorRequest.class, 
       Fornecedor.class);
            
       return typeMap;
    }


    @Test
    void testarToCollectionModel(){
    }

    private Fornecedor fornecedorMock(){
        Fornecedor fornecedor = new Fornecedor();
        return fornecedor;
    }



    private FornecedorRequest fornecedorRequestMock() {
        FornecedorRequest fornecedorRequest = new FornecedorRequest();
        fornecedorRequest.setEndereco("teste");
        fornecedorRequest.setEmail("teste");
        fornecedorRequest.setTelefone("19988776655");
        fornecedorRequest.setObservacao("fds");
        fornecedorRequest.setNomeFantasia("Teste");
        fornecedorRequest.setRazaoSocial("Teste");
        fornecedorRequest.setCnpj("60.575.973/0001-80");
        return fornecedorRequest;
    }
    
}
