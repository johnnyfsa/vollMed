package br.com.biscoithor.api_med_voll.medico;

public record DadosListagemMedico
        (
         long id ,String nome, String email, String crm, Especialidade especialidade
        )
{
    public DadosListagemMedico(Medico m)
    {
        this(m.getId(), m.getNome(), m.getEmail(), m.getCrm(),m.getEspecialidade());
    }
}
