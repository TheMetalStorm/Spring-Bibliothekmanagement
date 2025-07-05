package com.themetalstorm.bibliothekssystem.config;

import com.themetalstorm.bibliothekssystem.model.Book;
import com.themetalstorm.bibliothekssystem.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
public class DatabaseInitializer {
    @Bean
    @Transactional
    CommandLineRunner initDatabase(BookRepository bookRepository) {
        return args -> {
            // Clear existing data
            bookRepository.deleteAll();

            // Insert 50 books
            bookRepository.saveAll(getSampleBooks());

            System.out.println("Initialized database with 50 books");
        };
    }

    private List<Book> getSampleBooks() {
        return List.of(
                new Book("To Kill a Mockingbird", "978-0061120084", "Harper Lee", "HarperCollins", "Classic"),
                new Book("1984", "978-0451524935", "George Orwell", "Signet Classics", "Dystopian"),
                new Book("The Great Gatsby", "978-0743273565", "F. Scott Fitzgerald", "Scribner", "Classic"),
                new Book("The Hobbit", "978-0547928227", "J.R.R. Tolkien", "Houghton Mifflin", "Fantasy"),
                new Book("Pride and Prejudice", "978-0486280615", "Jane Austen", "Dover Publications", "Romance"),
                new Book("The Diary of a Young Girl", "978-0553577129", "Anne Frank", "Bantam", "Biography"),
                new Book("Animal Farm", "978-0451526342", "George Orwell", "Signet Classics", "Political Satire"),
                new Book("The Alchemist", "978-0062315007", "Paulo Coelho", "HarperOne", "Fantasy"),
                new Book("Brave New World", "978-0060850524", "Aldous Huxley", "Harper Perennial", "Dystopian"),
                new Book("The Grapes of Wrath", "978-0143039433", "John Steinbeck", "Penguin Classics", "Historical"),
                new Book("The Book Thief", "978-0375842207", "Markus Zusak", "Knopf", "Historical"),
                new Book("The Kite Runner", "978-1594631931", "Khaled Hosseini", "Riverhead Books", "Historical Fiction"),
                new Book("The Hunger Games", "978-0439023481", "Suzanne Collins", "Scholastic Press", "Dystopian"),
                new Book("The Fault in Our Stars", "978-0525478812", "John Green", "Dutton Books", "Young Adult"),
                new Book("Gone Girl", "978-0307588371", "Gillian Flynn", "Crown Publishing", "Thriller"),
                new Book("The Girl on the Train", "978-1594634024", "Paula Hawkins", "Riverhead Books", "Mystery"),
                new Book("The Da Vinci Code", "978-0307474278", "Dan Brown", "Anchor", "Thriller"),
                new Book("The Shining", "978-0307743657", "Stephen King", "Anchor", "Horror"),
                new Book("The Road", "978-0307387899", "Cormac McCarthy", "Vintage", "Post-Apocalyptic"),
                new Book("A Game of Thrones", "978-0553103540", "George R.R. Martin", "Bantam", "Fantasy"),
                new Book("The Handmaid's Tale", "978-0385490818", "Margaret Atwood", "Anchor", "Dystopian"),
                new Book("The Martian", "978-0553418026", "Andy Weir", "Broadway Books", "Science Fiction"),
                new Book("Educated", "978-0399590504", "Tara Westover", "Random House", "Memoir"),
                new Book("Sapiens", "978-0062316097", "Yuval Noah Harari", "Harper", "Anthropology"),
                new Book("The Silent Patient", "978-1250301697", "Alex Michaelides", "Celadon Books", "Psychological Thriller"),
                new Book("Where the Crawdads Sing", "978-0735219090", "Delia Owens", "G.P. Putnam's Sons", "Mystery"),
                new Book("Atomic Habits", "978-0735211292", "James Clear", "Avery", "Self-Help"),
                new Book("Becoming", "978-1524763138", "Michelle Obama", "Crown", "Memoir"),
                new Book("The Midnight Library", "978-0525559474", "Matt Haig", "Viking", "Fantasy"),
                new Book("Circe", "978-0316556347", "Madeline Miller", "Little, Brown", "Mythology"),
                new Book("Normal People", "978-1984822185", "Sally Rooney", "Hogarth", "Literary Fiction"),
                new Book("The Seven Husbands of Evelyn Hugo", "978-1501161933", "Taylor Jenkins Reid", "Washington Square Press", "Historical Fiction"),
                new Book("Project Hail Mary", "978-0593135204", "Andy Weir", "Ballantine Books", "Science Fiction"),
                new Book("Dune", "978-0441172719", "Frank Herbert", "Ace", "Science Fiction"),
                new Book("The Vanishing Half", "978-0525536291", "Brit Bennett", "Riverhead Books", "Literary Fiction"),
                new Book("Malibu Rising", "978-1524798659", "Taylor Jenkins Reid", "Ballantine Books", "Historical Fiction"),
                new Book("Klara and the Sun", "978-0593318171", "Kazuo Ishiguro", "Knopf", "Science Fiction"),
                new Book("The Invisible Life of Addie LaRue", "978-0765387561", "V.E. Schwab", "Tor Books", "Fantasy"),
                new Book("Crying in H Mart", "978-1984820365", "Michelle Zauner", "Knopf", "Memoir"),
                new Book("The House in the Cerulean Sea", "978-1250217288", "TJ Klune", "Tor Books", "Fantasy"),
                new Book("Pachinko", "978-1455563937", "Min Jin Lee", "Grand Central Publishing", "Historical Fiction"),
                new Book("The Song of Achilles", "978-0062060624", "Madeline Miller", "Ecco", "Mythology"),
                new Book("All the Light We Cannot See", "978-1501173219", "Anthony Doerr", "Scribner", "Historical Fiction"),
                new Book("Little Fires Everywhere", "978-0735224292", "Celeste Ng", "Penguin Press", "Literary Fiction"),
                new Book("The Goldfinch", "978-0316055437", "Donna Tartt", "Little, Brown", "Literary Fiction"),
                new Book("The Thursday Murder Club", "978-1984880983", "Richard Osman", "Penguin Books", "Mystery"),
                new Book("Cloud Cuckoo Land", "978-1982168438", "Anthony Doerr", "Scribner", "Historical Fiction"),
                new Book("The Midnight Rose", "978-1471133433", "Lucinda Riley", "Atria Books", "Historical Romance"),
                new Book("The Lincoln Highway", "978-0735222359", "Amor Towles", "Viking", "Literary Fiction"),
                new Book("Babel", "978-0063021426", "R.F. Kuang", "Harper Voyager", "Fantasy")
        );
    }
}
