package com.example;

import com.example.entity.Produit;
import com.example.repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProduitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduitServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initProduits(ProduitRepository produitRepository) {
        return args -> {
            // Vérifier si la table de produits est vide pour éviter d'ajouter plusieurs fois
            if (produitRepository.count() == 0) {
                // Ajouter des produits à la base de données
                Produit produit1 = Produit.builder()
                        .nom("Produit A")
                        .description("Description du produit A")
                        .prix(100.0)
                        .quantite(50)
                        .build();

                Produit produit2 = Produit.builder()
                        .nom("Produit B")
                        .description("Description du produit B")
                        .prix(150.0)
                        .quantite(30)
                        .build();

                Produit produit3 = Produit.builder()
                        .nom("Produit C")
                        .description("Description du produit C")
                        .prix(200.0)
                        .quantite(20)
                        .build();

                produitRepository.saveAll(List.of(produit1, produit2, produit3));
                System.out.println("Produits initialisés dans la base de données.");
            } else {
                System.out.println("Les produits existent déjà dans la base de données.");
            }
        };
    }
}
