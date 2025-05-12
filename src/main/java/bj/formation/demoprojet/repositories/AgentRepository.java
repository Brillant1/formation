package bj.formation.demoprojet.repositories;

import bj.formation.demoprojet.entities.Agent;
import jakarta.validation.constraints.Min;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AgentRepository extends JpaRepository<Agent,String> {
    //SELECT a from Agent a WHERE Min

    @Query("SELECT a FROM Agent a WHERE a.indice = (SELECT MIN(g.indice) FROM Grade g)")
    List<Agent> findAgentsWithLowestGradeIndex();

    @Query("SELECT a FROM Agent a WHERE a.indice = (SELECT MAX(g.indice) FROM Grade g)")
    List<Agent> findAgentsWithBigestGradeIndex();

    
}
