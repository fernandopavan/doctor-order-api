package com.projeto.order.domain.validation;

import com.projeto.order.domain.Medico;
import com.projeto.order.domain.QMedico;
import com.projeto.order.repositories.MedicoRepository;
import com.projeto.order.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MedicoValidator implements ConstraintValidator<MedicoValid, Medico> {

    @Autowired
    private MedicoRepository repository;

    @Override
    public void initialize(MedicoValid ann) {
    }

    @Override
    public boolean isValid(Medico medico, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (repository == null) {
            return true;
        }

        UUID id = medico.getId();

        list.addAll(existsEmail(list, id, medico.getEmail()));

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

    private List<FieldMessage> existsEmail(List<FieldMessage> list, UUID id, String email) {
        Medico existsEmail = repository.findOne(QMedico.medico.email.eq(email)).orElse(null);

        if ((id == null && existsEmail != null) || (id != null && existsEmail != null && !id.equals(existsEmail.getId()))) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        return list;
    }
}

