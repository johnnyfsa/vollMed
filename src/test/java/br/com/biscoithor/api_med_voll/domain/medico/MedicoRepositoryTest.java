package br.com.biscoithor.api_med_voll.domain.medico;

import br.com.biscoithor.api_med_voll.domain.consulta.Consulta;
import br.com.biscoithor.api_med_voll.domain.endereco.DadosEndereco;
import br.com.biscoithor.api_med_voll.domain.paciente.DadosCadastroPaciente;
import br.com.biscoithor.api_med_voll.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando o único médico cadastrado não está disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario1()
    {
        //given
        var dateTest = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)).toLocalDate().atTime(10,0);
        var medico = cadastrarMedico("medico1", "medico@voll.com.br","123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("paciente1", "paciente@voll.com.br", "44444444444");
        cadastrarConsulta(medico, paciente, dateTest);
        //when
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, dateTest);
        //assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando o médico cadastrado está disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario2()
    {
        //given
        var dateTest = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)).toLocalDate().atTime(10,0);
        var medico = cadastrarMedico("medico1", "medico@voll.com.br","123456", Especialidade.CARDIOLOGIA);
        //when | act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, dateTest);
        //assert | then
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data)
    {
        em.persist(new Consulta(null,medico,paciente,data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade)
    {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf)
    {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return  paciente;
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "7889564512",
                cpf,
                dadosEndereco()
        );
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade)
    {
        return new DadosCadastroMedico(nome, email, "999999999",crm, especialidade, dadosEndereco());
    }

    private DadosEndereco dadosEndereco()
    {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Salvador",
                "BA",
                "confidencial",
                "000"
        );
    }
}