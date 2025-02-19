package bj.formation.demoprojet.repositories;

import bj.formation.demoprojet.entities.AgentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgentGradeRepository extends JpaRepository<AgentGrade,Long> {
}
