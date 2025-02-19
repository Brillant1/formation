package bj.formation.demoprojet.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_agent_grade")
public class AgentGrade implements Serializable {
    @Id
    @Column(name="agent_grade_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="agent_grade_date_debut")
    private LocalDate dateDebut;

    @Column(name="agent_grade_date_fin")
    private LocalDate dateFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aggr_agen_matricule", referencedColumnName = "agen_matricule")
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aggr_grad_code", referencedColumnName = "grad_code")
    private Grade grade;
}
