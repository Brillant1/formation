package bj.formation.demoprojet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_enfant")
public class Enfant implements Serializable     {
    @Id
    @Column(name = "enfa_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(name = "enfa_nom")
    private String nom;

    @Column(name = "enfa_prenom")
    private String prenom;

    @Column(name = "enfa_dateNaissance")
    private Date dateNaissance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfa_niveau_enfant_id", referencedColumnName = "nive_code")
    private NiveauEnfant niveauEnfant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfa_agen_matricule", referencedColumnName = "agen_matricule")
    private Agent agent;

}
