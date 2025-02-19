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
@Table(name = "t_generation")
public class Generation implements Serializable {
    @Id
    @Column(name="gene_id", unique = true, nullable = false)
    private Long id;

    @Column(name="gene_montant")
    private int montant;

    @Column(name="gene_echeance")
    private Date echeance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="gene_agen_matricule", referencedColumnName = "agen_matricule")
    private Agent agent;
}
