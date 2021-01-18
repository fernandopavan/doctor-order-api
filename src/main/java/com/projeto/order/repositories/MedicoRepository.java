package com.projeto.order.repositories;

import com.projeto.order.domain.Medico;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicoRepository extends CrudRepository<Medico, UUID>, QuerydslPredicateExecutor<Medico> {

}