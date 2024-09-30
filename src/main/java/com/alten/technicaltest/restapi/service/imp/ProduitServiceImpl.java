package com.alten.technicaltest.restapi.service.imp;

import com.alten.technicaltest.restapi.dto.ProduitRequestDTO;
import com.alten.technicaltest.restapi.exception.NotFoundException;
import com.alten.technicaltest.restapi.mapper.ProduitMapper;
import com.alten.technicaltest.restapi.model.Produit;
import com.alten.technicaltest.restapi.repository.ProduitRepository;
import com.alten.technicaltest.restapi.service.ProduitService;
import com.alten.technicaltest.restapi.util.enums.InventoryStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional  // Gestion des transactions pour garantir la cohérence des données
@Slf4j  // Pour la journalisation
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    private ProduitRepository produitRepository;  // Injecte le repository pour la gestion des produits

    @Autowired
    private ProduitMapper produitMapper;  // Injecte le mapper pour convertir les entités vers les DTOs

    /**
     * Récupérer tous les produits.
     *
     * @return Liste de tous les produits.
     */
    @Transactional
    public List<Produit> findAll() {
        // Retourne tous les produits en utilisant le repository
        return this.produitRepository.findAll();
    }

    /**
     * Récupérer un produit spécifique par son ID.
     *
     * @param id ID du produit à rechercher.
     * @return Produit correspondant à l'ID fourni.
     * @throws NotFoundException si aucun produit n'est trouvé avec l'ID donné.
     */
    @Transactional(readOnly = true)
    public Produit findById(Long id) {
       // Cherche le produit par son ID, sinon renvoie une exception NotFoundException
        return this.produitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produit non trouvé"));

    }

    @Override
    /**
     * Créer un nouveau produit.
     *
     * @param produitToCreate Objet produit représentant le nouveau produit.
     * @return Le produit nouvellement créé.
     */
    public Produit create(Produit produitToCreate) {
        // Enregistre le produit en base de données et retourne le produit créé
        Produit produit = produitRepository.save(produitToCreate);
        return produit;
    }

    @Override
    public Produit update(Long aLong, Produit entity) {
        // Cette méthode n'est pas encore implémentée
        return null;
    }

    /**
     * Supprimer un produit par son ID.
     *
     * @param aLong ID du produit à supprimer.
     */
    @Override
    public void delete(Long aLong) {
        // Cherche le produit avant de le supprimer
        Produit produit = this.findById(aLong);
        this.produitRepository.delete(produit);  // Supprime le produit trouvé
    }

    /**
     * Mettre à jour un produit existant par son ID.
     *
     * @param id ID du produit à mettre à jour.
     * @param updateDTO DTO contenant les champs à mettre à jour.
     * @return Produit mis à jour.
     */
    @Override
    public Produit updateProduct(Long id, ProduitRequestDTO updateDTO) {
        // Récupère le produit à partir de son ID ou lance une exception NotFoundException
        Produit produitupdated = produitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produit non trouvé"));

        // Met à jour chaque champ du produit s'il est présent dans le DTO
        if (updateDTO.getCode() != null) {
            produitupdated.setCode(updateDTO.getCode());
        }
        if (updateDTO.getName() != null) {
            produitupdated.setName(updateDTO.getName());
        }
        if (updateDTO.getDescription() != null) {
            produitupdated.setDescription(updateDTO.getDescription());
        }
        if (updateDTO.getImage() != null) {
            produitupdated.setImage(updateDTO.getImage());
        }
        if (updateDTO.getCategory() != null) {
            produitupdated.setCategory(updateDTO.getCategory());
        }
        if (updateDTO.getPrice() != null) {
            produitupdated.setPrice(updateDTO.getPrice());
        }
        if (updateDTO.getQuantity() != null) {
            produitupdated.setQuantity(updateDTO.getQuantity());
        }
        if (updateDTO.getInternalReference() != null) {
            produitupdated.setInternalReference(updateDTO.getInternalReference());
        }
        if (updateDTO.getShellId() != null) {
            produitupdated.setShellId(updateDTO.getShellId());
        }
        if (updateDTO.getInventoryStatus() != null) {
            produitupdated.setInventoryStatus(InventoryStatus.valueOf(updateDTO.getInventoryStatus()));
        }
        if (updateDTO.getRating() != null) {
            produitupdated.setRating(updateDTO.getRating());
        }

        // Sauvegarde le produit mis à jour dans le repository
        return produitRepository.save(produitupdated);
    }
}
