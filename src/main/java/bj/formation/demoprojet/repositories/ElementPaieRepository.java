package bj.formation.demoprojet.repositories;

import bj.formation.demoprojet.entities.ElementPaie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ElementPaieRepository extends JpaRepository<ElementPaie,String> {
}
