package br.com.biscoithor.api_med_voll.domain.paciente;

import br.com.biscoithor.api_med_voll.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(@NotNull Long id, String nome, String telefone, String email, DadosEndereco endereco) {
}
