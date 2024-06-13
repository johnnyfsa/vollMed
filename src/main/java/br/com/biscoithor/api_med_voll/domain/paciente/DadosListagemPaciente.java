package br.com.biscoithor.api_med_voll.domain.paciente;

public record DadosListagemPaciente (String nome, String cpf, String telefone, String email){
    public DadosListagemPaciente(Paciente paciente)
    {
        this(paciente.getNome(), paciente.getCpf(), paciente.getTelefone(), paciente.getEmail());
    }
}
