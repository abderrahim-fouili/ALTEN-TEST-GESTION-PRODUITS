package com.alten.technicaltest.restapi.repository;

import com.alten.technicaltest.restapi.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
