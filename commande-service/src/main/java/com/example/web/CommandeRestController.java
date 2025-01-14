package com.example.web;

import com.example.entities.Commande;
import com.example.repository.CommandeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommandeRestController {
    private final CommandeRepository commandeRepository;

    public CommandeRestController(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @GetMapping("/commandes")
    public List<Commande> listCommandes() {
        return commandeRepository.findAll();
    }

    @GetMapping("/commandes/{id}")
    public Commande commandeById(@PathVariable Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @PostMapping("/commandes")
    public Commande saveCommande(@RequestBody Commande commande) {
        return commandeRepository.save(commande);
    }

    @PutMapping("/commandes/{id}")
    public Commande updateCommande(@RequestBody Commande commande, @PathVariable Long id) {
        commande.setId(id);
        return commandeRepository.save(commande);
    }

    @DeleteMapping("/commandes/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeRepository.deleteById(id);
    }
}
