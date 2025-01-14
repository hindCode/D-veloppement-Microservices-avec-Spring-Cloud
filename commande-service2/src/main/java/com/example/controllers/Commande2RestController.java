package com.example.controllers;

import com.example.entities.Commande2;
import com.example.entities.Produit;
import com.example.produits.ProductRestProduit;
import com.example.repository.Commande2Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/commandes2") // Préfixe pour différencier les commandes2
public class Commande2RestController {

    private final Commande2Repository commande2Repository;
    private final ProductRestProduit productRestProduit;

    public Commande2RestController(Commande2Repository commande2Repository, ProductRestProduit productRestProduit) {
        this.commande2Repository = commande2Repository;
        this.productRestProduit = productRestProduit;
    }

    // Lire une commande par ID
    @GetMapping("/commandes/{commandeId}")
    public ResponseEntity getCommandeById(@PathVariable Long commandeId) {
        Commande2 commande2 = commande2Repository.findById(commandeId).orElse(null);
        if (commande2 == null) {
            return ResponseEntity.status(404).body(Map.of("errorMessage", "Commande not found"));
        }

        // Appel au client ProductRestProduit pour récupérer les informations du produit associé à la commande
        Produit produit = productRestProduit.getProduitById(commande2.getIdProduit());
        if (produit != null) {
            commande2.setProduit(produit); // Ajouter le produit à la commande
        }

        return ResponseEntity.ok(commande2);
    }

    // Lire toutes les commandes
    @GetMapping("/commandes")
    public List<Commande2> getAllCommandes() {
        return commande2Repository.findAll();
    }

    // Créer une nouvelle commande
    @PostMapping("/commandes")
    public ResponseEntity createCommande(@RequestBody Commande2 commande2) {
        if (commande2.getIdProduit() == null) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", "Produit ID is required"));
        }

        Commande2 savedCommande = commande2Repository.save(commande2);
        return ResponseEntity.status(201).body(savedCommande);
    }

    // Mettre à jour une commande existante
    @PutMapping("/commandes/{commandeId}")
    public ResponseEntity updateCommande(@PathVariable Long commandeId, @RequestBody Commande2 updatedCommande) {
        Optional<Commande2> existingCommande = commande2Repository.findById(commandeId);

        if (existingCommande.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("errorMessage", "Commande not found"));
        }

        Commande2 commandeToUpdate = existingCommande.get();
        commandeToUpdate.setDescription(updatedCommande.getDescription());
        commandeToUpdate.setQuantite(updatedCommande.getQuantite());
        commandeToUpdate.setDate(updatedCommande.getDate());
        commandeToUpdate.setMontant(updatedCommande.getMontant());
        commandeToUpdate.setIdProduit(updatedCommande.getIdProduit());

        Commande2 savedCommande = commande2Repository.save(commandeToUpdate);
        return ResponseEntity.ok(savedCommande);
    }

    // Supprimer une commande par ID
    @DeleteMapping("/commandes/{commandeId}")
    public ResponseEntity deleteCommande(@PathVariable Long commandeId) {
        if (!commande2Repository.existsById(commandeId)) {
            return ResponseEntity.status(404).body(Map.of("errorMessage", "Commande not found"));
        }

        commande2Repository.deleteById(commandeId);
        return ResponseEntity.status(204).build();
    }
}
