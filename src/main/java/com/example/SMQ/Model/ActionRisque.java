package com.example.SMQ.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Entity
@Table(name="t_ActionRisque")
public class ActionRisque implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idActionRisque;
    private String libelle;
    private String responsable;
    private String cin;
    private LocalDateTime dateDecision;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name="idRisque")
    private Risque risque;
}
