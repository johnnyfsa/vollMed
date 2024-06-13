package br.com.biscoithor.api_med_voll.domain.medico;

import br.com.biscoithor.api_med_voll.domain.endereco.Endereco;

public record RetornoAtualizacaoMedicoDTO(long id , String nome, String email, String crm, String telefone , Especialidade especialidade, Endereco endereco) {
    public RetornoAtualizacaoMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
