package com.projeto.order.resources;

import com.projeto.order.domain.Medico;
import com.projeto.order.domain.QMedico;
import com.projeto.order.repositories.MedicoRepository;
import com.projeto.order.repositories.PessoaFisicaRepository;
import com.projeto.order.services.MedicoService;
import com.projeto.order.services.PessoaFisicaService;
import com.projeto.order.services.exceptions.DataIntegrityException;
import com.querydsl.core.BooleanBuilder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoResource {

    private final MedicoService service;
    private final MedicoRepository repository;

    public MedicoResource(MedicoService service, MedicoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @ApiOperation("Busca uma médico por Id")
    @GetMapping("/{id}")
    public ResponseEntity<Medico> find(@PathVariable UUID id) {
        Medico obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation("Busca uma médico por nome")
    @GetMapping("/nome")
    public ResponseEntity<Iterable<Medico>> findByName(@RequestParam(value = "nome") String nome) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (nome != null && !nome.isEmpty()) {
            booleanBuilder.and(QMedico.medico.nome.containsIgnoreCase(nome));
        }
        Iterable<Medico> pessoasFisicas = repository.findAll(booleanBuilder);
        return ResponseEntity.ok().body(pessoasFisicas);
    }

    @ApiOperation("Busca uma médico por e-mail")
    @GetMapping("/email")
    public ResponseEntity<Medico> findByEmail(@RequestParam(value = "email") String email) {
        Medico obj = service.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation("Insere uma médico")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Transactional
    @PostMapping
    public ResponseEntity<Medico> insert(@Valid @RequestBody Medico medico) {
        return ResponseEntity.ok().body(repository.save(medico));
    }

    @ApiOperation("Atualiza uma médico")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody Medico medico, @PathVariable UUID id) {
        return ResponseEntity.ok().body(service.update(medico, id));
    }

    @ApiOperation("Remove uma médico")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        try {
            if (!repository.exists(QMedico.medico.id.ne(id))) {
                throw new DataIntegrityException("Não é possivel excluir a primeira médico do sistema :( !");
            }
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há dados relacionados");
        }

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Retorna uma lista de pessoas paginada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de pessoas paginada"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public ResponseEntity<Page<Medico>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "nome", defaultValue = "") String nome) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (nome != null && !nome.isEmpty()) {
            booleanBuilder.and(QMedico.medico.nome.containsIgnoreCase(nome));
        }

        PageRequest pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.valueOf(direction), orderBy));

        Page<Medico> list = repository.findAll(booleanBuilder, pageable);

        return ResponseEntity.ok().body(list);
    }

}
