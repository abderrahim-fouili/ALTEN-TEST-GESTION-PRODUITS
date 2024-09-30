package com.alten.technicaltest.restapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ProduitResponseDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Integer quantity;
    private String internalReference;
    private Long shellId;
    private String inventoryStatus;
    private Double rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

