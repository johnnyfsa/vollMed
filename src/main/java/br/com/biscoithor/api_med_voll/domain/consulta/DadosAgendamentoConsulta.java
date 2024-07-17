package br.com.biscoithor.api_med_voll.domain.consulta;

import br.com.biscoithor.api_med_voll.domain.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta
        (
                Long idMedico,
                @NotNull Long idPaciente,
                @NotNull @Future LocalDateTime data,
                Especialidade especialidade
                )
{

}
