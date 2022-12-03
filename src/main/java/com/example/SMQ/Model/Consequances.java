package com.example.SMQ.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Entity
@Table(name="t_Consequances")
public class Consequances implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idConsequances;
    private LocalDateTime dateCreation;
    private String descriptionConsequances;
    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name="idReclamation")
    private Reclamation reclamation;
}
