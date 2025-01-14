package com.example.service;

import com.example.entity.Produit;
import com.example.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Produit getProduitById(Long id) {
        return produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Long id, Produit produit) {
        // Vérification si le produit existe avant la mise à jour
        if (!produitRepository.existsById(id)) {
            throw new RuntimeException("Produit non trouvé");
        }

        // Si le produit existe, on met à jour
        produit.setId(id);  // Assigner l'ID pour la mise à jour
        return produitRepository.save(produit);
    }

    public void deleteProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new RuntimeException("Produit non trouvé");
        }
        produitRepository.deleteById(id);
    }
}
