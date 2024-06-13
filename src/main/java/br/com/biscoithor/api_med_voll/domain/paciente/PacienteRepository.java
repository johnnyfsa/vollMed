package br.com.biscoithor.api_med_voll.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable paging);
}
