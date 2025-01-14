package com.example.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestController {

    // Propriétés personnalisées pour le microservice-commandes
    @Value("${mes-config-ms.commandes-last}")
    private int commandesLast;

    @Value("${commande.service.default.currency}")
    private String defaultCurrency;

    @Value("${commande.service.default.language}")
    private String defaultLanguage;

    @Value("${commande.service.default.region}")
    private String defaultRegion;

    @GetMapping("/configTest")
    public Map<String, Object> config() {
        return Map.of(
                "commandesLast", commandesLast,
                "defaultCurrency", defaultCurrency,
                "defaultLanguage", defaultLanguage,
                "defaultRegion", defaultRegion
        );
    }
}
