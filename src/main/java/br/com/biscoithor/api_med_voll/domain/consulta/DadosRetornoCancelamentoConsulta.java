package br.com.biscoithor.api_med_voll.domain.consulta;

import java.time.LocalDateTime;

public record DadosRetornoCancelamentoConsulta(Long idConsulta, boolean cancelado, LocalDateTime data) {
    public DadosRetornoCancelamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.isCancelada(), consulta.getData());
    }
}
