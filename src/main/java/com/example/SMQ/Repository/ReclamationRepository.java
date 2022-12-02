package com.example.SMQ.Repository;

import com.example.SMQ.Model.ActionRisque;
import com.example.SMQ.Model.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {
   // Optional<Reclamation> findAllByName(String name);
}
