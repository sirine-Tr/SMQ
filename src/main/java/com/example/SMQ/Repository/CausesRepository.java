package com.example.SMQ.Repository;

import com.example.SMQ.Model.ActionRisque;
import com.example.SMQ.Model.Causes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CausesRepository extends JpaRepository<Causes, Integer> {
}
