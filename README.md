# Java Spring Boot Search REST API


A Spring Boot REST API for managing and searching products stored in a MySQL database. Supports search by ID, search by name/description using both JPQL and native SQL queries, and product creation.


---


## Table of Contents


- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Architecture Overview](#architecture-overview)
- [API Endpoints](#api-endpoints)
- [Data Model](#data-model)
- [Database Configuration](#database-configuration)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)


---


## Tech Stack


| Technology              | Version          |
|-------------------------|------------------|
| Java                    | 21               |
| Spring Boot             | 4.0.6            |
| Spring Web MVC          | -                |
| Spring Data JPA         | -                |
| Hibernate               | (via JPA)        |
| MySQL                   | -                |
| Lombok                  | -                |
| Spring Boot Actuator    | -                |
| Maven                   | Wrapper included |


---


## Project Structure


```
src/
└── main/
    └── java/
        └── springboot/search_rest_api/
            ├── controller/
            │   └── ProductController.java       # REST endpoints
            ├── entity/
            │   └── Product.java                 # JPA entity mapped to 'products' table
            ├── respository/
            │   └── ProductRepository.java       # JPA repository with custom queries
            ├── service/
            │   ├── ProductService.java          # Service interface / class
            │   └── impl/                        # Service implementation
            └── SearchRestApiApplication.java    # Application entry point
```


---


## Architecture Overview


```
Client
  │
  ▼
ProductController       (@RestController — handles HTTP requests)
  │
  ▼
ProductService          (Business logic layer)
  │
  ▼
ProductRepository       (Spring Data JPA — JPQL + native SQL queries)
  │
  ▼
MySQL Database          (products table)
```


---


## API Endpoints


Base path: `/api/v1/products`


### Get Product by ID


```
GET /api/v1/products/search/{id}
```


| Parameter | Type   | Description              |
|-----------|--------|--------------------------|
| `id`      | `Long` | Unique product identifier |


**Response — 200 OK**
```json
{
  "id": 1,
  "sku": "SKU-001",
  "name": "Product Name",
  "description": "Product description",
  "active": true,
  "imageUrl": "https://example.com/image.jpg",
  "dateCreated": "2024-01-01T10:00:00",
  "dateUpdated": "2024-01-02T10:00:00"
}
```


---


### Search Products by Name or Description


```
GET /api/v1/products/search?query={searchTerm}
```


| Parameter | Type     | Description                    |
|-----------|----------|--------------------------------|
| `query`   | `String` | Text to search in name/description |


**Response — 200 OK**
```json
[
  {
    "id": 1,
    "name": "Product Name",
    "description": "Contains the search term"
  }
]
```


---


### Create Product


```
POST /api/v1/products/
```


**Request Body**
```json
{
  "sku": "SKU-002",
  "name": "New Product",
  "description": "Product description",
  "active": true,
  "imageUrl": "https://example.com/image.jpg"
}
```


**Response — 200 OK** — returns the saved product.


---


## Data Model


### Product Entity (`products` table)


| Field         | Type            | Description                        |
|---------------|-----------------|------------------------------------|
| `id`          | `Long`          | Auto-generated primary key         |
| `sku`         | `String`        | Stock-keeping unit identifier      |
| `name`        | `String`        | Product name                       |
| `description` | `String`        | Product description                |
| `active`      | `boolean`       | Whether the product is active      |
| `imageUrl`    | `String`        | URL of the product image           |
| `dateCreated` | `LocalDateTime` | Auto-set on creation (Hibernate)   |
| `dateUpdated` | `LocalDateTime` | Auto-updated on change (Hibernate) |


---


## Repository Queries


`ProductRepository` extends `JpaRepository<Product, Long>` and includes:


| Method                             | Type          | Description                                      |
|------------------------------------|---------------|--------------------------------------------------|
| `searchProducts(query)`            | JPQL          | LIKE search on name and description              |
| `searchProductsSQL(query)`         | Native SQL    | Same search using raw SQL                        |
| `findByName(name)`                 | Derived query | Find by exact name                               |
| `findById(id)`                     | Derived query | Find by ID                                       |
| `findByNameOrDescription(n, desc)` | Derived query | Find by name or description                      |


---


## Database Configuration


Add the following to `src/main/resources/application.properties`:


```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```


---


## Getting Started


### Prerequisites


- Java 21+
- MySQL 8+
- Maven (or use the included `mvnw` wrapper)


### Clone the Repository


```bash
git clone https://github.com/HemanthkumarS0120/Java_SpringBoot-SearchRestAPI-Project.git
cd Java_SpringBoot-SearchRestAPI-Project
```


---


## Running the Application


**Linux/Mac:**
```bash
./mvnw spring-boot:run
```


**Windows:**
```bash
mvnw.cmd spring-boot:run
```


The application starts on **http://localhost:8080**.


Spring Boot Actuator health check: `http://localhost:8080/actuator/health`



