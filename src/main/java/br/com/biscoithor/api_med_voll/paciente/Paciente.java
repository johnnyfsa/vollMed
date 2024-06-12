package br.com.biscoithor.api_med_voll.paciente;

import br.com.biscoithor.api_med_voll.endereco.DadosEndereco;
import br.com.biscoithor.api_med_voll.endereco.Endereco;
import br.com.biscoithor.api_med_voll.medico.DadosAtualizacaoMedico;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Boolean ativo;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados)
    {
        this.ativo = true;
        this.nome=dados.nome();
        this.email=dados.email();
        this.telefone=dados.telefone();
        this.cpf=dados.cpf().replaceAll("\\.|-","");
        this.endereco= new Endereco(dados.endereco());
    }

    public long getId() {
        return id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoPaciente dados)
    {
        if(dados.nome()!=null) {this.nome = dados.nome();}
        if (dados.telefone()!= null){this.telefone = dados.telefone();}
        if(dados.email()!=null){this.email= dados.email();}
        if (dados.endereco()!=null){this.endereco.atualizarEndereco(dados.endereco());}
    }

    public void desativar() {
        this.setAtivo(false);
    }
}
