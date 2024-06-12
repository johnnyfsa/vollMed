package br.com.biscoithor.api_med_voll.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico,Long>
{
    Page<Medico> findAllByAtivoTrue(Pageable paging);
}
