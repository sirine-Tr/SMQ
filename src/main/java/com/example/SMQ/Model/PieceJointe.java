package com.example.SMQ.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Entity
@Table(name="t_PieceJointe")
public class PieceJointe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPieceJointe ;
    private String name;
    @Lob
    private byte[] file;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pieceJointe")
    private Reclamation reclamation;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pieceJointeRisque")
    private Risque risque;
}
