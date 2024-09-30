package com.alten.technicaltest.restapi.api;

import com.alten.technicaltest.restapi.dto.ProduitRequestDTO;
import com.alten.technicaltest.restapi.dto.ProduitResponseDTO;
import com.alten.technicaltest.restapi.mapper.ProduitMapper;
import com.alten.technicaltest.restapi.model.Produit;
import com.alten.technicaltest.restapi.service.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.alten.technicaltest.restapi.util.constants.GlobalConstants.*;

@RestController
@RequestMapping(BASE_PATH + "products")
@Tag(name = PRODUITS_APIS_TAG, description = INFO_API_DESCRIPTION_PRODUITS)
@Slf4j
public record ProduitsApi(ProduitService produitService, ProduitMapper produitMapper) {

    /**
     * Endpoint pour récupérer un produit par son ID.
     *
     * @param productId ID du produit à récupérer.
     * @return ResponseEntity contenant le produit trouvé ou une réponse 404.
     */
    @GetMapping("/{productId}")
    @Operation(summary = "Récupérer un produit par ID", description = "Récupérer un produit spécifique à partir de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opération réussie"),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<ProduitResponseDTO> findProductById(@PathVariable Long productId) {
        log.info("Début de l'API : Récupérer le produit avec ID '{}'", productId);
        Produit produit = produitService.findById(productId);

        // Si le produit n'est pas trouvé, renvoyer une réponse 404
        if (produit == null){
            log.info("Fin de l'API : Aucun produit trouvé");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Mapper l'entité produit vers un DTO pour la réponse
        ProduitResponseDTO produitResponseDTO = produitMapper.mapToProduitResponseDTO(produit);
        log.info("Fin de l'API : Produit avec ID '{}' récupéré", productId);
        return ResponseEntity.ok(produitResponseDTO);
    }

    /**
     * Endpoint pour récupérer tous les produits enregistrés.
     *
     * @return ResponseEntity contenant la liste des produits ou une réponse 404 si vide.
     */
    @GetMapping
    @Operation(summary = "Récupérer tous les produits", description = "Récupérer une liste de tous les produits enregistrés")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opération réussie"),
            @ApiResponse(responseCode = "404", description = "Aucun produit trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<List<ProduitResponseDTO>> getAllProducts() {
        log.info("Début de l'API : Récupérer tous les produits");
        List<Produit> produits = produitService.findAll();

        // Si la liste est vide, renvoyer une réponse 404
        if (produits.isEmpty()) {
            log.info("Fin de l'API : Aucun produit trouvé");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Mapper chaque produit en DTO et retourner la liste
        List<ProduitResponseDTO> produitDTOs = produits.stream()
                .map(produitMapper::mapToProduitResponseDTO)
                .collect(Collectors.toList());
        log.info("Fin de l'API : Liste de produits récupérée");
        return ResponseEntity.ok(produitDTOs);
    }

    /**
     * Endpoint pour ajouter un nouveau produit.
     *
     * @param paramProductDTO Le produit à ajouter, encapsulé dans un DTO.
     * @return ResponseEntity contenant le produit créé ou une réponse 422 si invalide.
     */
    @PostMapping
    @Operation(summary = "Ajouter un produit", description = "Créer un nouveau produit et renvoyer le produit créé")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produit créé avec succès"),
            @ApiResponse(responseCode = "422", description = "Données invalides pour le produit"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<Produit> addNewProduct(@RequestBody ProduitRequestDTO paramProductDTO) {
        log.info("Début de l'API : Ajout d'un nouveau produit '{}'", paramProductDTO);

        // Convertir le DTO en entité produit
        Produit newProduit = produitMapper.toProduit(paramProductDTO);

        // Pesister le produit dans la base de données
        Produit createdProduit = produitService.create(newProduit);

        // Créer une URI de localisation pour le produit créé
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduit.getId())
                .toUri();

        log.info("Fin de l'API : Nouveau produit ajouté");
        return ResponseEntity.created(location).body(createdProduit);
    }

    /**
     * Endpoint pour supprimer un produit existant.
     *
     * @param productId ID du produit à supprimer.
     * @return ResponseEntity avec un statut 204 en cas de succès ou 404 si non trouvé.
     */
    @DeleteMapping("/{productId}")
    @Operation(summary = "Supprimer un produit", description = "Supprimer un produit existant en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produit supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<ProduitResponseDTO> deleteProduct1(@PathVariable Long productId) {
        log.info("Début de l'API : Suppression du produit avec ID '{}'", productId);
        Produit produit = produitService.findById(productId);
        // Si le produit existe, le supprimer et renvoyer une réponse OK avec le produit supprimé
        if (produit == null){
            log.info("Fin de l'API : Produit avec ID '{}' non trouvé pour suppression", productId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
            produitService.delete(productId);
            ProduitResponseDTO produitResponseDTO = produitMapper.mapToProduitResponseDTO(produit);
            log.info("Produit avec ID '{}' supprimé", productId);
            log.info("Fin de l'API : Produit avec ID '{}' est trouvé pour suppression", productId);
            return ResponseEntity.ok(produitResponseDTO);

    }

    /**
     * Endpoint pour mettre à jour un produit existant.
     *
     * @param id du produit à mettre à jour.
     * @param updateDTO Les champs à mettre à jour encapsulés dans un DTO.
     * @return ResponseEntity contenant le produit mis à jour ou une réponse 404 si non trouvé.
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Mettre à jour un produit", description = "Mettre à jour un produit existant en fonction de son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produit mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<Produit> patchUpdateProduct(@PathVariable Long id, @RequestBody ProduitRequestDTO updateDTO) {
        log.info("Début de l'API : Mise à jour du produit avec ID '{}'", id);

        // Chercher le produit à partir de son ID
        Produit produit = produitService.findById(id);

        // Si le produit existe, appliquer les modifications et sauvegarder
        if (produit == null) {
            log.info("Fin de l'API : Produit avec ID '{}' non trouvé pour mise à jour", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Produit updatedProduit = produitService.updateProduct(id, updateDTO);
        log.info("Produit avec ID '{}' mis à jour", id);
        log.info("Fin de l'API : Produit avec ID '{}' est trouvé pour mise à jour", id);
        return ResponseEntity.ok(updatedProduit);



    }
}
