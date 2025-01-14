package com.example.entities;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Produit {
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private Integer quantite;
}