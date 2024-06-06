package br.com.biscoithor.api_med_voll.medico;

import br.com.biscoithor.api_med_voll.endereco.DadosEndereco;

public record DadosCadastroMedico (String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco){
}
