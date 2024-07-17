package br.com.biscoithor.api_med_voll.domain.consulta.validacoes.cacelamento;

import br.com.biscoithor.api_med_voll.domain.consulta.DadosCancelamentoConsulta;

public interface ValidaCancelamentoConsulta {
    public void validar(DadosCancelamentoConsulta dados);
}
