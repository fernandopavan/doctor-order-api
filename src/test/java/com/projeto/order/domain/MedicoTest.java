package com.projeto.order.domain;

import com.projeto.order.domain.enums.Perfil;
import com.projeto.order.domain.enums.TipoConselho;
import com.projeto.order.domain.enums.Uf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class MedicoTest {

    @Test
    void deveCriarAPartirDoBuilder() {
        Medico medico = Medico.Builder.create()
                .nome("Nome 1")
                .numeroConselho("1234")
                .tipoConselho(TipoConselho.CFM)
                .estadoConselho(Uf.ES)
                .email("teste@gmail.com")
                .senha("123")
                .perfis(Collections.singleton(Perfil.ADMIN))
                .build();

        Assertions.assertEquals("Nome 1", medico.getNome());
        Assertions.assertEquals("1234", medico.getNumeroConselho());
        Assertions.assertEquals(TipoConselho.CFM, medico.getTipoConselho());
        Assertions.assertEquals(Uf.ES, medico.getEstadoConselho());
        Assertions.assertEquals("teste@gmail.com", medico.getEmail());
        Assertions.assertEquals(Collections.singleton(Perfil.ADMIN), medico.getPerfis());
    }

}