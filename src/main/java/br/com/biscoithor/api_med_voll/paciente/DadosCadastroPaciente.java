package br.com.biscoithor.api_med_voll.paciente;

import br.com.biscoithor.api_med_voll.endereco.DadosEndereco;

public record DadosCadastroPaciente(String nome,
                                    String email,
                                    String telefone,
                                    String cpf,
                                    DadosEndereco endereco) {
}
