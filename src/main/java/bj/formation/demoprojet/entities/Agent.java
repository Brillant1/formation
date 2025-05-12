package bj.formation.demoprojet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_agent")
public class Agent implements Serializable {

    @Id
    @Column(name = "agen_matricule", unique = true, nullable = false)
    private String matricule;

    @Column(name = "agen_nom")
    private String nom;

    @Column(name = "agen_prenom")
    private String prenom;

    @Column(name ="agen_date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "agen_indice")
    private  int indice;

    @Column(name = "agen_salaire_base")
    private  int salaireBase;

    @Column(name = "agen_allocation_familiale")
    private  int allocationFamiliale;

    @Column(name = "agen_nbre_enfant")
    private  int nbreEnfant;

    @Column(name = "agen_actif")
    private  Boolean actif = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agent")
    private Set<AgentGrade> listAgentGrade;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agent")
    private Set<AgentElementPaie> listAgentElementPaie;
}
