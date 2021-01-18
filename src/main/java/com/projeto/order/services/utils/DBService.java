package com.projeto.order.services.utils;

import com.projeto.order.domain.Medico;
import com.projeto.order.domain.Pedido;
import com.projeto.order.domain.PessoaFisica;
import com.projeto.order.domain.enums.Perfil;
import com.projeto.order.domain.enums.Sexo;
import com.projeto.order.domain.enums.TipoConselho;
import com.projeto.order.domain.enums.Uf;
import com.projeto.order.repositories.MedicoRepository;
import com.projeto.order.repositories.PedidoRepository;
import com.projeto.order.repositories.PessoaFisicaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

@Service
public class DBService {

    private final MedicoRepository repository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PedidoRepository pedidoRepository;

    public DBService(MedicoRepository repository,
                     PessoaFisicaRepository pessoaFisicaRepository,
                     PedidoRepository pedidoRepository) {
        this.repository = repository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public void instantiateTestDatabase() {
        Medico medico = Medico.Builder.create()
                .nome("Maria de Aparecida")
                .numeroConselho("12345")
                .tipoConselho(TipoConselho.CFM)
                .estadoConselho(Uf.SC)
                .email("maria@gmail.com")
                .senha("123")
                .perfis(Collections.singleton(Perfil.ADMIN))
                .build();

        repository.save(medico);

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

        PessoaFisica save = pessoaFisicaRepository.save(pessoaFisica);

        Pedido pedido = Pedido.Builder.create()
                .pessoaFisica(save)
                .dataValidade(LocalDate.of(2020, 4,12))
                .exames("Exame de sangue completo")
                .build();

        pedidoRepository.save(pedido);
    }
}
