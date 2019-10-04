package com.dev.mine.repository;

import com.dev.mine.model.Depot;
import com.dev.mine.model.Partenaire;
import com.dev.mine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Integer> {

}
