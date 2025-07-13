# Library System API Documentation

This document provides a detailed overview of the Library System API endpoints.

**Authentication:**

*   **Visitor:** No authentication required.
*   **User:** Requires `ROLE_USER`.
*   **Admin:** Requires `ROLE_ADMIN`.

Two User Accounts are registered automatically at the start of the application:

**Admin account** 
* username: admin
* password: admin

**User account**
* username: user1
* password: user1

---

## Users API

| Method | Endpoint            | Description                     | Access  |
| :--- | :------------------ | :------------------------------ | :------ |
| POST   | `/register`         | Register a new user             | Visitor / Admin to register another admin |
| POST   | `/login`            | Login as a user                 | Visitor |
| GET    | `/users`            | Get all users (paginated)       | Admin   |
| GET    | `/users/{id}`       | Get a specific user by ID       | Admin   |
| PUT    | `/users/{id}`       | Update a user's details         | Admin   |
| DELETE | `/deleteUser/{username}` | Delete a user by username       | Admin   |

**GET /login**

Returns users JWT Token. Required for requests that need User/Admin authorization.

**POST /register**

```json
{
    "username": "testuser",
    "password": "password",
    "role": "ROLE_USER"
}
```

**POST /login**

```json
{
    "username": "testuser",
    "password": "password"
}
```

**PUT /users/{id}**

```json
{
    "username": "newusername",
    "password": "newpassword",
    "role": "ROLE_USER"
}
```

---

## Authors API

Base Path: `/authors`

| Method | Endpoint      | Description                     | Access  |
| :--- | :------------ | :------------------------------ | :------ |
| GET    | `/`           | Get all authors (paginated)     | Visitor |
| GET    | `/{id}`       | Get a specific author by ID     | Visitor |
| POST   | `/`           | Add a new author                | Admin   |
| PUT    | `/{id}`       | Update an existing author       | Admin   |
| DELETE | `/{id}`       | Delete an author                | Admin   |

**POST /authors**

```json
{
    "firstName": "Max",
    "lastName": "Mustermann",
    "birthDate": "1960-01-01",
    "biography": "A famous author.",
    "pictureURL": "https://example.com/picture.jpg"
}
```

**PUT /authors/{id}**

```json
{
    "firstName": "Maximilian",
    "lastName": "Mustermann",
    "birthDate": "1960-01-01",
    "biography": "A very famous author.",
    "pictureURL": "https://example.com/picture.jpg"
}
```

---

## Books API

Base Path: `/books`

| Method | Endpoint      | Description                     | Access  |
| :--- | :------------ | :------------------------------ | :------ |
| GET    | `/`           | Search for books (paginated)    | Visitor |
| GET    | `/{id}`       | Get a specific book by ID       | Visitor |
| POST   | `/`           | Add a new book                  | Admin   |
| PUT    | `/{id}`       | Update an existing book         | Admin   |
| DELETE | `/{id}`       | Delete a book                   | Admin   |

**POST /books**

```json
{
    "name": "The Lord of the Rings",
    "isbn": "978-3-16-148410-0",
    "publisher": "Klett-Cotta",
    "genres": [
        {
            "id": 1
        }
    ],
    "authors": [
        {
            "id": 1
        }
    ],
    "availableCopies": 10,
    "totalCopies": 10
}
```

**PUT /books/{id}**

```json
{
    "name": "The Hobbit",
    "isbn": "978-3-16-148410-0",
    "publisher": "Klett-Cotta",
    "genres": [
        {
            "id": 1
        }
    ],
    "authors": [
        {
            "id": 1
        }
    ],
    "availableCopies": 5,
    "totalCopies": 10
}
```

---

## Genres API

Base Path: `/genres`

| Method | Endpoint      | Description                     | Access  |
| :--- | :------------ | :------------------------------ | :------ |
| GET    | `/`           | Get all genres (paginated)      | Visitor |
| GET    | `/{id}`       | Get a specific genre by ID      | Visitor |
| POST   | `/`           | Add a new genre                 | Admin   |
| PUT    | `/{id}`       | Update an existing genre        | Admin   |
| DELETE | `/{id}`       | Delete a genre                  | Admin   |

**POST /genres**

```json
{
    "name": "Fantasy",
    "description": "Fantasy is a genre of speculative fiction set in a fictional universe, often inspired by real world myth and folklore."
}
```

**PUT /genres/{id}**

```json
{
    "name": "Science Fiction",
    "description": "Science fiction is a genre of speculative fiction that typically deals with imaginative and futuristic concepts such as advanced science and technology, space exploration, time travel, parallel universes, and extraterrestrial life."
}
```

---

## Loans API

| Method | Endpoint         | Description                     | Access  |
| :--- | :--------------- | :------------------------------ | :------ |
| POST   | `/loan/{bookId}` | Loan a book                     | User    |
| POST   | `/return/{loanId}`| Return a book                   | User    |


