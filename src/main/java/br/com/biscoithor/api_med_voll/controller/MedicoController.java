package br.com.biscoithor.api_med_voll.controller;

import br.com.biscoithor.api_med_voll.medico.DadosCadastroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void Cadastrar(@RequestBody DadosCadastroMedico json)
    {
        System.out.println(json);
    }
}
