package com.projeto.order.services;

import com.projeto.order.domain.Medico;
import com.projeto.order.domain.Pedido;
import com.projeto.order.domain.QMedico;
import com.projeto.order.domain.enums.Perfil;
import com.projeto.order.repositories.MedicoRepository;
import com.projeto.order.repositories.PedidoRepository;
import com.projeto.order.security.UserSS;
import com.projeto.order.services.exceptions.AuthorizationException;
import com.projeto.order.services.exceptions.ObjectNotFoundException;
import com.projeto.order.services.utils.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final MedicoRepository medicoRepository;

    public PedidoService(PedidoRepository repository, MedicoRepository medicoRepository) {
        this.repository = repository;
        this.medicoRepository = medicoRepository;
    }

    public Pedido find(UUID id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido save(Pedido obj) {
        return repository.save(Pedido.Builder.from(obj)
                .medico(getMedicoLogado())
                .pessoaFisica(obj.getPessoaFisica())
                .exames(obj.getExames())
                .dataValidade(obj.getDataValidade())
                .build());
    }

    public Pedido update(Pedido obj, UUID id) {
        Pedido entity = find(id);

        return repository.save(Pedido.Builder.from(entity)
                .pessoaFisica(obj.getPessoaFisica())
                .exames(obj.getExames())
                .dataValidade(obj.getDataValidade())
                .build());
    }

    private Medico getMedicoLogado() {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN)) {
            throw new AuthorizationException("Acesso negado");
        }

        return medicoRepository.findOne(QMedico.medico.email.eq(user.getUsername()))
                .orElseThrow(() -> new ObjectNotFoundException("Médico não encontrado! Id: " + user.getId() + ", Tipo: " + Medico.class.getName()));
    }

}
