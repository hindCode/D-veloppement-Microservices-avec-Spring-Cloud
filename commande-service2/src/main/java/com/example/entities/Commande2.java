package com.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commande2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer quantite;
    private LocalDate date;
    private Double montant;
    private Long idProduit; // Colonne dans la base de données pour référencer un produit

    @Transient
    private Produit produit; // Champ non persistant utilisé pour stocker les détails du produit
}
