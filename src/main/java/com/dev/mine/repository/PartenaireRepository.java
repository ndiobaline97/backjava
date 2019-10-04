package com.dev.mine.repository;

import com.dev.mine.model.Compte;
import com.dev.mine.model.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Integer> {

}
