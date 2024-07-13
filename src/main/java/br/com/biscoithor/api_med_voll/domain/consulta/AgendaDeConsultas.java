package br.com.biscoithor.api_med_voll.domain.consulta;

import br.com.biscoithor.api_med_voll.domain.ValidacaoException;
import br.com.biscoithor.api_med_voll.domain.medico.Medico;
import br.com.biscoithor.api_med_voll.domain.medico.MedicoRepository;
import br.com.biscoithor.api_med_voll.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados)
    {
        if(!pacienteRepository.existsById(dados.idPaciente()))
        {
            throw new ValidacaoException("Id do paciente informado nao existente");
        }

        if(dados.idMedico()!=null && !medicoRepository.existsById(dados.idMedico()))
        {
            throw new ValidacaoException("Id do medico informado nao existente");
        }

        var medico = atribuirMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null,medico,paciente,dados.data());
        consultaRepository.save(consulta);
    }

    private Medico atribuirMedico(DadosAgendamentoConsulta dados)
    {
        if(dados.idMedico()!=null)
        {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        return null;
    }
}
