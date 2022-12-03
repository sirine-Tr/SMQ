package com.example.SMQ.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Entity
@Table(name="t_Causes")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","causes"})
public class Causes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCauses;
    private LocalDateTime dateCreation;
    private String descriptionCause;
    private String nature;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name="idReclamation")
    private Reclamation reclamation;

    @OneToMany(mappedBy = "causes")
    @JsonIgnore
    private List<ActionReclamation> actionReclamation;
}
