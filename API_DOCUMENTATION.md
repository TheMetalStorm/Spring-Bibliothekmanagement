
# Bibliothekssystem API Documentation

This document provides a detailed overview of the Bibliothekssystem API endpoints.

**Authentication:**

*   **Visitor:** No authentication required.
*   **User:** Requires `ROLE_USER`.
*   **Admin:** Requires `ROLE_ADMIN`.

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

---

## Loans API

| Method | Endpoint         | Description                     | Access  |
| :--- | :--------------- | :------------------------------ | :------ |
| POST   | `/loan/{bookId}` | Loan a book                     | User    |
| POST   | `/return/{loanId}`| Return a book                   | User    |

---

## Users API

| Method | Endpoint            | Description                     | Access  |
| :--- | :------------------ | :------------------------------ | :------ |
| POST   | `/register`         | Register a new user             | Visitor |
| POST   | `/login`            | Login as a user                 | Visitor |
| GET    | `/users`            | Get all users (paginated)       | Admin   |
| GET    | `/users/{id}`       | Get a specific user by ID       | Admin   |
| PUT    | `/users/{id}`       | Update a user's details         | Admin   |
| DELETE | `/deleteUser/{username}` | Delete a user by username       | Admin   |

