package med.voll.api.domain.consultas;

import java.time.LocalDateTime;

public record DadsDetalhamentoConsultas(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
}
