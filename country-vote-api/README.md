# Country Vote API

REST API intended to serve the front application.

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

Swagger docs and testing at `http://localhost:8080/country-votes/swagger-ui/index.html`

## Technologies Used

*   **Spring Boot 4**
*   **Spring Framework 7**
*   **Spring Data JPA**
*   **H2 (In-memory database)**
*   **Swagger**
*   **Gradle**
