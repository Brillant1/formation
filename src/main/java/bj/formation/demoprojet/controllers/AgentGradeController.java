package bj.formation.demoprojet.controllers;

import bj.formation.demoprojet.entities.AgentGrade;
import bj.formation.demoprojet.services.AgentGradeService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/agent-grades")
public class AgentGradeController {
    private final AgentGradeService agentGradeService;

    public AgentGradeController(AgentGradeService agentGradeService) {
        this.agentGradeService = agentGradeService;
    }


    @PostMapping
    public ResponseEntity<AgentGrade> saveAgentGrade(@RequestBody AgentGrade agentGrade){
        AgentGrade agentGrade1 = agentGradeService.save(agentGrade);
        return ResponseEntity.ok(agentGrade1);
    }

    @GetMapping
    public ResponseEntity<List<AgentGrade>> getAllAgentGrade(){
        List<AgentGrade> agentGrades = agentGradeService.getAll();
        return ResponseEntity.ok(agentGrades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentGrade> getAgentGradeById(Long id){
        AgentGrade agentGrade = agentGradeService.getAgentGradeById(id);
        return ResponseEntity.ok(agentGrade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgentGrade> updateAgentGrade(@PathVariable Long id,
                                                       @RequestParam String code_grade,
                                                       @RequestParam String matricule_agent,
                                                       @RequestBody AgentGrade agentGrade){
        AgentGrade agentGrade1 = agentGradeService.updateAgentGrade(id,code_grade, matricule_agent, agentGrade);

        return ResponseEntity.ok(agentGrade1);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgentGrade(@PathVariable Long id){
        try{
            agentGradeService.deleteAgentGrade(id);
            return ResponseEntity.ok("Agent grade supprimé avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


}
