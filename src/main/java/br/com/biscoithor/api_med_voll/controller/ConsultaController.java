package br.com.biscoithor.api_med_voll.controller;


import br.com.biscoithor.api_med_voll.domain.consulta.AgendaDeConsultas;
import br.com.biscoithor.api_med_voll.domain.consulta.DadosAgendamentoConsulta;
import br.com.biscoithor.api_med_voll.domain.consulta.DadosCancelamentoConsulta;
import br.com.biscoithor.api_med_voll.domain.consulta.DadosDetalhamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados)
    {
         var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cancelar")
    @Transactional
    public ResponseEntity cancelar(@RequestBody DadosCancelamentoConsulta dados)
    {
        var dto = agenda.cancelar(dados);
        return ResponseEntity.ok(dto);
    }



}
