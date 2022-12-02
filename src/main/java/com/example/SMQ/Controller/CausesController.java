package com.example.SMQ.Controller;

import com.example.SMQ.Dto.CausesDto;
import com.example.SMQ.Model.Causes;
import com.example.SMQ.Repository.CausesRepository;
import com.example.SMQ.Services.CausesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(path = "api/causes")
@RestController
@AllArgsConstructor
public class CausesController {
    @Autowired
    private final CausesService causesService;
    private final CausesRepository causesRepository;

    @GetMapping("/causes")
    public ResponseEntity<List<CausesDto>> getAllCauses(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {

        List<CausesDto> causes = causesService.getAllCauses(pageNumber, pageSize);
        return new ResponseEntity<>(causes, HttpStatus.OK);
    }

    @GetMapping(path = "/cause/{idCauses}", produces = "application/json")
    public ResponseEntity<CausesDto> getCause(@PathVariable(value = "idCauses") Integer idCauses) {
        return new ResponseEntity<>(causesService.getCause(idCauses), HttpStatus.OK);
    }

    @PostMapping("/cause")
    public ResponseEntity<Causes> addCause(@RequestBody Causes cause) {
        Causes e = causesService.addCause(cause);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/cause/{idCauses}")
    public Causes updateCause(@PathVariable Integer idCauses, @RequestBody Causes equipment) {
        Causes baseCause = causesService.findCauseById(idCauses);

        if ((baseCause.getReclamation() != equipment.getReclamation()) && (!ObjectUtils.isEmpty(equipment.getReclamation()))) {

            baseCause.setReclamation(equipment.getReclamation());
        }
        if ((baseCause.getActionReclamation() != equipment.getActionReclamation()) && (equipment.getActionReclamation() != null)) {
            baseCause.setActionReclamation(equipment.getActionReclamation());
        }
        if ((baseCause.getDateCreation() != equipment.getDateCreation()) && (equipment.getDateCreation() != null)) {
            baseCause.setDateCreation(equipment.getDateCreation());
        }
        if (baseCause.getDescriptionCause() != equipment.getDescriptionCause()) {
            baseCause.setDescriptionCause(equipment.getDescriptionCause());
        }
        if (baseCause.getNature() != equipment.getNature()) {
            baseCause.setNature(equipment.getNature());
        }
        
        return causesService.updateCause(baseCause);
    }

    @DeleteMapping("/cause/{idCauses}")
    public void deleteCause(@PathVariable(name = "idCauses") Integer idCauses) {
        String message = causesService.deleteCause(idCauses);

    }

}
