package com.example.SMQ.Services;

import com.example.SMQ.Dto.CausesDto;
import com.example.SMQ.Dto.ConsequancesDto;
import com.example.SMQ.Dto.ReclamationDto;
import com.example.SMQ.Model.ActionReclamation;
import com.example.SMQ.Model.Causes;
import com.example.SMQ.Model.Consequances;
import com.example.SMQ.Model.Reclamation;
import com.example.SMQ.Repository.ConsequancesRepository;
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
public class ConsequancesService {
    private final ConsequancesRepository consequancesRepository;
    private final ReclamationRepository reclamationRepository;
    @Autowired
    private final ReclamationService reclamationService;
    @Transactional
    public Consequances addConsequance(Consequances consequances) {
        if (null == consequances.getReclamation()) {
            consequances.setReclamation(new Reclamation());
        }
        Consequances savedConsequances = consequancesRepository.save(consequances);
        return (savedConsequances);
    }
    public ConsequancesDto getConsequanceById(Integer idConsequances) {
        Optional<Consequances> optionalProvenance = consequancesRepository.findById(idConsequances);
        if (optionalProvenance.isPresent()) {
            return mapEntityToDto(optionalProvenance.get());
        }
        return null;
    }
    public Consequances getConsequanceByIdNotDto(int id) {
        return consequancesRepository.findById(id).get();
    }
    public List<ConsequancesDto> getAllConsequances(int pageNumber, int pageSize) {
        PageRequest pages = PageRequest.of(pageNumber, pageSize);
        List<ConsequancesDto> consequancesDtos = new ArrayList<>();
        List<Consequances> causes = consequancesRepository.findAll(pages).getContent();
        causes.stream().forEach(cause -> {
            ConsequancesDto causesDto = mapEntityToDto(cause);
            consequancesDtos.add(causesDto);
        });
        return consequancesDtos;
    }

    public Consequances updateConsequance(Consequances consequance) {
        return consequancesRepository.save(consequance);
    }


    public String deleteConsequances(Integer consequancesId) {
        Optional<Consequances> consequances = consequancesRepository.findById(consequancesId);

        if (consequances.isPresent()) {
            consequancesRepository.deleteById(consequances.get().getIdConsequances());
            return "consequances with id: " + consequancesId + " deleted successfully!";
        }
        return null;
    }


    public ConsequancesDto mapEntityToDto(Consequances consequances) {
        ConsequancesDto consequancesDto = new ConsequancesDto();
        consequancesDto.setIdConsequances(consequances.getIdConsequances());
        consequancesDto.setDescriptionConsequances(consequances.getDescriptionConsequances());
        consequancesDto.setDateCreation(consequances.getDateCreation());
        if (consequances.getReclamation() != null) {
            consequancesDto.setReclamationDto(reclamationService.mapEntityToDto(consequances.getReclamation()));
        }
        return consequancesDto;
    }

    public void mapDtoToEntity(ConsequancesDto consequancesDto, Consequances consequances) {
        consequances.setIdConsequances(consequancesDto.getIdConsequances());
        consequances.setDescriptionConsequances(consequancesDto.getDescriptionConsequances());
        consequances.setDateCreation(consequancesDto.getDateCreation());
    }

}
