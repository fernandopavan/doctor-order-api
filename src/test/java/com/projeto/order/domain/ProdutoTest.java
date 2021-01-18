//package com.projeto.order.domain;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//
//@SpringBootTest
//class ProdutoTest {
//
//    @Test
//    public void deveCriarAPartirDoBuilder() {
//        PessoaFisica pessoaFisica = PessoaFisica.Builder.create()
//                .descricao("Produto 1")
//                .inativo(Boolean.TRUE)
//                .servico(Boolean.FALSE)
//                .preco(BigDecimal.TEN)
//                .build();
//
//        Assertions.assertEquals("Produto 1", pessoaFisica.getDescricao());
//        Assertions.assertTrue(pessoaFisica.getInativo());
//        Assertions.assertFalse(pessoaFisica.getServico());
//        Assertions.assertEquals(BigDecimal.TEN, pessoaFisica.getPreco());
//    }
//
//    @Test
//    public void deveAtualizarAPartirDoBuilder() {
//        PessoaFisica pessoaFisica = PessoaFisica.Builder.create()
//                .descricao("Produto 1")
//                .inativo(Boolean.TRUE)
//                .servico(Boolean.FALSE)
//                .preco(BigDecimal.TEN)
//                .build();
//
//        Assertions.assertEquals("Produto 1", pessoaFisica.getDescricao());
//        Assertions.assertTrue(pessoaFisica.getInativo());
//        Assertions.assertFalse(pessoaFisica.getServico());
//        Assertions.assertEquals(BigDecimal.TEN, pessoaFisica.getPreco());
//
//        PessoaFisica atualizado = PessoaFisica.Builder.from(pessoaFisica)
//                .descricao("Produto Atualizado")
//                .build();
//
//        Assertions.assertEquals("Produto Atualizado", atualizado.getDescricao());
//    }
//}