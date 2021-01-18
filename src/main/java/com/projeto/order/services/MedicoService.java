package com.projeto.order.services;

import com.projeto.order.domain.Medico;
import com.projeto.order.domain.QMedico;
import com.projeto.order.domain.enums.Perfil;
import com.projeto.order.repositories.MedicoRepository;
import com.projeto.order.security.UserSS;
import com.projeto.order.services.exceptions.AuthorizationException;
import com.projeto.order.services.exceptions.ObjectNotFoundException;
import com.projeto.order.services.utils.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public Medico find(UUID id) {
        Optional<Medico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Medico.class.getName()));
    }

    public Medico findByEmail(String email) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Medico> obj = repository.findOne(QMedico.medico.email.eq(email));

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Medico.class.getName()));
    }

    public Medico update(Medico medico, UUID id) {
        Medico entity = find(id);

        Medico build = Medico.Builder.from(entity)
                .nome(medico.getNome())
                .numeroConselho(medico.getNumeroConselho())
                .estadoConselho(medico.getEstadoConselho())
                .tipoConselho(medico.getTipoConselho())
                .email(medico.getEmail())
                .senha(medico.getSenha())
                .perfis(medico.getPerfis())
                .build();

        return repository.save(build);
    }
}
