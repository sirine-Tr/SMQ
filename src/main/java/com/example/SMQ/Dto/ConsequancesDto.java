package com.example.SMQ.Dto;

import com.example.SMQ.Model.Reclamation;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsequancesDto {
    private int idConsequances;
    private LocalDateTime dateCreation;
    private String descriptionConsequances;
    private ReclamationDto reclamationDto;


}
