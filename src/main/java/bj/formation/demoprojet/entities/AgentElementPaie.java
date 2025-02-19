package bj.formation.demoprojet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_agent_element_paie")
public class AgentElementPaie implements Serializable {
    @Id
    @Column(name="agent_element_paie_id", unique = true, nullable = false)
    private Long id;

    @Column(name="agent_element_paie_montant")
    private int montant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_element_paie_elementPaie_code", referencedColumnName = "elementPaie_code")
    private ElementPaie elementPaie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_element_paie_agen_matricule", referencedColumnName = "agen_matricule")
    private Agent agent;

}
