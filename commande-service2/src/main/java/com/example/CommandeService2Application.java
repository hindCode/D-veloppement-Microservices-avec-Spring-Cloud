package com.example;

import com.example.entities.Commande2;
import com.example.entities.Produit;
import com.example.produits.ProductRestProduit;
import com.example.repository.Commande2Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class CommandeService2Application {

    public static void main(String[] args) {
        SpringApplication.run(CommandeService2Application.class, args);
    }

    @Bean
    CommandLineRunner start(Commande2Repository commande2Repository, ProductRestProduit productRestProduit) {
        return args -> {
            // Récupérer la liste des produits via le client Feign
            List<Produit> produits = productRestProduit.getAllProduits();
            produits.forEach(produit -> {
                // Créer des commandes pour chaque produit
                Commande2 commande2 = Commande2.builder()
                        .idProduit(produit.getId()) // Associer le produit à la commande
                        .quantite(1) // Quantité de produit dans la commande
                        .date(LocalDate.now()) // Date de la commande
                        .build();

                // Sauvegarder la commande dans la base de données
                commande2Repository.save(commande2);
            });
        };
    }

}
