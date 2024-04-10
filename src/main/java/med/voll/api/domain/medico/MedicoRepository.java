package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

//pra criar bd
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao); //qual identidade e qual o atributo da PK


    @Query(value = """
            select * from medico m
            where 
            m.ativo = true
            and
            m.especialidade_id = :#{#especialidade.id}
            and 
            m.id not in (
                select c.medico_id from consultas c
                where
                c.data = :data
            )
            order by random()
            limit 1 
            """, nativeQuery = true)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

}