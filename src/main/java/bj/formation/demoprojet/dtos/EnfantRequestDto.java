package bj.formation.demoprojet.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnfantRequestDto{
        String nom;
        String prenom;
        LocalDate dateNaissance;
        String code_niveau_enfant;
        String agentMatricule;
}
