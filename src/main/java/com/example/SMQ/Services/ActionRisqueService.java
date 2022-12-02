package com.example.SMQ.Services;

import com.example.SMQ.Dto.ActionRisqueDto;
import com.example.SMQ.Model.ActionRisque;
import com.example.SMQ.Model.Consequances;
import com.example.SMQ.Model.Risque;
import com.example.SMQ.Repository.ActionRisqueRepository;
import com.example.SMQ.Repository.RisqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class ActionRisqueService {
    private final ActionRisqueRepository actionRisqueRepository;
    private final RisqueRepository risqueRepository;



    public ActionRisque findActionRisqueById(Integer idActionRisque) {
        Optional<ActionRisque> optionalActionRisque = actionRisqueRepository.findById(idActionRisque);
        if (optionalActionRisque.isPresent()) {
            return (optionalActionRisque.get());
        }
        return null;

    }
    public ActionRisqueDto getActionRisqueById(Integer idActionRisque) {
        Optional<ActionRisque> optionalActionRisque = actionRisqueRepository.findById(idActionRisque);
        if (optionalActionRisque.isPresent()) {
            return mapEntityToDto(optionalActionRisque.get());
        }
        return null;
    }
    public List<ActionRisqueDto> getAllActionRisques() {
        List<ActionRisqueDto> actionRisqueDtos = new ArrayList<>();
        List<ActionRisque> actionRisques = actionRisqueRepository.findAll();
        actionRisques.stream().forEach(actionRisque -> {
            ActionRisqueDto actionRisqueDto = mapEntityToDto(actionRisque);
            actionRisqueDtos.add(actionRisqueDto);
        });
        return actionRisqueDtos;
    }

    public ActionRisque addActionRisque(ActionRisque actionRisque) {
        Risque risque = risqueRepository.findById(actionRisque.getRisque().getIdRisque()).orElse(null);
        if (null == risque) {
            risque = new Risque();
        }
        return actionRisqueRepository.save(actionRisque);
    }

    public ActionRisque updateActionRisque(ActionRisque entity) {
        return actionRisqueRepository.save(entity);
    }

    public String deleteActionRisque(Integer idActionRisque) {
        Optional<ActionRisque> consequances = actionRisqueRepository.findById(idActionRisque);

        if (consequances.isPresent()) {
            actionRisqueRepository.deleteById(consequances.get().getIdActionRisque());
            return "Action Risque with id: " + idActionRisque + " deleted successfully!";
        }
        return null;
    }
    public ActionRisqueDto mapEntityToDto(ActionRisque actionRisque) {
        ActionRisqueDto actionRisqueDto = new ActionRisqueDto();
        actionRisqueDto.setIdActionRisque(actionRisque.getIdActionRisque());
        actionRisqueDto.setLibelle(actionRisque.getLibelle());
        actionRisqueDto.setResponsable(actionRisque.getResponsable());
        actionRisqueDto.setCin(actionRisque.getCin());
        actionRisqueDto.setDateDecision(actionRisque.getDateDecision());
        actionRisqueDto.setRisque(actionRisque.getRisque());
        return actionRisqueDto;
    }
}
