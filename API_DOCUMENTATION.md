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
| GET    | `/users`            | Get all users.       | Admin   |
| GET    | `/users/{id}`       | Get a specific user by ID       | Admin   |
| PUT    | `/users/{id}`       | Update a user's details         | Admin   |
| DELETE | `/deleteUser/{username}` | Delete a user by username       | Admin   |

### GET /users
Returns a paginated and sorted list of all users.

**Query Parameters:**
*   `page` (optional, integer): The page number to retrieve.
*   `size` (optional, integer): The number of users per page.
*   `sortField` (optional, string, default: `username`): The field to sort by.
*   `sortDirection` (optional, string, default: `ASC`): The sort direction (`ASC` or `DESC`).

**Note:** Pagination is only enabled when both `page` and `size` parameters are provided.

### GET /users/{id}
Retrieves a specific user by their ID.

**Path Parameters:**
*   `id` (required, integer): The ID of the user to retrieve.

### DELETE /deleteUser/{username}
Deletes a user by their username.

**Path Parameters:**
*   `username` (required, string): The username of the user to delete.

### POST /register

**Body:**

```json
{
    "username": "testuser",
    "password": "password",
    "role": "ROLE_USER"
}
```

### POST /login
Returns users JWT Token. Required for requests that need User/Admin authorization.

**Body:**
```json
{
    "username": "testuser",
    "password": "password"
}
```

### PUT /users/{id}
**Path Parameters:**
*   `id` (required, integer): The ID of the user to update.

**Body:**
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
| GET    | `/`           | Get all authors.     | Visitor |
| GET    | `/{id}`       | Get a specific author by ID     | Visitor |
| POST   | `/`           | Add a new author                | Admin   |
| PUT    | `/{id}`       | Update an existing author       | Admin   |
| DELETE | `/{id}`       | Delete an author                | Admin   |

### GET /authors
Returns a paginated and sorted list of all authors.

**Query Parameters:**
*   `page` (optional, integer): The page number to retrieve.
*   `size` (optional, integer): The number of authors per page.
*   `sortField` (optional, string, default: `lastName`): The field to sort by.
*   `sortDirection` (optional, string, default: `ASC`): The sort direction (`ASC` or `DESC`).

**Note:** Pagination is only enabled when both `page` and `size` parameters are provided.

### GET /authors/{id}
Retrieves a specific author by their ID.

**Path Parameters:**
*   `id` (required, integer): The ID of the author to retrieve.

### POST /authors
**Body:**
```json
{
    "firstName": "Max",
    "lastName": "Mustermann",
    "birthDate": "1960-01-01",
    "biography": "A famous author.",
    "pictureURL": "https://example.com/picture.jpg"
}
```

### PUT /authors/{id}
**Path Parameters:**
*   `id` (required, integer): The ID of the author to update.
**Body:**
```json
{
    "firstName": "Maximilian",
    "lastName": "Mustermann",
    "birthDate": "1960-01-01",
    "biography": "A very famous author.",
    "pictureURL": "https://example.com/picture.jpg"
}
```

### DELETE /authors/{id}
**Path Parameters:**
*   `id` (required, integer): The ID of the author to delete.

---

## Books API

Base Path: `/books`

| Method | Endpoint      | Description                     | Access  |
| :--- | :------------ | :------------------------------ | :------ |
| GET    | `/`           | Search for books.    | Visitor |
| GET    | `/{id}`       | Get a specific book by ID       | Visitor |
| POST   | `/`           | Add a new book                  | Admin   |
| PUT    | `/{id}`       | Update an existing book         | Admin   |
| DELETE | `/{id}`       | Delete a book                   | Admin   |

### GET /books
Returns a paginated and sorted list of books based on search criteria.

**Query Parameters:**
*   `name` (optional, string): Filter by book name (case-insensitive search).
*   `genreId` (optional, integer): Filter by genre ID.
*   `authorId` (optional, integer): Filter by author ID.
*   `page` (optional, integer): The page number to retrieve.
*   `size` (optional, integer): The number of books per page.
*   `sortField` (optional, string, default: `name`): The field to sort by.
*   `sortDirection` (optional, string, default: `ASC`): The sort direction (`ASC` or `DESC`).

**Note:** Pagination is only enabled when both `page` and `size` parameters are provided.

### GET /books/{id}
Retrieves a specific book by its ID.

**Path Parameters:**
*   `id` (required, integer): The ID of the book to retrieve.

### POST /books
**Body:**
```json
{
    "name": "The Lord of the Rings",
    "isbn": "978-3-16-148410-0",
    "publisher": "Klett-Cotta",
    "genreIds": [1, 6],
    "authorIds": [1, 2],
    "availableCopies": 10,
    "totalCopies": 10
}
```

### PUT /books/{id}
**Path Parameters:**
*   `id` (required, integer): The ID of the book to update.
**Body:**
```json
{
    "name": "The Lord of the Rings",
    "isbn": "978-3-16-148410-0",
    "publisher": "Klett-Cotta",
    "genreIds": [1, 6],
    "authorIds": [1, 2],
    "availableCopies": 10,
    "totalCopies": 10
}
```

