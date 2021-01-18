package com.projeto.order.services.utils;

import com.projeto.order.domain.Medico;
import com.projeto.order.domain.QMedico;
import com.projeto.order.repositories.MedicoRepository;
import com.projeto.order.security.UserSS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MedicoRepository repository;

    public UserDetailsServiceImpl(MedicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Medico> medico = repository.findOne(QMedico.medico.email.eq(email));

        if (!medico.isPresent()) {
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(medico.get().getId(), medico.get().getEmail(), medico.get().getSenha(), medico.get().getPerfis());
    }
}
