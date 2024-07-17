package br.com.biscoithor.api_med_voll.domain.consulta.validacoes.agendamento;

import br.com.biscoithor.api_med_voll.domain.consulta.DadosAgendamentoConsulta;

public interface ValidaAgendamentoConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