### DELETE /books/{id}
**Path Parameters:**
*   `id` (required, integer): The ID of the book to delete.

---

## Genres API

Base Path: `/genres`

| Method | Endpoint      | Description                     | Access  |
| :--- | :------------ | :------------------------------ | :------ |
| GET    | `/`           | Get all genres.      | Visitor |
| GET    | `/{id}`       | Get a specific genre by ID      | Visitor |
| POST   | `/`           | Add a new genre                 | Admin   |
| PUT    | `/{id}`       | Update an existing genre        | Admin   |
| DELETE | `/{id}`       | Delete a genre                  | Admin   |

### GET /genres
Returns a paginated and sorted list of all genres.

**Query Parameters:**
*   `page` (optional, integer): The page number to retrieve.
*   `size` (optional, integer): The number of genres per page.
*   `sortField` (optional, string, default: `name`): The field to sort by.
*   `sortDirection` (optional, string, default: `ASC`): The sort direction (`ASC` or `DESC`).

**Note:** Pagination is only enabled when both `page` and `size` parameters are provided.

### GET /genres/{id}
Retrieves a specific genre by its ID.

**Path Parameters:**
*   `id` (required, integer): The ID of the genre to retrieve.

### POST /genres
**Body:**
```json
{
    "name": "Fantasy",
    "description": "Fantasy is a genre of speculative fiction set in a fictional universe, often inspired by real world myth and folklore."
}
```

### PUT /genres/{id}
**Path Parameters:**
*   `id` (required, integer): The ID of the genre to update.
**Body:**
```json
{
    "name": "Science Fiction",
    "description": "Science fiction is a genre of speculative fiction that typically deals with imaginative and futuristic concepts such as advanced science and technology, space exploration, time travel, parallel universes, and extraterrestrial life."
}
```

### DELETE /genres/{id}
**Path Parameters:**
*   `id` (required, integer): The ID of the genre to delete.

---

## Loans API

| Method | Endpoint         | Description                     | Access  |
| :--- | :--------------- | :------------------------------ | :------ |
| GET    | `/loans`         | Get all loans for the user      | User    |
| GET    | `/admin/loans`   | Get all loans                   | Admin   |
| POST   | `/loans/{bookId}` | Loan a book                     | User    |
| POST   | `/return/{loanId}`| Return a book                   | User    |
| POST   | `/admin/loans`   | Add a new loan                  | Admin   |
| POST   | `/admin/loans/raw`| Add a new loan (bypassing checks) | Admin   |
| PUT    | `/admin/loans/{loanId}`| Update an existing loan         | Admin   |
| POST   | `/admin/return/{loanId}`| Return a book (as admin)        | Admin   |
| DELETE | `/admin/loans/{loanId}`| Delete a loan                   | Admin   |

**Note:** LoanStatus can be `LOANED` or `RETURNED`

### GET /loans
Returns a paginated and sorted list of all loans for the authenticated user.

**Query Parameters:**
*   `page` (optional, integer): The page number to retrieve.
*   `size` (optional, integer): The number of loans per page.
*   `sortField` (optional, string, default: `id`): The field to sort by.
*   `sortDirection` (optional, string, default: `ASC`): The sort direction (`ASC` or `DESC`).

**Note:** Pagination is only enabled when both `page` and `size` parameters are provided.

### GET /admin/loans
Returns a paginated and sorted list of all loans.

**Query Parameters:**
*   `loanStatus` (optional, string): Filter by loan status (`ACTIVE`, `RETURNED`).
*   `userId` (optional, integer): Filter by user ID.
*   `bookId` (optional, integer): Filter by book ID.
*   `page` (optional, integer): The page number to retrieve.
*   `size` (optional, integer): The number of loans per page.
*   `sortField` (optional, string, default: `id`): The field to sort by.
*   `sortDirection` (optional, string, default: `ASC`): The sort direction (`ASC` or `DESC`).

**Note:** Pagination is only enabled when both `page` and `size` parameters are provided.

### POST /loan/{bookId}
Loans a book to the authenticated user.

**Path Parameters:**
*   `bookId` (required, integer): The ID of the book to loan.

### POST /return/{loanId}
Returns a loaned book.

**Path Parameters:**
*   `loanId` (required, integer): The ID of the loan to return.


### POST /admin/loans

**Query Parameters:**
*   `userId` (required, integer): The ID of the user.
*   `bookId` (required, integer): The ID of the book to loan.

### POST /admin/loans/raw
**Body:**
```json
{
    "userId": 1,
    "bookId": 1,
    "returned": "YYYY-MM-DDTHH:MM:SS",
    "loanStatus": "RETURNED"
}
```

### PUT /admin/loans/{loanId}
**Path Parameters:**
*   `loanId` (required, integer): The ID of the loan to update.
**Body:**
```json
{
    "userId": 1,
    "bookId": 1,
    "returned": null,
    "loanStatus": "LOANED"
}
```

### POST /admin/return/{loanId}
**Path Parameters:**
*   `loanId` (required, integer): The ID of the loan to return.

### DELETE /admin/loans/{loanId}
**Path Parameters:**
*   `loanId` (required, integer): The ID of the loan to delete.


