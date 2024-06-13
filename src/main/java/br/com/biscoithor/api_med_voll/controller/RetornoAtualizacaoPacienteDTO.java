package br.com.biscoithor.api_med_voll.controller;

import br.com.biscoithor.api_med_voll.domain.endereco.Endereco;
import br.com.biscoithor.api_med_voll.domain.paciente.Paciente;

public record RetornoAtualizacaoPacienteDTO(long id, String nome, String email, String telefone, String cpf, Endereco endereco) {
    RetornoAtualizacaoPacienteDTO(Paciente paciente)
    {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
