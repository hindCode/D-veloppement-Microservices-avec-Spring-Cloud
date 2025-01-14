package com.example;

import com.example.entities.Commande;
import com.example.repository.CommandeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class CommandeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommandeServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CommandeRepository commandeRepository) {
        return args -> {
            commandeRepository.save(Commande.builder()
                    .description("Commande de fournitures de bureau")
                    .quantite(5)
                    .date(LocalDate.now().minusDays(2)) // Exemple : il y a 2 jours
                    .montant(250.75)
                    .build());

            commandeRepository.save(Commande.builder()
                    .description("Commande de matériel informatique")
                    .quantite(2)
                    .date(LocalDate.now().minusDays(10)) // Exemple : il y a 10 jours
                    .montant(1250.00)
                    .build());
        };
    }
}
