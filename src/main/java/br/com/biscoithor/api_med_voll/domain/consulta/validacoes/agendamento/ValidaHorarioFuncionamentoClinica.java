package br.com.biscoithor.api_med_voll.domain.consulta.validacoes.agendamento;

import br.com.biscoithor.api_med_voll.domain.ValidacaoException;
import br.com.biscoithor.api_med_voll.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidaHorarioFuncionamentoClinica implements ValidaAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados)
    {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var tooEarly = dataConsulta.getHour()<7;
        var tooLate = dataConsulta.getHour()>18;
        if(domingo||tooLate||tooEarly)
        {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clinica");
        }
    }
}
