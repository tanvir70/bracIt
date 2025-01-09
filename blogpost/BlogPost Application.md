# BlogPost Application

This is a demo project for a BlogPost application built with Spring Boot, Maven, and PostgreSQL.

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [License](#license)

## Requirements

- Java 23
- Maven
- PostgreSQL

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/blogpost.git
    cd blogpost
    ```

2. Configure the PostgreSQL database:
    - Create a database named `bps`.
    - Update the database credentials in `src/main/resources/application.properties` if necessary.

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Usage

The application will be available at `http://localhost:8080`.

## API Endpoints

### Create a Blog Post

- **URL:** `/api/blogposts`
- **Method:** `POST`
- **Consumes:** `application/json`
- **Produces:** `application/json`
- **Request Body:**
    ```json
    {
        "title": "Sample Title",
        "content": "Sample Content",
        "author": "Author Name"
    }
    ```
- **Response:**
    ```json
    {
        "id": 1,
        "title": "Sample Title",
        "content": "Sample Content",
        "author": "Author Name"
    }
    ```

### Get All Blog Posts

- **URL:** `/api/blogposts`
- **Method:** `GET`
- **Produces:** `application/json`
- **Response:**
    ```json
    [
        {
            "id": 1,
            "title": "Sample Title",
            "content": "Sample Content",
            "author": "Author Name"
        }
    ]
    ```

### Get Blog Post by ID

- **URL:** `/api/blogposts/{id}`
- **Method:** `GET`
- **Produces:** `application/json`
- **Response:**
    ```json
    {
        "id": 1,
        "title": "Sample Title",
        "content": "Sample Content",
        "author": "Author Name"
    }
    ```

### Update Blog Post

- **URL:** `/api/blogposts/{id}`
- **Method:** `PUT`
- **Consumes:** `application/json`
- **Produces:** `application/json`
- **Request Body:**
    ```json
    {
        "title": "Updated Title",
        "content": "Updated Content",
        "author": "Updated Author"
    }
    ```
- **Response:**
    ```json
    {
        "id": 1,
        "title": "Updated Title",
        "content": "Updated Content",
        "author": "Updated Author"
    }
    ```

### Delete Blog Post

- **URL:** `/api/blogposts/{id}`
- **Method:** `DELETE`
- **Produces:** `application/json`
- **Response:**
    ```json
    {
        "id": 1,
        "title": "Sample Title",
        "content": "Sample Content",
        "author": "Author Name"
    }
    ```

## License

This project is licensed under the MIT License.
