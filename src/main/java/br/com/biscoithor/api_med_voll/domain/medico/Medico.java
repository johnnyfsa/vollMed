package br.com.biscoithor.api_med_voll.domain.medico;

import br.com.biscoithor.api_med_voll.domain.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Boolean ativo;
    private String nome;
    private String email;
    private  String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
         this.ativo=true;
         this.nome=dados.nome();
         this.email=dados.email();
         this.telefone= dados.telefone();
         this.crm=dados.crm();
         this.especialidade=dados.especialidade();
         this.endereco= new Endereco(dados.endereco());
    }

    public long getId() {
        return id;
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

    public String getCrm() {
        return crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoMedico dados) {
        if(dados.nome()!=null) {this.nome = dados.nome();}
        if (dados.telefone()!= null){this.telefone = dados.telefone();}
        if (dados.endereco()!=null){this.endereco.atualizarEndereco(dados.endereco());}
    }

    public void desativar() {
        this.setAtivo(false);
    }
}
