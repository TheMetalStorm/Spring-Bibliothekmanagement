package dto;

import com.themetalstorm.bibliothekssystem.model.Book;

public record BookDTO(String name, String isbn, String author, String publisher, String genre) {

    public BookDTO (Book book) {
        this(book.getName(), book.getIsbn(),  book.getAuthor(), book.getPublisher(), book.getGenre());
    }
}
