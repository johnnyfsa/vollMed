package br.com.biscoithor.api_med_voll.controller;

import br.com.biscoithor.api_med_voll.paciente.DadosCadastroPaciente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroPaciente paciente)
    {
        System.out.println(paciente);
    }
}
