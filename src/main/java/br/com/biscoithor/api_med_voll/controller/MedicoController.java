package br.com.biscoithor.api_med_voll.controller;

import br.com.biscoithor.api_med_voll.domain.medico.*;
import br.com.biscoithor.api_med_voll.domain.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder)
    {
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new RetornoAtualizacaoMedicoDTO(medico));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listarMedicos(Pageable paging)
    {
        var page = repository.findAllByAtivoTrue(paging).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados)
    {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new RetornoAtualizacaoMedicoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id)
    {
        var medico = repository.getReferenceById(id);
        medico.desativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity consultar(@PathVariable Long id)
    {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new RetornoAtualizacaoMedicoDTO(medico));
    }
}
