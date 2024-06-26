package med.voll.api.domain.consultas;


import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.validacoes.ValidadorAgendamentoDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository; //achar por id

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;
    //cria uma lista pros validadores

    public void agendar(DadosAgendamentoConsultas dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("ID de paciente nao existe");
        }
        if(dados.idMedico()!= null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("ID de medico nao existe");
        }//medico opcional

        validadores.forEach(v->v.validar(dados));
        //nao precisa mexer nos validadores
        //se aumentar ou deletar, nao precisa fazer nada aqui
        //S - cada validador faz uma coisa (Solid)
        //O - Open to change, mas n precisa mudar
        //D - Depende da abstracao da validacao
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();//find devolve optional, usa o get
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        //preciso resgatar os med e paciente por id
        //@autorwired cm repository pra buscar



        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsultas dados) {
        if(dados.idMedico()!=null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade nao foi escolhida, nem o medico!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());


    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
