package bj.formation.demoprojet.repositories;

import bj.formation.demoprojet.entities.AgentElementPaie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentElementPaieRepository extends JpaRepository<AgentElementPaie,Long> {
}
