package bj.formation.demoprojet.requests;

import bj.formation.demoprojet.dtos.GradeDto;
import bj.formation.demoprojet.entities.Enfant;
import bj.formation.demoprojet.entities.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentRequest {
    private String matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Integer salaireBase;
    private Integer nbreEnfant;
    private GradeDto grade;
    private List<Enfant> enfants = new ArrayList<>();
}
