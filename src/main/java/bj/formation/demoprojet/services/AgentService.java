package bj.formation.demoprojet.services;
import bj.formation.demoprojet.dtos.AgentRequestDto;
import bj.formation.demoprojet.dtos.EnfantRequestDto;
import bj.formation.demoprojet.dtos.EnfantResponseDto;
import bj.formation.demoprojet.entities.*;

import bj.formation.demoprojet.handlers.HttpResponseHandler;
import bj.formation.demoprojet.mappers.AgentMapper;
import bj.formation.demoprojet.mappers.EnfantMapper;
import bj.formation.demoprojet.mappers.GradeMapper;
import bj.formation.demoprojet.repositories.AgentRepository;
import bj.formation.demoprojet.repositories.GradeRepository;
import bj.formation.demoprojet.repositories.NiveauEnfantRepository;
import bj.formation.demoprojet.requests.AgentRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentService {
    private final AgentRepository agentRepository;
    private final AgentGradeService agentGradeService;
    private final GradeRepository gradeRepository;
    private final AgentMapper agentMapper;
    private final GradeMapper gradeMapper;
    private final EnfantMapper enfantMapper;
    private final NiveauEnfantRepository niveauEnfantRepository;
    private final EnfantService enfantService;


    @Transactional
    public ResponseEntity<?> save(AgentRequestDto request) {
        if (request.getMatricule() != null && !request.getMatricule().isEmpty()){
            Agent isOldAgent = agentRepository.findById(request.getMatricule())
                    .orElse(null);

            if (isOldAgent != null) {
                return HttpResponseHandler.generateResponse(
                        false,
                        "L'agent avec ce matricule existe déjà",
                        HttpStatus.MULTI_STATUS,null,
                        "/api/agents"
                );
            }
            Grade gradeExist = null;

            if(request.getGrade().code() != null && !request.getGrade().code().isEmpty()){
                gradeExist = gradeRepository.findById(request.getGrade().code())
                        .orElse(null);
                if (gradeExist == null) {
                    return HttpResponseHandler.generateResponse(
                            false,
                            "Le grade fourni est introuvable",
                            HttpStatus.NOT_FOUND,null,
                            "/api/agents"
                    );
                }

            }

            Agent agent = agentMapper.toAgent(request);
            agentRepository.saveAndFlush(agent);

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
                    enfantService.saveEnfant(enfantRequestDto);
                }
            }

            return HttpResponseHandler.generateResponse(
                    false,
                    "L'agent a été créé avec succès",
                    HttpStatus.CREATED,null,
                    "/api/agents"
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "Le matricule est obligatoire",
                    HttpStatus.MULTI_STATUS,null,
                    "/api/agents"
            );
        }
    }

    public  ResponseEntity<Object> paginated_data(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<Agent> agents = agentRepository.findAll(pageable);
        return HttpResponseHandler.generateResponse(
                true,
                "List des agents paginée",
                HttpStatus.OK,
                agents,
                "api/agents"
        );
    }

    public ResponseEntity<Object> getAll(){
        List<Agent> agents = agentRepository.findAll();
        return HttpResponseHandler.generateResponse(
                true,
                "List des agents",
                HttpStatus.OK,
                agents,
                "api/agents"
        );
    }


    public ResponseEntity<Object> updateAgent(String matricule, AgentRequestDto agentDetails){

        if (matricule != null && !matricule.isEmpty()) {
            Agent oldAgent = agentRepository.findById(agentDetails.getMatricule())
                    .orElse(null);

            if (oldAgent == null) {
                return HttpResponseHandler.generateResponse(
                        false,
                        "L'agent avec ce matricule n'existe déjà",
                        HttpStatus.MULTI_STATUS, null,
                        "/api/agents"
                );
            }

            Grade gradeExist = (agentDetails.getGrade() != null && agentDetails.getGrade().code() != null && !agentDetails.getGrade().code().isEmpty())
                    ? gradeRepository.findById(agentDetails.getGrade().code()).orElse(null)
                    : null;

            if (gradeExist == null) {
                return HttpResponseHandler.generateResponse(
                        false,
                        "Le grade fourni est introuvable",
                        HttpStatus.NOT_FOUND, null,
                        "/api/agents/" + matricule
                );
            }

            if (agentDetails.getNom() != null) oldAgent.setNom(agentDetails.getNom());
            if (agentDetails.getPrenom() != null) oldAgent.setPrenom(agentDetails.getPrenom());
            if (agentDetails.getDateNaissance() != null) oldAgent.setDateNaissance(agentDetails.getDateNaissance());
            if (agentDetails.getNbreEnfant() != null) oldAgent.setNbreEnfant(agentDetails.getNbreEnfant());


            // Enregistrer les nouveaux enfants s'il y en a
            if (agentDetails.getEnfants() != null && !agentDetails.getEnfants().isEmpty()) {
                for (EnfantRequestDto enfantRequestDto : agentDetails.getEnfants()) {
                    enfantService.saveEnfant(enfantRequestDto);
                }
            }

            agentRepository.saveAndFlush(oldAgent);

            // Si un nouveau grade est défini, on ajoute aux grades de l'agent
            if (gradeExist != null) {
                AgentGrade agentGrade = new AgentGrade();
                agentGrade.setAgent(oldAgent);
                agentGrade.setGrade(gradeExist);
                agentGrade.setDateDebut(LocalDate.now());
                agentGradeService.save(agentGrade);
            }

            return HttpResponseHandler.generateResponse(
                    true,
                    "L'agent a été mis à jour avec succès",
                    HttpStatus.OK, oldAgent,
                    "/api/agents/" + matricule
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "L matricule de l'agent est obligatoire",
                    HttpStatus.MULTI_STATUS, null,
                    "/api/agents/" + matricule
            );
        }
    }

    public ResponseEntity<?> findAgentByMatricule(String matricule){
        Agent agent = agentRepository.findById(matricule)
                .orElse(null);

        if(agent != null){
            return HttpResponseHandler.generateResponse(
                    true,
                    "L'agent a été récupéré",
                    HttpStatus.OK,agent,
                    "/api/agents/"+matricule
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "L'agent n'existe pas",
                    HttpStatus.NOT_FOUND,null,
                    "/api/agents/"+matricule
            );
        }
    }

    public ResponseEntity<Object> deleteAgent(String matricule){
        Agent agent = agentRepository.findById(matricule)
                .orElse(null);

        if(agent != null){
            agentRepository.delete(agent);
            return HttpResponseHandler.generateResponse(
                    true,
                    "L'agent supprimé avec succès",
                    HttpStatus.OK,null,
                    "/api/agents/"+matricule
            );
        }else {
            return HttpResponseHandler.generateResponse(
                    false,
                    "L'agent n'existe pas",
                    HttpStatus.NOT_FOUND,null,
                    "/api/agents/"+matricule
            );
        }
    }



}
