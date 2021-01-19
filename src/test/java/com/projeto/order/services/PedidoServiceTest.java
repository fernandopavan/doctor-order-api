package com.projeto.order.services;

import com.projeto.order.domain.Medico;
import com.projeto.order.domain.Pedido;
import com.projeto.order.domain.PessoaFisica;
import com.projeto.order.domain.enums.Sexo;
import com.projeto.order.repositories.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PedidoServiceTest {

    @Autowired
    private PedidoService service;

    @MockBean
    private PedidoRepository repository;

    @MockBean
    private PessoaFisicaService pessoaFisicaService;

    @Test
    void deveInserirPedidoComProdutosEServicos() {
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

        Pedido pedido = createPedido(pessoaFisica, medico);

        when(repository.save(any(Pedido.class))).thenReturn(pedido);
        when(pessoaFisicaService.find(any())).thenReturn(pessoaFisica);

        Pedido save = repository.save(pedido);

        Assertions.assertEquals("Exame de sangue", save.getExames());
        Assertions.assertEquals(LocalDate.of(2022, 1, 2), save.getDataValidade());
        Assertions.assertEquals("Gustavo Alves", save.getPessoaFisica().getNome());
        Assertions.assertEquals("445.412.420-52", save.getPessoaFisica().getCpf());
        Assertions.assertEquals("Medico Alberto", save.getMedico().getNome());
    }

    @Test
    void deveAtualizarPedidoComProdutosEServicos() {
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

        Pedido pedidoUpdated = createPedido(pessoaFisica, medico);

        when(repository.findById(any())).thenReturn(java.util.Optional.ofNullable(createPedido(pessoaFisica, medico)));
        when(pessoaFisicaService.find(any())).thenReturn(pessoaFisica);
        when(repository.save(any(Pedido.class))).thenReturn(pedidoUpdated);

        Pedido save = service.update(pedidoUpdated, UUID.randomUUID());

        Assertions.assertEquals("Exame de sangue", save.getExames());
        Assertions.assertEquals(LocalDate.of(2022, 1, 2), save.getDataValidade());
        Assertions.assertEquals("Gustavo Alves", save.getPessoaFisica().getNome());
        Assertions.assertEquals("445.412.420-52", save.getPessoaFisica().getCpf());
        Assertions.assertEquals("Medico Alberto", save.getMedico().getNome());
    }

    private Pedido createPedido(PessoaFisica pessoa, Medico medico) {
        return Pedido.Builder.create()
                .medico(medico)
                .pessoaFisica(pessoa)
                .dataValidade(LocalDate.of(2022, 1, 2))
                .exames("Exame de sangue")
                .build();
    }

}