package com.example.SMQ.Services;

import com.example.SMQ.Dto.ActionReclamationDto;
import com.example.SMQ.Dto.ActionRisqueDto;
import com.example.SMQ.Dto.CausesDto;
import com.example.SMQ.Model.ActionReclamation;
import com.example.SMQ.Model.ActionRisque;
import com.example.SMQ.Model.Causes;
import com.example.SMQ.Repository.ActionReclamationRepository;
import com.example.SMQ.Repository.CausesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class ActionReclamationService {
    private final ActionReclamationRepository actionReclamationRepository;
    //private final CausesRepository causesRepository;
    private final CausesService causesService;

   @Transactional
   public ActionReclamation addActionReclamation(ActionReclamation actionReclamation) {

       if (null == actionReclamation.getCauses()) {
           actionReclamation.setCauses(new Causes());

       }
       ActionReclamation savedActionReclamation = actionReclamationRepository.save(actionReclamation);

       return (savedActionReclamation);
   }

    public ActionReclamationDto getActionReclamationById(Integer idDecision) {
        Optional<ActionReclamation> optionalActionReclamation = actionReclamationRepository.findById(idDecision);
        if (optionalActionReclamation.isPresent()) {
            return mapEntityToDto(optionalActionReclamation.get());
        }
        return null;
    }
    public List<ActionReclamationDto> getAllActionReclamation() {
        List<ActionReclamationDto> actionReclamationDtos = new ArrayList<>();
        List<ActionReclamation> actionReclamations = actionReclamationRepository.findAll();
        actionReclamations.stream().forEach(actionReclamation -> {
            ActionReclamationDto actionRisqueDto = mapEntityToDto(actionReclamation);
            actionReclamationDtos.add(actionRisqueDto);
        });
        return actionReclamationDtos;
    }


    public ActionReclamation updateActionReclamation(ActionReclamation entity) {
        return actionReclamationRepository.save(entity);
    }

    public ActionReclamation findActionReclamationById(Integer idDecision) {
        Optional<ActionReclamation> optionalEquipment = actionReclamationRepository.findById(idDecision);
        if (optionalEquipment.isPresent()) {
            return (optionalEquipment.get());
        }
        return null;
    }

    public String deleteActionReclamation(Integer idDecision) {
        Optional<ActionReclamation> equipment = actionReclamationRepository.findById(idDecision);

        if (equipment.isPresent()) {
            actionReclamationRepository.deleteById(equipment.get().getIdDecision());


            return "Action Reclamation with id: " + idDecision + " deleted successfully!";
        }
        return null;
    }

    public ActionReclamationDto getEquipment(Integer idEquipment) {
        Optional<ActionReclamation> optionalEquipment = actionReclamationRepository.findById(idEquipment);
        if (optionalEquipment.isPresent()) {
            return mapEntityToDto(optionalEquipment.get());
        }
        return null;
    }

    public ActionReclamationDto mapEntityToDto(ActionReclamation actionReclamation) {
        ActionReclamationDto responseDto = new ActionReclamationDto();

        responseDto.setIdDecision(actionReclamation.getIdDecision());
        responseDto.setLibelle(actionReclamation.getLibelle());
        responseDto.setResponsable(actionReclamation.getResponsable());
        responseDto.setDateDecision(actionReclamation.getDateDecision());
        responseDto.setCin(actionReclamation.getCin());
        if(actionReclamation.getCauses() != null)
        {
            responseDto.setCausesDto(causesService.mapEntityToDto(actionReclamation.getCauses()));
        }

        return responseDto;
    }

    public void mapDtoToEntity(ActionReclamationDto actionReclamationDto, ActionReclamation response) {
        response.setIdDecision(actionReclamationDto.getIdDecision());
        response.setLibelle(actionReclamationDto.getLibelle());
        response.setResponsable(actionReclamationDto.getResponsable());
        response.setDateDecision(actionReclamationDto.getDateDecision());
        response.setCin(actionReclamationDto.getCin());
        CausesDto equipmentDto=actionReclamationDto.getCausesDto();
        Causes eq =new Causes();
        causesService.mapDtoToEntity(equipmentDto,eq);
        response.setCauses(eq);

    }
}
