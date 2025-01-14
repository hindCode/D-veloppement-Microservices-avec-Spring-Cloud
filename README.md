
# D-veloppement-Microservices-avec-Spring-Cloud

## Description du Projet

Ce projet consiste à la mise en œuvre d'une architecture de microservices en utilisant **Spring Boot**, **Spring Cloud**, **Spring Cloud Netflix**, **Spring Cloud Config**, et **Spring Cloud OpenFeign**. L'objectif est de créer des services de gestion des **produits** et des **commandes**, avec une communication entre ces services à travers des API RESTful, et d'assurer la résilience et la tolérance aux pannes via des mécanismes comme **Hystrix** et **Resilience4j**.

Les services sont configurés de manière à garantir une gestion efficace de la communication, avec un système de gestion des erreurs et des timeouts. Ce projet permet aussi d'expérimenter l'intégration avec un service distant de gestion des **clients**.

### Membres de l'Équipe

- **Hind Touzzelti**
- **Mariame Attar**

**Groupe**: 5IIR5 G1

## Fonctionnalités

1. **Produit Service**:
   - Permet de gérer des produits : création, lecture, mise à jour, suppression.
   - Communication avec le service des commandes pour récupérer les informations des produits associés.

2. **Commande Service**:
   - Permet de gérer les commandes : création, consultation.
   - Intégration avec le service produit pour récupérer les détails des produits associés.

3. **Resilience et Tolérance aux Pannes** :
   - Mise en place d'un mécanisme de **Circuit Breaker** pour la gestion des pannes de services (via **Hystrix** et **Resilience4j**).
   - Gestion des **timeouts** pour les appels externes aux microservices.
   
4. **Communication entre Microservices** :
   - Utilisation de **Spring Cloud OpenFeign** pour la communication entre les services produits et commandes.

5. **Timeout et Fallback** :
   - Implémentation de timeout de 5 secondes pour la communication entre les microservices, avec des mécanismes de fallback en cas d'échec des appels aux services externes.

## Prérequis

Pour exécuter ce projet, vous devez avoir installé les outils suivants :

- **Java 17** ou supérieur
- **Maven** (pour la gestion des dépendances et la compilation)
- **Docker** (si vous souhaitez exécuter les services dans des conteneurs)
- **Git** (pour récupérer le projet depuis GitHub)

## Installation

### 1. Cloner le dépôt GitHub

```bash
git clone https://github.com/hindCode/D-veloppement-Microservices-avec-Spring-Cloud.git
cd D-veloppement-Microservices-avec-Spring-Cloud
```

### 2. Installer les dépendances

```bash
mvn clean install
```

### 3. Lancer les services

#### a) Démarrer les services microservices via Spring Boot :

```bash
mvn spring-boot:run
```

#### b) Lancer le service de découverte Eureka (si nécessaire) :

Le service Eureka est utilisé pour la découverte des microservices.

```bash
cd eureka-server
mvn spring-boot:run
```

### 4. Accéder à l'application

- **Commande Service** : http://localhost:8081/api/commandes2
- **Produit Service** : http://localhost:8082/api/produits
- **Eureka Server** (si utilisé) : http://localhost:8761

## Fonctionnement du Projet

1. **Produits et Commandes** :
   - Les produits sont stockés dans un service dédié (`Produit Service`).
   - Les commandes sont gérées par un autre service (`Commande Service`), qui peut récupérer les informations des produits associés à chaque commande.
   
2. **Gestion des erreurs et résilience** :
   - En cas de panne ou de timeout dans la communication entre les services, un mécanisme de **circuit breaker** permet de revenir à des données par défaut via un fallback.
   
3. **Communication entre microservices** :
   - La communication entre les services est réalisée via **Spring Cloud OpenFeign**, qui permet d'appeler des API RESTful de manière déclarative.

## Tests et Débogage

1. Utilisez **Postman** ou un outil similaire pour tester les endpoints.
2. Consultez les logs de l'application pour déboguer toute erreur potentielle pendant l'exécution des services.

## Documentation supplémentaire

Pour toute question ou problème lié à l'utilisation du projet, consultez la documentation officielle de **Spring Cloud** et **Spring Boot**.

- **Spring Cloud** : https://spring.io/projects/spring-cloud
- **Spring Boot** : https://spring.io/projects/spring-boot

## Contribution

Si vous souhaitez contribuer à ce projet, veuillez faire une **pull request** sur ce dépôt. Nous apprécions les contributions et nous vous remercions pour votre aide à améliorer ce projet.

## License

Ce projet est sous la licence **MIT**.

<img width="948" alt="Capture1" src="https://github.com/user-attachments/assets/7c8acc29-62a6-4a32-b2df-07c16975bd57" />

