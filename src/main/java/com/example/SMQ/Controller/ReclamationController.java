package com.example.SMQ.Controller;

import com.example.SMQ.Dto.ReclamationDto;
import com.example.SMQ.Model.Reclamation;
import com.example.SMQ.Repository.ReclamationRepository;
import com.example.SMQ.Services.ReclamationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static java.nio.file.Paths.get;

@CrossOrigin
@RequestMapping(path = "api/reclamation")
@RestController
@AllArgsConstructor
public class ReclamationController {
    @Autowired
    private final ReclamationService reclamationService;
    private final ReclamationRepository reclamationRepository;
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/";
    //work
    @GetMapping("/reclamations")
    public ResponseEntity<List<ReclamationDto>> getAllReclamations(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        List<ReclamationDto> reclamations = reclamationService.getAllReclamations(pageNumber, pageSize);
        return new ResponseEntity<>(reclamations, HttpStatus.OK);
    }
//work
    @GetMapping(path = "/reclamation/{idReclamation}", produces = "application/json")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable(value = "idReclamation") Integer idReclamation) {
        return new ResponseEntity<>(reclamationService.getReclamationById(idReclamation), HttpStatus.OK);
    }
// work
    @PostMapping("/reclamation")
    public ResponseEntity<Reclamation> addReclamation(@RequestBody Reclamation reclamation) {
        Reclamation e = reclamationService.addReclamation(reclamation);
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }


   @PutMapping(consumes = "application/json", produces = "application/json", path = "/reclamation/{idReclamation}")
    public Reclamation updateReclamation(@PathVariable Integer idReclamation,@RequestBody Reclamation reclamation) {
        Reclamation baseReclamation = reclamationService.getReclamationById(idReclamation);

       if (baseReclamation.getDateDetection() != reclamation.getDateDetection()) {
           baseReclamation.setDateDetection(reclamation.getDateDetection());
        }
        if (baseReclamation.getType() != reclamation.getType()) {
            baseReclamation.setType(reclamation.getType());
        }
        if (baseReclamation.getGravite() != reclamation.getGravite()) {
            baseReclamation.setGravite(reclamation.getGravite());
        }
        if (baseReclamation.getDescription() != reclamation.getDescription()) {
            baseReclamation.setDescription(reclamation.getDescription());
        }
        if (baseReclamation.getLieuOuPromotion() != reclamation.getLieuOuPromotion()) {
            baseReclamation.setLieuOuPromotion(reclamation.getLieuOuPromotion());
        }
       if (baseReclamation.getActeur() != reclamation.getActeur()) {
           baseReclamation.setActeur(reclamation.getActeur());
       }
        return  reclamationService.updateReclamation(baseReclamation);
    }
    @DeleteMapping("/reclamation/{idReclamation}")
    public void deleteReclamation(@PathVariable(name = "idReclamation") Integer idReclamation) {
        String message = reclamationService.deleteReclamation(idReclamation);

    }


}
