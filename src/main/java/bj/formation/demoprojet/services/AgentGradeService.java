package bj.formation.demoprojet.services;

import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.AgentGrade;
import bj.formation.demoprojet.entities.Grade;
import bj.formation.demoprojet.repositories.AgentGradeRepository;
import bj.formation.demoprojet.repositories.AgentRepository;
import bj.formation.demoprojet.repositories.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AgentGradeService {
    private final AgentGradeRepository agentGradeRepository;
    private final GradeRepository gradeRepository;
    private final AgentRepository agentRepository;

    public AgentGradeService(AgentGradeRepository agentGradeRepository, GradeRepository gradeRepository, AgentRepository agentRepository) {
        this.agentGradeRepository = agentGradeRepository;
        this.gradeRepository = gradeRepository;
        this.agentRepository = agentRepository;
    }

    public AgentGrade save(AgentGrade agentGrade){
        return  agentGradeRepository.save(agentGrade);
    }

    public List<AgentGrade> getAll(){
        return agentGradeRepository.findAll();
    }

    public AgentGrade getAgentGradeById(Long id){
        AgentGrade agentGrade = agentGradeRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Agent grade introuvable"));
        return agentGrade;
    }

    public AgentGrade updateAgentGrade(Long id, String code_grade, String matricule_agent, AgentGrade agentGradeDetails){
        AgentGrade agentGrade = agentGradeRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Agent grade introuvable"));

        Agent agent = agentRepository.findById(matricule_agent)
                .orElseThrow( ()-> new RuntimeException("Agent introuvable avec le matricule "+ matricule_agent));

        Grade grade = gradeRepository.findById(code_grade)
                .orElseThrow( ()-> new RuntimeException("Grade introuvable avec le code "+ code_grade));


        agentGrade.setDateDebut(agentGradeDetails.getDateDebut());
        agentGrade.setDateFin(agentGradeDetails.getDateFin());
        agentGrade.setAgent(agent);
        agentGrade.setGrade(grade);

        return agentGradeRepository.save(agentGrade);
    }

    public void deleteAgentGrade(Long id){
        AgentGrade agentGrade = agentGradeRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Agent grade introuvable"));
        agentGradeRepository.delete(agentGrade);
    }
}
