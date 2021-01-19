package com.projeto.order.domain;

import com.projeto.order.domain.enums.Sexo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PedidoTest {

    @Test
    public void deveCriarAPartirDoBuilder() {
        PessoaFisica pessoaFisica = PessoaFisica.Builder.create()
                .nome("Gustavo Alves")
                .nomeMae("Andreia Alves")
                .dataNascimento(LocalDate.of(2012, 1, 2))
                .sexo(Sexo.FEM)
                .telefone("21 1234-1234")
                .email("gustavo@teste.com")
                .cpf("445.412.420-52")
                .rg("46.986.898-3")
                .endereco("Rua Joaquim Nabuco")
                .build();

        Medico medico = mock(Medico.class);
        when(medico.getNome()).thenReturn("Medico Alberto");

        Pedido pedido = Pedido.Builder.create()
                .medico(medico)
                .pessoaFisica(pessoaFisica)
                .dataValidade(LocalDate.of(2022, 1, 2))
                .exames("Exame de sangue")
                .build();

        Assertions.assertEquals("Medico Alberto", pedido.getMedico().getNome());
        Assertions.assertEquals("Exame de sangue", pedido.getExames());
        Assertions.assertEquals(LocalDate.of(2022, 1, 2), pedido.getDataValidade());
        Assertions.assertEquals("Gustavo Alves", pedido.getPessoaFisica().getNome());
        Assertions.assertEquals("445.412.420-52", pedido.getPessoaFisica().getCpf());
    }

}