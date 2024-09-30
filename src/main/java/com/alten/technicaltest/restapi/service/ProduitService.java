package com.alten.technicaltest.restapi.service;

import com.alten.technicaltest.restapi.dto.ProduitRequestDTO;
import com.alten.technicaltest.restapi.model.Produit;
public interface ProduitService extends CrudService<Long, Produit> {
    Produit updateProduct(Long id, ProduitRequestDTO updateDTO);
}
