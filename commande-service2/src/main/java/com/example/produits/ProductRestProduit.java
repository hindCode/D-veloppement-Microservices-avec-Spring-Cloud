package com.example.produits;

import com.example.entities.Produit;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PRODUIT-SERVICE")
public interface ProductRestProduit {

    @GetMapping("/produits/{id}")
    @CircuitBreaker(name = "produitService", fallbackMethod = "getDefaultProduit")
    Produit getProduitById(@PathVariable Long id);

    @GetMapping("/produits")
    @CircuitBreaker(name = "produitService", fallbackMethod = "getDefaultProduits")
    List<Produit> getAllProduits();

    // Fallback methods for resilience
    default Produit getDefaultProduit(Long id, Exception e) {
        return Produit.builder()
                .id(id)
                .nom("Produit Indisponible")
                .description("Description non disponible")
                .prix(0.0)
                .quantite(0)
                .build();
    }

    default List<Produit> getDefaultProduits(Exception e) {
        return List.of();
    }
}
