package bj.formation.demoprojet.dtos;

import bj.formation.demoprojet.entities.Agent;

import java.time.LocalDate;

public class AgentDto {
        String matricule;
        String nom;
        String prenom;
        LocalDate dateNaissance;
        Integer nbreEnfant;
}
