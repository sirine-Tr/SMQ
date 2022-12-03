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
@Table(name="t_ActionReclamation")
public class ActionReclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idDecision;
    private String libelle;
    private String responsable;
    private String cin;
    private LocalDateTime dateDecision;
    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name="idCauses")
    private Causes causes;


}
