package com.example.health;


import com.example.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CustomHealthIndicator(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Health health() {
        // Vérifie s'il existe des commandes dans la table COMMANDE
        long commandesCount = commandeRepository.count();

        if (commandesCount > 0) {
            // Si des commandes sont présentes, on retourne un statut "UP"
            return Health.up().withDetail("commandes", commandesCount).build();
        } else {
            // Sinon, on retourne un statut "DOWN"
            return Health.down().withDetail("commandes", 0).build();
        }
    }
    /*
    La méthode health() vérifie si des commandes existent dans la table COMMANDE en comptant les enregistrements avec commandeRepository.count().
Si des commandes existent, le service renverra un statut UP (avec le nombre de commandes). Sinon, il renverra un statut DOWN.
     */
}