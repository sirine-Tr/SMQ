package com.example.SMQ.Services;

import com.example.SMQ.Dto.CausesDto;
import com.example.SMQ.Model.ActionReclamation;
import com.example.SMQ.Model.Causes;
import com.example.SMQ.Model.Reclamation;
import com.example.SMQ.Repository.ActionReclamationRepository;
import com.example.SMQ.Repository.CausesRepository;
import com.example.SMQ.Repository.ReclamationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class CausesService {
    private final CausesRepository causesRepository;
    private final ReclamationRepository reclamationRepository;
    private final ActionReclamationRepository actionReclamationRepository;
    @Autowired
    private final ReclamationService reclamationService;
    @Transactional
    public Causes addCause(Causes cause) {

        if (null == cause.getReclamation()) {
            cause.setReclamation(new Reclamation());
        }
        if (null == cause.getActionReclamation()) {
            List<ActionReclamation> actionReclamation = new ArrayList<>();
            cause.setActionReclamation(actionReclamation);
        }
        Causes savedCause = causesRepository.save(cause);
        return (savedCause);
    }

    public List<CausesDto> getAllCauses(int pageNumber, int pageSize) {
        PageRequest pages = PageRequest.of(pageNumber, pageSize);
        List<CausesDto> causesDtos = new ArrayList<>();
        List<Causes> causes = causesRepository.findAll(pages).getContent();
        causes.stream().forEach(cause -> {
            CausesDto causesDto = mapEntityToDto(cause);
            causesDtos.add(causesDto);
        });
        return causesDtos;
    }
    public Causes updateCause(Causes entity) {
        return causesRepository.save(entity);
    }

    public Causes findCauseById(Integer idCauses) {
        Optional<Causes> optionalCauses = causesRepository.findById(idCauses);
        if (optionalCauses.isPresent()) {
            return (optionalCauses.get());
        }
        return null;
    }

    public String deleteCause(Integer causeId) {
        Optional<Causes> cause = causesRepository.findById(causeId);

        if (cause.isPresent()) {
            causesRepository.deleteById(cause.get().getIdCauses());
            return "Cause with id: " + causeId + " deleted successfully!";
        }
        return null;
    }

    public CausesDto getCause(Integer idCause) {
        Optional<Causes> optionalCause = causesRepository.findById(idCause);
        if (optionalCause.isPresent()) {
            return mapEntityToDto(optionalCause.get());
        }
        return null;
    }
    public CausesDto mapEntityToDto(Causes cause) {
        CausesDto causesDto = new CausesDto();
        causesDto.setIdCauses(cause.getIdCauses());
        causesDto.setNature(cause.getNature());
        causesDto.setDateCreation(cause.getDateCreation());
        causesDto.setDescriptionCause(cause.getDescriptionCause());
        causesDto.setActionReclamation(cause.getActionReclamation());
        if (cause.getReclamation() != null) {
            causesDto.setReclamationDto(reclamationService.mapEntityToDto(cause.getReclamation()));
        }
        return causesDto;
    }

    public void mapDtoToEntity(CausesDto equipmentDto, Causes equipment) {
        equipment.setIdCauses(equipment.getIdCauses());
        equipment.setNature(equipment.getNature());
        equipment.setDateCreation(equipment.getDateCreation());
        equipment.setDescriptionCause(equipment.getDescriptionCause());
        equipment.setActionReclamation(equipment.getActionReclamation());

    }
}
