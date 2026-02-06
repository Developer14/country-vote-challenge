# Country Vote API

REST API intended to serve the front application.

## Table of Contents
*   [Assumptions](#assumptions)
*   [Design Choices](#design-choices)
*   [Trade-offs](#trade-offs)
*   [Getting Started](#getting-started)
*   [Prerequisites](#prerequisites)
*   [Running the Application](#running-the-application)
*   [API Endpoints](#api-endpoints)
*   [Swagger](#swagger)
*   [Technologies Used](#technologies-used)

## Assumptions
 
* Persistence is not specified, so I decided to use an in-memory database
* Countries retrieved from the public api did not have an id, so assuming that countries do not change their names, country name was used as the unique identifier.

## Design Choices

For the main functional requirement of listing country details and their votes I identified that a merge of user data and countries had to be performed. In order to achieve that merge operation I found two possible approaches, 1) persist country details in another relational table, or 2) merge both datasources programmatically. I chose the latter for simplicity and given that country data is rather static and can be stored in a cache.

Standard layered architecture package structure to organise code

Layers:
- config: spring configuration classes
- controller: HTTP endpoints
- service: Business logic
- repository: Data access
- models: Database entity models
- dto: Transferencia de datos
- client: client classes for external api
- exception: exception handling classes
- constant: common app constants

I decided to use a relational database to persist user data (personal info and favorite country). The table structure is created through hibernate annotations (in-memory db)

I implemented spring cache (default in-memory) to reduce the number of executions to external countries api assuming that it's static data that does not change often.

Standard synchronous code in spring applications for simplicity

## Trade-offs

Model/Dto mapping implemented in services which in this case is not a problem for the scope of the application, but can lead to confusion and hard to maintain if application grows.

For the purpose of the challenge a default in-memory cache provided by spring was used instead of a dedicated cache storage like Redis.

Caching adds complexity in managing cache invalidation and consistency

## Getting Started

### Prerequisites

*   Java 21

### Running the Application

On windows

    `gradlew.bat build`
    `gradlew.bat bootrun`

On linux

    `chmod +x gradlew` (permission denied error)
    `./gradlew build`
    `./gradlew bootrun`

The API will be available at `http://localhost:8080/country-votes/api/v1`.

## API Endpoints

| Method | Path                    | Description                          |
| :--- |:------------------------|:-------------------------------------|
| `POST` | `/votes`                | Create a new user and vote           |
| `GET` | `/countries/most-voted` | Retrieve the 10 most voted countries |
| `DELETE`| `/countries`            | Retrieve simple country list         |

## Swagger

Swagger docs and API testing at `http://localhost:8080/country-votes/swagger-ui/index.html`

## Technologies Used

*   **Spring Boot 4**
*   **Spring Framework 7**
*   **Spring Data JPA**
*   **H2 (In-memory database)**
*   **Swagger**
*   **Gradle**
