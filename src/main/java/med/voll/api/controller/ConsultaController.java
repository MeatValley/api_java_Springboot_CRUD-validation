package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultas.AgendaDeConsultas;
import med.voll.api.domain.consultas.DadosAgendamentoConsultas;
import med.voll.api.domain.consultas.DadosCancelamentoConsulta;
import med.voll.api.domain.consultas.DadsDetalhamentoConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    //nao deveria ter regras de negocio
    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultas dados){
        System.out.println(dados);
        agendaDeConsultas.agendar(dados);//nao sabe as valida
        return ResponseEntity.ok(new DadsDetalhamentoConsultas(null,null,null,null));
    }

//    @DeleteMapping
//    @Transactional
//    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
//        agendaDeConsultas.cancelar(dados);
//        return ResponseEntity.noContent().build();
//    }


}
