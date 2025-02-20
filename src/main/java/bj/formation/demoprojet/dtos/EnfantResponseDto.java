package bj.formation.demoprojet.dtos;

import bj.formation.demoprojet.entities.Agent;
import bj.formation.demoprojet.entities.NiveauEnfant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnfantResponseDto{
        Long id;
        String nom;
        String prenom;
        LocalDate datNaissance;
        NiveauEnfant niveauEnfant;
        Agent agent;
}
