package com.projeto.order.domain;

import com.projeto.order.domain.enums.Perfil;
import com.projeto.order.domain.enums.TipoConselho;
import com.projeto.order.domain.enums.Uf;
import com.projeto.order.domain.pattern.AbstractEntity;
import com.projeto.order.domain.pattern.EntityBuilder;
import com.projeto.order.domain.validation.MedicoValid;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@MedicoValid
@Table(name = "MEDICOS")
public class Medico extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotEmpty(message = "Preenchimento do nome obrigatório")
    @Length(min = 3, max = 120, message = "O nome deve ter entre {min} e {max} caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento do Nº do conselho obrigatório")
    @Length(min = 3, max = 50, message = "O Nº do conselho deve ter entre {min} e {max} caracteres")
    private String numeroConselho;

    @NotNull(message = "Preenchimento do estado do conselho obrigatório")
    private Uf estadoConselho;

    @NotNull(message = "Preenchimento do tipo do conselho obrigatório")
    private TipoConselho tipoConselho;

    @Email(message = "E-mail inválido")
    @NotEmpty(message = "Preenchimento do e-mail obrigatório")
    private String email;

    @NotEmpty(message = "Preenchimento da senha obrigatória")
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis")
    private Set<Integer> perfis = new HashSet<>();

    @Override
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroConselho() {
        return numeroConselho;
    }

    public Uf getEstadoConselho() {
        return estadoConselho;
    }

    public TipoConselho getTipoConselho() {
        return tipoConselho;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public static final class Builder extends EntityBuilder<Medico> {

        public Builder(Medico entity, EntityBuilder.State state) {
            super(entity, state);
        }

        public static Builder create() {
            return new Builder(new Medico(), State.NEW);
        }

        public static Builder from(Medico entity) {
            return new Builder(entity, State.BUILT);
        }

        public Builder nome(String nome) {
            entity.nome = nome;
            return this;
        }

        public Builder numeroConselho(String numeroConselho) {
            entity.numeroConselho = numeroConselho;
            return this;
        }

        public Builder estadoConselho(Uf estadoConselho) {
            entity.estadoConselho = estadoConselho;
            return this;
        }

        public Builder tipoConselho(TipoConselho tipoConselho) {
            entity.tipoConselho = tipoConselho;
            return this;
        }

        public Builder email(String email) {
            entity.email = email;
            return this;
        }

        public Builder senha(String senha) {
            BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
            entity.senha = entity.id == null ? pe.encode(senha) : senha;
            return this;
        }

        public Builder perfis(Set<Perfil> perfis) {
            List<Integer> perfisId = perfis.stream().map(Perfil::getId).collect(Collectors.toList());
            entity.perfis.addAll(perfisId);
            return this;
        }
    }

}
