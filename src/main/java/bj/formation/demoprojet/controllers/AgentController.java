package bj.formation.demoprojet.controllers;



import bj.formation.demoprojet.dtos.AgentEnfantDto;
import bj.formation.demoprojet.dtos.AgentRequestDto;
import bj.formation.demoprojet.dtos.AgentResponseDto;
import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.requests.AgentRequest;
import bj.formation.demoprojet.services.AgentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/agents")
@RequiredArgsConstructor
public class AgentController {
    private final AgentService agentService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AgentRequestDto request){
        return agentService.save(request);
    }


    @GetMapping
    public ResponseEntity<Object> getAllAgents() {
        return agentService.getAll();
    }

    @GetMapping("paginated")
    public ResponseEntity<Object> paginate_data(@RequestParam int page, @RequestParam int size){
        return agentService.paginated_data(page, size);
    }

    @GetMapping("agentWithLowerGradeCode")
    public  ResponseEntity<Object> agentWithLowerGradeCode(){
        return agentService.getAgentsWithLowestGradeIndex();
    }

    @GetMapping("agentWithBigerGradeCode")
    public  ResponseEntity<Object> agentWithBigGradeCode(){
        return agentService.getAgentsWithLowestGradeIndex();
    }

    @GetMapping("/{matricule}")
    public ResponseEntity<?> getAgentByMatricule(@PathVariable String matricule){
        return  agentService.findAgentByMatricule(matricule);
    }

    @PutMapping("/{matricule}")
    public ResponseEntity<Object> updateAgent(@PathVariable String matricule, @RequestBody AgentRequestDto agentDetails) {
        return agentService.updateAgent(matricule, agentDetails);
    }

    @DeleteMapping("/{matricule}")
    public ResponseEntity<Object> deleteAgent(@PathVariable String matricule){
        return agentService.deleteAgent(matricule);
    }


}
