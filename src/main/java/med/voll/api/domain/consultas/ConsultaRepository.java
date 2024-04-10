package med.voll.api.domain.consultas;

import org.springframework.data.jpa.repository.JpaRepository;

//pra criar bd
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}