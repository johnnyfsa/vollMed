package br.com.biscoithor.api_med_voll.paciente;

import br.com.biscoithor.api_med_voll.endereco.DadosEndereco;
import br.com.biscoithor.api_med_voll.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(@NotNull Long id, String nome, String telefone, String email, DadosEndereco endereco) {
}
