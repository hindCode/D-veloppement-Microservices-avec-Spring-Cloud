package com.example.controllers;

import com.example.entities.Commande2;
import com.example.entities.Produit;
import com.example.produits.ProductRestProduit;
import com.example.repository.Commande2Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/commandes2") // Préfixe pour différencier les commandes2
public class Commande2RestController {

    private final Commande2Repository commande2Repository;
    private final ProductRestProduit productRestProduit;

    public Commande2RestController(Commande2Repository commande2Repository, ProductRestProduit productRestProduit) {
        this.commande2Repository = commande2Repository;
        this.productRestProduit = productRestProduit;
    }

    @GetMapping("/commandes/{commandeId}")
    public ResponseEntity getCommandeById(@PathVariable Long commandeId) {
        Commande2 commande2 = commande2Repository.findById(commandeId).orElse(null);
        if (commande2 == null) {
            return ResponseEntity.internalServerError().body(Map.of("errorMessage", "Commande not found"));
        }

        // Appel au client ProductRestProduit pour récupérer les informations du produit associé à la commande
        Produit produit = productRestProduit.getProduitById(commande2.getIdProduit());
        if (produit != null) {
            commande2.setProduit(produit); // Ajouter le produit à la commande
        }

        return ResponseEntity.ok(commande2);
    }


    @GetMapping("/commandes")
    public List<Commande2> getAllCommandes() {
        return commande2Repository.findAll();
    }
}
