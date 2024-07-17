package br.com.biscoithor.api_med_voll.domain.consulta.validacoes.cacelamento;

import br.com.biscoithor.api_med_voll.domain.ValidacaoException;
import br.com.biscoithor.api_med_voll.domain.consulta.ConsultaRepository;
import br.com.biscoithor.api_med_voll.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidaAntecedenciaCancelamento implements ValidaCancelamentoConsulta {
    @Autowired
    private ConsultaRepository repository;
    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if(diferencaEmHoras < 24)
        {
            throw new ValidacaoException("Consulta so pode ser cancelada com 24 horas de antecedencia");
        }
    }
}
