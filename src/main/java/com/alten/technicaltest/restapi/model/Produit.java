package com.alten.technicaltest.restapi.model;

import com.alten.technicaltest.restapi.util.enums.InventoryStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Table(name = "products")
public class Produit {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String code;

        @Column(nullable = false)
        private String name;

        private String description;

        private String image;

        private String category;

        @Column(nullable = false)
        private Double price;

        @Column(nullable = false)
        private Integer quantity;

        @Column(name = "internal_reference")
        private String internalReference;

        @Column(name = "shell_id")
        private Long shellId;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private InventoryStatus inventoryStatus;

        private Double rating;

        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

    }
