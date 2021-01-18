package com.projeto.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.order.domain.pattern.AbstractEntity;
import com.projeto.order.domain.pattern.EntityBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "PEDIDOS")
public class Pedido extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotNull(message = "Preenchimento da pessoa é obrigatório")
    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    private PessoaFisica pessoaFisica;

    @NotEmpty(message = "Preenchimento de exames é obrigatório")
    @Length(min = 3, max = 300, message = "Exames deve ter entre {min} e {max} caracteres")
    private String exames;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataValidade;

    @Override
    public UUID getId() {
        return id;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public String getExames() {
        return exames;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public static final class Builder extends EntityBuilder<Pedido> {

        public Builder(Pedido entity, EntityBuilder.State state) {
            super(entity, state);
        }

        public static Builder create() {
            return new Builder(new Pedido(), State.NEW);
        }

        public static Builder from(Pedido entity) {
            return new Builder(entity, State.BUILT);
        }

        public Builder pessoaFisica(PessoaFisica pessoaFisica) {
            entity.pessoaFisica = pessoaFisica;
            return this;
        }

        public Builder exames(String exames) {
            entity.exames = exames;
            return this;
        }

        public Builder dataValidade(LocalDate dataValidade) {
            entity.dataValidade = dataValidade;
            return this;
        }

    }

}
