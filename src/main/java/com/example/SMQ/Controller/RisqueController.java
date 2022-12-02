package com.example.SMQ.Controller;

import com.example.SMQ.Dto.ReclamationDto;
import com.example.SMQ.Dto.RisqueDto;
import com.example.SMQ.Model.Risque;
import com.example.SMQ.Model.Risque;
import com.example.SMQ.Services.ReclamationService;
import com.example.SMQ.Services.RisqueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(path = "api/risque")
@RestController
@AllArgsConstructor
public class RisqueController {
    @Autowired
    private final RisqueService risqueService;
    @GetMapping("/risques")
    public ResponseEntity<List<RisqueDto>> getAllRisques(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<RisqueDto> risques = risqueService.getAllRisque(pageNumber, pageSize);
        return new ResponseEntity<>(risques, HttpStatus.OK);
    }

    @GetMapping(path = "/risque/{idRisque}", produces = "application/json")
    public ResponseEntity<Risque> getRisque(@PathVariable(value = "idRisque") Integer idRisque) {
        return new ResponseEntity<>(risqueService.getRisqueById(idRisque), HttpStatus.OK);
    }

    @PostMapping("/risque")
    public ResponseEntity<Risque> addRisque(@RequestBody Risque risque) {
        Risque r = risqueService.addRisque(risque);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/risque/{idRisque}")
    public Risque updateRisque(@PathVariable Integer idRisque,@RequestBody Risque risque) {
        Risque baseRisque = risqueService.getRisqueById(idRisque);

        if (baseRisque.getDateDetection() != risque.getDateDetection()) {
            baseRisque.setDateDetection(risque.getDateDetection());
        }
        if (baseRisque.getType() != risque.getType()) {
            baseRisque.setType(risque.getType());
        }
        if (baseRisque.getDescription() != risque.getDescription()) {
            baseRisque.setDescription(risque.getDescription());
        }
        if (baseRisque.getNiveau() != risque.getNiveau()) {
            baseRisque.setNiveau(risque.getNiveau());
        }
        if (baseRisque.getLieuOuPromotion() != risque.getLieuOuPromotion()) {
            baseRisque.setLieuOuPromotion(risque.getLieuOuPromotion());
        }
        if (baseRisque.getActeur() != risque.getActeur()) {
            baseRisque.setActeur(risque.getActeur());
        }
        if (baseRisque.getEvolution() != risque.getEvolution()) {
            baseRisque.setEvolution(risque.getEvolution());
        }
        return  risqueService.updateRisque(baseRisque);
    }

    @DeleteMapping("/risque/{idRisque}")
    public void deleteRisque(@PathVariable(name = "idRisque") Integer idRisque) {
        String message = risqueService.deleteRisque(idRisque);

    }

}
