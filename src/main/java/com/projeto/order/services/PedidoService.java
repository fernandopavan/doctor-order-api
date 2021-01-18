package com.projeto.order.services;

import com.projeto.order.domain.Pedido;
import com.projeto.order.repositories.PedidoRepository;
import com.projeto.order.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final PessoaFisicaService pessoaFisicaService;

    public PedidoService(PedidoRepository repository, PessoaFisicaService pessoaFisicaService) {
        this.repository = repository;
        this.pessoaFisicaService = pessoaFisicaService;
    }

    public Pedido find(UUID id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }


    public Pedido update(Pedido obj, UUID id) {
        Pedido entity = find(id);

        return repository.save(Pedido.Builder.from(entity)
                .pessoaFisica(pessoaFisicaService.find(obj.getPessoaFisica().getId()))
                .exames(obj.getExames())
                .dataValidade(obj.getDataValidade())
                .build());
    }

}
