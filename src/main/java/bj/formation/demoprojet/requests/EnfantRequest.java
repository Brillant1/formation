package bj.formation.demoprojet.requests;

import java.time.LocalDate;

import bj.formation.demoprojet.dtos.AgentDto;
import bj.formation.demoprojet.dtos.NiveauEnfantDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnfantRequest {
    private Integer id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private NiveauEnfantDto niveauEnfant;
    private AgentDto agent;
}
