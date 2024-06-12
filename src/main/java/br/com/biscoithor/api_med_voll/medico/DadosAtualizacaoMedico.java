package br.com.biscoithor.api_med_voll.medico;

import br.com.biscoithor.api_med_voll.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
