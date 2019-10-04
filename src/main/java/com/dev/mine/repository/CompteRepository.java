package com.dev.mine.repository;

import com.dev.mine.model.Compte;
import com.dev.mine.model.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
    Optional<Compte> findByNumCompte(String numCompte);
    Boolean existsById(int id);
}