package br.com.biscoithor.api_med_voll.domain.consulta;

import br.com.biscoithor.api_med_voll.domain.ValidacaoException;
import br.com.biscoithor.api_med_voll.domain.consulta.validacoes.agendamento.ValidaAgendamentoConsulta;
import br.com.biscoithor.api_med_voll.domain.consulta.validacoes.cacelamento.ValidaCancelamentoConsulta;
import br.com.biscoithor.api_med_voll.domain.medico.Medico;
import br.com.biscoithor.api_med_voll.domain.medico.MedicoRepository;
import br.com.biscoithor.api_med_voll.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidaAgendamentoConsulta> validadores;
    @Autowired
    private List<ValidaCancelamentoConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados)
    {
        if(!pacienteRepository.existsById(dados.idPaciente()))
        {
            throw new ValidacaoException("Id do paciente informado nao existente");
        }

        if(dados.idMedico()!=null && !medicoRepository.existsById(dados.idMedico()))
        {
            throw new ValidacaoException("Id do medico informado nao existente");
        }

        //validações
        validadores.forEach(v -> v.validar(dados));


        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = atribuirMedico(dados);
        if(medico==null)
        {
            throw new ValidacaoException("Não há medicos disponiveis nessa data");
        }
        var consulta = new Consulta(null,medico,paciente,dados.data());
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }


    public DadosRetornoCancelamentoConsulta cancelar(DadosCancelamentoConsulta dados)
    {
        if(!consultaRepository.existsById(dados.idConsulta()))
        {
            throw new ValidacaoException("Id da Consulta Informada Não Existe");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());

        var dto = new DadosRetornoCancelamentoConsulta(consulta);
        return dto;
    }

    private Medico atribuirMedico(DadosAgendamentoConsulta dados)
    {
        if(dados.idMedico()!=null)
        {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade()==null)
        {
            throw new ValidacaoException("Especialidade é obrigatoria quando o medico não for definido");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());
    }
}
