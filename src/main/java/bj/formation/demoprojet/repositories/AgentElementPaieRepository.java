package bj.formation.demoprojet.repositories;

import bj.formation.demoprojet.entities.AgentElementPaie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentElementPaieRepository extends JpaRepository<AgentElementPaie,Long> {

    Optional<AgentElementPaie> findByAgent_MatriculeAndElementPaie_Code(String agentMatricule, String elementPaieCode);
}
