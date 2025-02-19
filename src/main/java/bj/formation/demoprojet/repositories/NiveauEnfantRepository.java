package bj.formation.demoprojet.repositories;

import bj.formation.demoprojet.entities.NiveauEnfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauEnfantRepository extends JpaRepository<NiveauEnfant,String> {
}
