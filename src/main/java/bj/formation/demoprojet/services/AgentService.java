package bj.formation.demoprojet.services;
import bj.formation.demoprojet.dtos.AgentRequestDto;
import bj.formation.demoprojet.dtos.EnfantRequestDto;
import bj.formation.demoprojet.entities.*;

import bj.formation.demoprojet.handlers.HttpResponseHandler;
import bj.formation.demoprojet.mappers.AgentMapper;
import bj.formation.demoprojet.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableAsync
public class AgentService {
    private final AgentRepository agentRepository;
    private final AgentGradeService agentGradeService;
    private final GradeRepository gradeRepository;
    private final AgentMapper agentMapper;
    private final EnfantService enfantService;
    private final ElementPaieRepository elementPaieRepository;
    private  final AgentElementPaieRepository agentElementPaieRepository;
    private final HttpServletRequest requestUrl;


    @Transactional
    public ResponseEntity<?> save(AgentRequestDto request) {

        Grade gradeExist = null;
        if(request.getGrade_code() != null && !request.getGrade_code().isEmpty()){
            gradeExist = gradeRepository.findById(request.getGrade_code())
                    .orElseThrow(()-> new EntityNotFoundException("Le grade avec le code "+request.getGrade_code()+" n'existe pas"));
        }

        Agent agent = agentMapper.toAgent(request);
        agent.setIndice(gradeExist.getIndice());
        Integer salaireBase = gradeExist.getIndice()*3097/12;
        agent.setSalaireBase(salaireBase);

        agentRepository.saveAndFlush(agent);

        // Reuper ou créer l'elementPaie dont le code est SB
       boolean elementPaieWithCodeSBExist = elementPaieRepository.existsById("SB");
        ElementPaie elementPaieWithCodeSB = new ElementPaie();
       if(!elementPaieWithCodeSBExist){

           elementPaieWithCodeSB.setLibelle("Salaire de base");
           elementPaieWithCodeSB.setCode("SB");
           elementPaieRepository.saveAndFlush(elementPaieWithCodeSB);
       }else {
           elementPaieWithCodeSB = elementPaieRepository.findById("SB")
                   .orElseThrow(() -> new EntityNotFoundException("L'élement paie avec le code SB n'existe pas"));
       }


        // Créer un agent element paie
        AgentElementPaie agentElementPaie = new AgentElementPaie();
        agentElementPaie.setMontant(salaireBase);
        agentElementPaie.setDateDebut(LocalDate.now());
        agentElementPaie.setAgent(agent);
        agentElementPaie.setElementPaie(elementPaieWithCodeSB);

        agentElementPaieRepository.save(agentElementPaie);

        // Créer un code grade si le grade de l'agent est défini
        AgentGrade agentGrade = new AgentGrade();
        agentGrade.setAgent(agent);
        agentGrade.setGrade(gradeExist);
        agentGrade.setDateDebut(LocalDate.now());
        agentGradeService.save(agentGrade);

        // Enregistrer les enfants s'il y en a
        if(request.getEnfants() != null && !request.getNbreEnfant().describeConstable().isEmpty()){
            for (int i = 0; i < request.getEnfants().size()  ; i++) {
                EnfantRequestDto enfantRequestDto = request.getEnfants().get(i);
                enfantRequestDto.setAgentMatricule(agent.getMatricule());
                enfantService.saveEnfant(enfantRequestDto);
            }
        }

        this.sendNotification(agent);
        return HttpResponseHandler.generateResponse(
                true,
                "L'agent a été créé avec succès",
                HttpStatus.CREATED,agent,
                null,
                requestUrl.getRequestURI()
        );
    }


    public ResponseEntity<Object> getAgentsWithLowestGradeIndex() {
        List<Agent>  agents =  agentRepository.findAgentsWithLowestGradeIndex();
        return HttpResponseHandler.generateResponse(
                true,
                "List des agents ayant le plus petit grade",
                HttpStatus.OK,
                agents, null,
                requestUrl.getRequestURI()
        );
    }

    public ResponseEntity<Object> findAgentsWithBigestGradeIndex() {
        List<Agent>  agents =  agentRepository.findAgentsWithLowestGradeIndex();
        return HttpResponseHandler.generateResponse(
                true,
                "List des agents ayant le plus grand grade",
                HttpStatus.OK,
                agents, null,
                requestUrl.getRequestURI()
        );
    }

