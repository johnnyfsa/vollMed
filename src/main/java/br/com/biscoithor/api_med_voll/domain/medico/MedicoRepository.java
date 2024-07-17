package br.com.biscoithor.api_med_voll.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico,Long>
{
    Page<Medico> findAllByAtivoTrue(Pageable paging);

    @Query("""
            SELECT m from Medico m
            WHERE
            m.ativo = true
            AND
            m.especialidade = :especialidade
            AND m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
            and
                c.cancelada = false
            )
            ORDER BY rand()
            LIMIT 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
