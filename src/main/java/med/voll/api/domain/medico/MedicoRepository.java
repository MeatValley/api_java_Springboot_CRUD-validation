package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//pra criar bd
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao); //qual identidade e qual o atributo da PK
}