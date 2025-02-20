package bj.formation.demoprojet.dtos;

import bj.formation.demoprojet.entities.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentResponseDto {
    String matricule;
    String nom;
    String prenom;
    LocalDate dateNaissance;
    Integer nbreEnfant;
    Grade grade;
    List<EnfantResponseDto> enfants;
}
