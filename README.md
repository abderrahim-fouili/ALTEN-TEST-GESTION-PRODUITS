# API de Gestion des Produits

## Description

Cette API de **gestion des produits** est développée en **Java** avec **Spring Boot** (version **3.2.2**) dans le cadre d'un **test technique pour ALTEN**. Elle fournit une interface RESTful permettant de gérer les produits, avec des fonctionnalités pour créer, récupérer, mettre à jour et supprimer des produits.

## Fonctionnalités

L'API inclut les fonctionnalités suivantes :

- **Créer un nouveau produit** : Ajouter un produit avec des informations telles que le code, le nom, la catégorie, le prix, etc.
- **Récupérer tous les produits** : Obtenir la liste de tous les produits disponibles.
- **Récupérer un produit par ID** : Obtenir les détails d'un produit spécifique à partir de son ID.
- **Mettre à jour les détails d'un produit** : Mettre à jour partiellement les informations d'un produit s'il existe.
- **Supprimer un produit** : Retirer un produit du système.

## Pile Technologique

- **Java** (JDK 17 ou supérieur)
- **Spring Boot** (version **3.2.2**)
- **H2** (base de données)
- **Maven** (pour la gestion de projet et des dépendances)
- **Swagger** (pour tester les API)
- **Docker** (facultatif)
- **Docker Compose** (facultatif)

## Endpoints de l'API

| Ressource           | POST                   | GET                            | PATCH                                    | PUT | DELETE           |
| ------------------  | ---------------------- | ------------------------------ | ---------------------------------------- | --- | ---------------- |
| **/products**       | Créer un nouveau produit | Récupérer tous les produits     | X                                        | X   | X                |
| **/products/{id}**  | X                      | Récupérer les détails du produit {id} | Mettre à jour partiellement le produit {id} s'il existe | X   | Supprimer le produit {id} |

## Modèle de Produit

Un produit possède les caractéristiques suivantes :

```typescript
class Product {
  id: number;
  code: string;
  name: string;
  description: string;
  image: string;
  category: string;
  price: number;
  quantity: number;
  internalReference: string;
  shellId: number;
  inventoryStatus: "INSTOCK" | "LOWSTOCK" | "OUTOFSTOCK";
  rating: number;
  createdAt: number;
  updatedAt: number;
}
```
# Installation et Exécution

## Environnement Local

- **Compilez et exécutez le projet :**

  ```bash
  mvn clean install
  mvn spring-boot:run
  ```

- **Accédez à l'API à l'adresse suivante :** [http://localhost:8080/alten/technical-test/v1/products-management/products](http://localhost:8080/alten/technical-test/v1/products-management/products)

- **Accédez à Swagger pour tester les API à l'adresse suivante :** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Avec Docker et Docker Compose

- **Assurez-vous d'avoir Docker et Docker Compose installés sur votre machine.**

- **Exécutez la commande suivante pour démarrer l'application :**

  ```bash
  docker-compose up --build
  ```

- **Accédez à l'API à l'adresse suivante :** [http://localhost:8080/alten/technical-test/v1/products-management/products](http://localhost:8080/alten/technical-test/v1/products-management/products)

- **Accédez à Swagger pour tester les API à l'adresse suivante :** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Exemple de Jeu de Tests JSON

```typescript
[
  {
    "code": "PRD12345",
    "name": "Produit Exemple 1",
    "description": "Description de produit 1.",
    "image": "image1.jpg",
    "category": "Catégorie Exemple 1",
    "price": 99.99,
    "quantity": 100,
    "internalReference": "REF123",
    "shellId": 1,
    "inventoryStatus": "INSTOCK",
    "rating": 4.5,
    "createdAt": "2024-09-27T12:00:00",
    "updatedAt": "2024-09-27T12:00:00"
  },
  {
    "code": "PRD12346",
    "name": "Produit Exemple 2",
    "description": "Description de produit 2.",
    "image": "image2.jpg",
    "category": "Catégorie Exemple 2",
    "price": 149.99,
    "quantity": 50,
    "internalReference": "REF124",
    "shellId": 2,
    "inventoryStatus": "LOWSTOCK",
    "rating": 4.0,
    "createdAt": "2024-09-27T12:00:00",
    "updatedAt": "2024-09-27T12:00:00"
  },
  {
    "code": "PRD12347",
    "name": "Produit Exemple 3",
    "description": "Description de produit 3.",
    "image": "image3.jpg",
    "category": "Catégorie Exemple 3",
    "price": 199.99,
    "quantity": 0,
    "internalReference": "REF125",
    "shellId": 3,
    "inventoryStatus": "OUTOFSTOCK",
    "rating": 4.8,
    "createdAt": "2024-09-27T12:00:00",
    "updatedAt": "2024-09-27T12:00:00"
  },
  {
    "code": "PRD12348",
    "name": "Produit Exemple 4",
    "description": "Description de produit 4.",
    "image": "image4.jpg",
    "category": "Catégorie Exemple 4",
    "price": 299.99,
    "quantity": 30,
    "internalReference": "REF126",
    "shellId": 4,
    "inventoryStatus": "INSTOCK",
    "rating": 5.0,
    "createdAt": "2024-09-27T12:00:00",
    "updatedAt": "2024-09-27T12:00:00"
  }
]
```