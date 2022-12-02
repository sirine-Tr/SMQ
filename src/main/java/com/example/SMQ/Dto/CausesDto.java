package com.example.SMQ.Dto;

import com.example.SMQ.Model.ActionReclamation;
import com.example.SMQ.Model.Reclamation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CausesDto {
    private int idCauses;
    private LocalDateTime dateCreation;
    private String descriptionCause;
    private String nature;
    private ReclamationDto reclamationDto;
    private List<ActionReclamation> actionReclamation;
}
