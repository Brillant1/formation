package bj.formation.demoprojet.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentRequestDto {
    String matricule;
    String nom;
    String prenom;
    LocalDate dateNaissance;
    Integer nbreEnfant;
    GradeDto grade;
    List<EnfantRequestDto> enfants = new ArrayList<>();
}
