package com.projeto.order.domain;

import com.projeto.order.domain.enums.Sexo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class PessoaFisicaTest {

    @Test
    void deveCriarAPartirDoBuilder() {
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

        Assertions.assertEquals("Gustavo Alves", pessoaFisica.getNome());
        Assertions.assertEquals("Andreia Alves", pessoaFisica.getNomeMae());
        Assertions.assertEquals(LocalDate.of(2012, 1, 2), pessoaFisica.getDataNascimento());
        Assertions.assertEquals(Sexo.FEM, pessoaFisica.getSexo());
        Assertions.assertEquals("21 1234-1234", pessoaFisica.getTelefone());
        Assertions.assertEquals("gustavo@teste.com", pessoaFisica.getEmail());
        Assertions.assertEquals("445.412.420-52", pessoaFisica.getCpf());
        Assertions.assertEquals("46.986.898-3", pessoaFisica.getRg());
        Assertions.assertEquals("Rua Joaquim Nabuco", pessoaFisica.getEndereco());
    }

    @Test
    void deveAtualizarAPartirDoBuilder() {
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

        Assertions.assertEquals("Gustavo Alves", pessoaFisica.getNome());

        PessoaFisica atualizado = PessoaFisica.Builder.from(pessoaFisica)
                .nome("Nome atualizado")
                .build();

        Assertions.assertEquals("Nome atualizado", atualizado.getNome());
    }
}