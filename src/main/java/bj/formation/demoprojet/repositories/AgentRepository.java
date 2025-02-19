package bj.formation.demoprojet.repositories;

import bj.formation.demoprojet.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgentRepository extends JpaRepository<Agent,String> {
}
