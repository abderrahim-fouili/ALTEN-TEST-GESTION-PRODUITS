package com.alten.technicaltest.restapi.mapper;

import com.alten.technicaltest.restapi.dto.ProduitRequestDTO;
import com.alten.technicaltest.restapi.dto.ProduitResponseDTO;
import com.alten.technicaltest.restapi.model.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Interface qui sert de mapper pour convertir des entités Produit en DTO et inversement.
 * Utilise MapStruct pour automatiser les conversions entre les objets.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MapstructUtil.class)
public interface ProduitMapper {

    // Instance statique du mapper, utilisée pour effectuer les opérations de mapping
    ProduitMapper INSTANCE = Mappers.getMapper(ProduitMapper.class);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "internalReference", target = "internalReference")
    @Mapping(source = "shellId", target = "shellId")
    @Mapping(source = "inventoryStatus", target = "inventoryStatus")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")

    /**
     * Convertit une entité Produit en DTO ProduitResponseDTO.
     *
     * @param produit l'entité Produit à convertir en DTO.
     * @return un objet ProduitResponseDTO.
     */
    public ProduitResponseDTO mapToProduitResponseDTO(Produit produit);

    /**
     * Convertit une liste d'entités Produit en une liste de DTO ProduitResponseDTO.
     *
     * @param produits la liste d'entités Produit à convertir.
     * @return une liste de DTO ProduitResponseDTO.
     */
    public List<ProduitResponseDTO> mapToProduitResponseDTOs(List<Produit> produits);

    /**
     * Convertit un DTO ProduitRequestDTO en entité Produit.
     *
     * @param produitRequestDTO le DTO ProduitRequestDTO à convertir.
     * @return une entité Produit.
     */
    public Produit toProduit(ProduitRequestDTO produitRequestDTO);

    /**
     * Convertit une liste de DTO ProduitRequestDTO en une liste d'entités Produit.
     *
     * @param produitRequestDTOS la liste de DTO ProduitRequestDTO à convertir.
     * @return une liste d'entités Produit.
     */
    public List<Produit> toProduits(List<ProduitRequestDTO> produitRequestDTOS);
}
