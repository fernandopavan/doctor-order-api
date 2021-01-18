package com.projeto.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.order.domain.enums.Sexo;
import com.projeto.order.domain.pattern.AbstractEntity;
import com.projeto.order.domain.pattern.EntityBuilder;
import com.projeto.order.domain.validation.PessoaFisicaValid;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@PessoaFisicaValid
@Table(name = "PESSOAS_FISICAS")
public class PessoaFisica extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotEmpty(message = "Preenchimento do nome é obrigatório")
    @Length(min = 3, max = 120, message = "O nome deve ter entre {min} e {max} caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento do nome da mãe é obrigatório")
    @Length(min = 3, max = 120, message = "O nome da mãe deve ter entre {min} e {max} caracteres")
    private String nomeMae;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotNull(message = "Preenchimento do sexo é obrigatório")
    private Sexo sexo;

    @Length(min = 3, max = 20, message = "O telefone deve ter entre {min} e {max} caracteres")
    private String telefone;

    @Email(message = "E-mail inválido")
    @NotEmpty(message = "Preenchimento do e-mail é obrigatório")
    private String email;

    @CPF(message = "CPF inválido")
    @NotEmpty(message = "Preenchimento do CPF é obrigatório")
    private String cpf;

    @Length(min = 3, max = 20, message = "O RG deve ter entre {min} e {max} caracteres")
    private String rg;

    @NotEmpty(message = "Preenchimento do endereço é obrigatório")
    @Length(min = 3, max = 120, message = "O endereço deve ter entre {min} e {max} caracteres")
    private String endereco;

    @Override
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public static final class Builder extends EntityBuilder<PessoaFisica> {

        public Builder(PessoaFisica entity, State state) {
            super(entity, state);
        }

        public static Builder create() {
            return new Builder(new PessoaFisica(), State.NEW);
        }

        public static Builder from(PessoaFisica entity) {
            return new Builder(entity, State.BUILT);
        }

        public Builder nome(String nome) {
            entity.nome = nome;
            return this;
        }

        public Builder nomeMae(String nomeMae) {
            entity.nomeMae = nomeMae;
            return this;
        }

        public Builder dataNascimento(LocalDate dataNascimento) {
            entity.dataNascimento = dataNascimento;
            return this;
        }

        public Builder sexo(Sexo sexo) {
            entity.sexo = sexo;
            return this;
        }

        public Builder telefone(String telefone) {
            entity.telefone = telefone;
            return this;
        }

        public Builder email(String email) {
            entity.email = email;
            return this;
        }

        public Builder cpf(String cpf) {
            entity.cpf = cpf;
            return this;
        }

        public Builder rg(String rg) {
            entity.rg = rg;
            return this;
        }

        public Builder endereco(String endereco) {
            entity.endereco = endereco;
            return this;
        }

    }

}
