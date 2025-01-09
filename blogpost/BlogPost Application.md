# BlogPost Application

This is a demo project for a BlogPost application built with Spring Boot, Maven, and PostgreSQL. It includes basic CRUD Operations and Junit testing with Mockito. 

## Table of Contents

- [Tech Stack](#Tech_Stack)
- [API Endpoints](#api-endpoints)

## Tech Stack
- Java 23
- Maven
- PostgreSQL

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
