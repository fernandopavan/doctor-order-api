package com.projeto.order.services.utils;

import com.projeto.order.domain.Medico;
import com.projeto.order.repositories.MedicoRepository;
import com.projeto.order.services.MedicoService;
import com.projeto.order.services.exceptions.ObjectNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private final MedicoRepository repository;
    private final MedicoService service;
    private final BCryptPasswordEncoder pe;

    public AuthService(MedicoRepository repository, MedicoService service, BCryptPasswordEncoder pe) {
        this.repository = repository;
        this.service = service;
        this.pe = pe;
    }

    private Random rand = new Random();

    public void sendNewPassword(String email) {
        Medico medico = service.findByEmail(email);

        if (medico == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();

        medico = Medico.Builder.from(medico)
                .senha(pe.encode(newPass))
                .build();

        repository.save(medico);
        //emailService.sendNewPasswordEmail(pessoaFisica, newPass); envia para o e-mail
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if (opt == 0) { // gera um digito
            return (char) (rand.nextInt(10) + 48);
        } else if (opt == 1) { // gera letra maiuscula
            return (char) (rand.nextInt(26) + 65);
        } else { // gera letra minuscula
            return (char) (rand.nextInt(26) + 97);
        }
    }
}