    public  ResponseEntity<Object> paginated_data(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<Agent> agents = agentRepository.findAll(pageable);
        return HttpResponseHandler.generateResponse(
                true,
                "List des agents paginée",
                HttpStatus.OK,
                agents, null,
                requestUrl.getRequestURI()
        );
    }

    public ResponseEntity<Object> getAll(){
        List<Agent> agents = agentRepository.findAll();
        return HttpResponseHandler.generateResponse(
                true,
                "List des agents",
                HttpStatus.OK,
                agents, null,
                requestUrl.getRequestURI()
        );
    }

    @Transactional
    public ResponseEntity<Object> updateAgent(String matricule, AgentRequestDto agentDetails){

        if (matricule != null && !matricule.isEmpty()) {
            Agent oldAgent = agentRepository.findById(agentDetails.getMatricule())
                    .orElseThrow(()-> new EntityNotFoundException("L'agent avec le matricule "+agentDetails.getMatricule()+" n'existe pas"));

            if (agentDetails.getNom() != null) oldAgent.setNom(agentDetails.getNom());
            if (agentDetails.getPrenom() != null) oldAgent.setPrenom(agentDetails.getPrenom());
            if (agentDetails.getDateNaissance() != null) oldAgent.setDateNaissance(agentDetails.getDateNaissance());
            if (agentDetails.getNbreEnfant() != null) oldAgent.setNbreEnfant(agentDetails.getNbreEnfant());

            agentRepository.saveAndFlush(oldAgent);

            // Enregistrer les nouveaux enfants s'il y en a
            if (agentDetails.getEnfants() != null && !agentDetails.getEnfants().isEmpty()) {
                for (EnfantRequestDto enfantRequestDto : agentDetails.getEnfants()) {
                    enfantRequestDto.setAgentMatricule(oldAgent.getMatricule());
                    enfantService.saveEnfant(enfantRequestDto);
                }
            }

            // Si un nouveau grade est défini, on ajoute aux grades de l'agent
            if(agentDetails.getGrade_code() != null && !agentDetails.getGrade_code().isEmpty()){
                    Grade grade = gradeRepository.findById(agentDetails.getGrade_code())
                            .orElseThrow(() -> new EntityNotFoundException("Le grade avec le code "+agentDetails.getGrade_code()));
                    AgentGrade agentGrade = new AgentGrade();
                    agentGrade.setAgent(oldAgent);
                    agentGrade.setGrade(grade);
                    agentGrade.setDateDebut(LocalDate.now());
                    agentGradeService.save(agentGrade);
            }

            return HttpResponseHandler.generateResponse(
                    true,
                    "L'agent a été mis à jour avec succès",
                    HttpStatus.OK, oldAgent, null,
                    requestUrl.getRequestURI()
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "L matricule de l'agent est obligatoire",
                    HttpStatus.MULTI_STATUS, null, null,
                    requestUrl.getRequestURI()
            );
        }
    }

    public ResponseEntity<?> findAgentByMatricule(String matricule){
        Agent agent = agentRepository.findById(matricule)
                .orElseThrow(() -> new EntityNotFoundException("L'agent avec le matricule "+matricule+ " n'existe pas"));

            return HttpResponseHandler.generateResponse(
                    true,
                    "L'agent a été récupéré",
                    HttpStatus.OK,agent, null,
                    requestUrl.getRequestURI()
            );
    }

    public ResponseEntity<Object> deleteAgent(String matricule){
        Agent agent = agentRepository.findById(matricule)
                .orElseThrow(()-> new EntityNotFoundException("L'agent avec le matricule "+matricule+ " n'existe pas"));

        agentRepository.delete(agent);
        return HttpResponseHandler.generateResponse(
                true,
                "L'agent a été supprimé avec succès",
                HttpStatus.OK,null, null,
                requestUrl.getRequestURI()
        );
    }


    @Async
    public void sendNotification(Agent agent) {
        try {
            Thread.sleep(10000); // Pause de 10 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("L'agent "+agent.getNom()+ " " +agent.getPrenom()+" a été créé avec succès sous le matricule "+agent.getMatricule());
    }
}
