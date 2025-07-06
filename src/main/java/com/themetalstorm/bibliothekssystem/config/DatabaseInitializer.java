package com.themetalstorm.bibliothekssystem.config;

import com.themetalstorm.bibliothekssystem.dto.AuthorDTO;
import com.themetalstorm.bibliothekssystem.model.Author;
import com.themetalstorm.bibliothekssystem.model.Book;
import com.themetalstorm.bibliothekssystem.repository.BookRepository;
import com.themetalstorm.bibliothekssystem.service.AuthorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Configuration
public class DatabaseInitializer {
    @Bean
    @Transactional
    CommandLineRunner initDatabase( AuthorService authorService, BookRepository bookRepository) {
        return args -> {
            bookRepository.deleteAll();
            authorService.deleteAll();
            
            bookRepository.saveAll(getSampleBooks());

        };
    }



    private List<Book> getSampleBooks() {
        // First create all authors
        AuthorDTO harperLee = new AuthorDTO("Harper", "Lee", LocalDate.of(1926, 4, 28),
                "Pulitzer Prize-winning author of To Kill a Mockingbird", null);

        AuthorDTO georgeOrwell = new AuthorDTO("George", "Orwell", LocalDate.of(1903, 6, 25),
                "English novelist famous for 1984 and Animal Farm", null);

        AuthorDTO fScottFitzgerald = new AuthorDTO("F. Scott", "Fitzgerald", LocalDate.of(1896, 9, 24),
                "American author of The Great Gatsby", null);

        AuthorDTO jrrTolkien = new AuthorDTO("J.R.R.", "Tolkien", LocalDate.of(1892, 1, 3),
                "Creator of Middle-earth and author of The Lord of the Rings", null);

        AuthorDTO janeAusten = new AuthorDTO("Jane", "Austen", LocalDate.of(1775, 12, 16),
                "English novelist known for Pride and Prejudice", null);

        AuthorDTO anneFrank = new AuthorDTO("Anne", "Frank", LocalDate.of(1929, 6, 12),
                "Diary writer documenting her life in hiding during WWII", null);

        AuthorDTO pauloCoelho = new AuthorDTO("Paulo", "Coelho", LocalDate.of(1947, 8, 24),
                "Brazilian author of The Alchemist", null);

        AuthorDTO aldousHuxley = new AuthorDTO("Aldous", "Huxley", LocalDate.of(1894, 7, 26),
                "English writer of Brave New World", null);

        AuthorDTO johnSteinbeck = new AuthorDTO("John", "Steinbeck", LocalDate.of(1902, 2, 27),
                "American author of The Grapes of Wrath", null);

        AuthorDTO markusZusak = new AuthorDTO("Markus", "Zusak", LocalDate.of(1975, 6, 23),
                "Australian author of The Book Thief", null);

        AuthorDTO khaledHosseini = new AuthorDTO("Khaled", "Hosseini", LocalDate.of(1965, 3, 4),
                "Afghan-American author of The Kite Runner", null);

        AuthorDTO suzanneCollins = new AuthorDTO("Suzanne", "Collins", LocalDate.of(1962, 8, 10),
                "Author of The Hunger Games trilogy", null);

        AuthorDTO johnGreen = new AuthorDTO("John", "Green", LocalDate.of(1977, 8, 24),
                "Young adult author of The Fault in Our Stars", null);

        AuthorDTO gillianFlynn = new AuthorDTO("Gillian", "Flynn", LocalDate.of(1971, 2, 24),
                "Author of the thriller Gone Girl", null);

        AuthorDTO paulaHawkins = new AuthorDTO("Paula", "Hawkins", LocalDate.of(1972, 8, 26),
                "Author of The Girl on the Train", null);

        AuthorDTO danBrown = new AuthorDTO("Dan", "Brown", LocalDate.of(1964, 6, 22),
                "Author of The Da Vinci Code", null);

        AuthorDTO stephenKing = new AuthorDTO("Stephen", "King", LocalDate.of(1947, 9, 21),
                "Master of horror fiction", null);

        AuthorDTO cormacMcCarthy = new AuthorDTO("Cormac", "McCarthy", LocalDate.of(1933, 7, 20),
                "American novelist known for The Road", null);

        AuthorDTO georgeMartin = new AuthorDTO("George R.R.", "Martin", LocalDate.of(1948, 9, 20),
                "Author of A Song of Ice and Fire series", null);

        AuthorDTO margaretAtwood = new AuthorDTO("Margaret", "Atwood", LocalDate.of(1939, 11, 18),
                "Canadian author of The Handmaid's Tale", null);

        AuthorDTO andyWeir = new AuthorDTO("Andy", "Weir", LocalDate.of(1972, 6, 16),
                "Author of The Martian and Project Hail Mary", null);

        AuthorDTO taraWestover = new AuthorDTO("Tara", "Westover", LocalDate.of(1986, 9, 27),
                "Author of the memoir Educated", null);

        AuthorDTO yuvalHarari = new AuthorDTO("Yuval Noah", "Harari", LocalDate.of(1976, 2, 24),
                "Historian and author of Sapiens", null);

        AuthorDTO alexMichaelides = new AuthorDTO("Alex", "Michaelides", LocalDate.of(1977, 9, 4),
                "Author of The Silent Patient", null);

        AuthorDTO deliaOwens = new AuthorDTO("Delia", "Owens", LocalDate.of(1949, 12, 4),
                "Author of Where the Crawdads Sing", null);

        AuthorDTO jamesClear = new AuthorDTO("James", "Clear", LocalDate.of(1986, 1, 22),
                "Author of Atomic Habits", null);

        AuthorDTO michelleObama = new AuthorDTO("Michelle", "Obama", LocalDate.of(1964, 1, 17),
                "Former First Lady and author of Becoming", null);

        AuthorDTO mattHaig = new AuthorDTO("Matt", "Haig", LocalDate.of(1975, 7, 3),
                "Author of The Midnight Library", null);

        AuthorDTO madelineMiller = new AuthorDTO("Madeline", "Miller", LocalDate.of(1978, 7, 24),
                "Author of Circe and The Song of Achilles", null);

        AuthorDTO sallyRooney = new AuthorDTO("Sally", "Rooney", LocalDate.of(1991, 2, 20),
                "Author of Normal People", null);

        AuthorDTO taylorReid = new AuthorDTO("Taylor Jenkins", "Reid", LocalDate.of(1983, 12, 20),
                "Author of The Seven Husbands of Evelyn Hugo", null);

        AuthorDTO frankHerbert = new AuthorDTO("Frank", "Herbert", LocalDate.of(1920, 10, 8),
                "Author of Dune", null);

        AuthorDTO britBennett = new AuthorDTO("Brit", "Bennett", LocalDate.of(1989, 10, 17),
                "Author of The Vanishing Half", null);

        AuthorDTO kazuoIshiguro = new AuthorDTO("Kazuo", "Ishiguro", LocalDate.of(1954, 11, 8),
                "Nobel Prize-winning author of Klara and the Sun", null);

        AuthorDTO veSchwab = new AuthorDTO("V.E.", "Schwab", LocalDate.of(1987, 7, 7),
                "Author of The Invisible Life of Addie LaRue", null);

        AuthorDTO michelleZauner = new AuthorDTO("Michelle", "Zauner", LocalDate.of(1989, 3, 29),
                "Author of Crying in H Mart", null);

        AuthorDTO tjKlune = new AuthorDTO("TJ", "Klune", LocalDate.of(1982, 5, 20),
                "Author of The House in the Cerulean Sea", null);

        AuthorDTO minJinLee = new AuthorDTO("Min Jin", "Lee", LocalDate.of(1968, 11, 11),
                "Author of Pachinko", null);

        AuthorDTO anthonyDoerr = new AuthorDTO("Anthony", "Doerr", LocalDate.of(1973, 10, 27),
                "Pulitzer Prize-winning author of All the Light We Cannot See", null);

        AuthorDTO celesteNg = new AuthorDTO("Celeste", "Ng", LocalDate.of(1980, 7, 30),
                "Author of Little Fires Everywhere", null);

        AuthorDTO donnaTartt = new AuthorDTO("Donna", "Tartt", LocalDate.of(1963, 12, 23),
                "Pulitzer Prize-winning author of The Goldfinch", null);

        AuthorDTO richardOsman = new AuthorDTO("Richard", "Osman", LocalDate.of(1970, 11, 28),
                "Author of The Thursday Murder Club", null);

        AuthorDTO lucindaRiley = new AuthorDTO("Lucinda", "Riley", LocalDate.of(1965, 2, 16),
                "Author of The Midnight Rose", null);

        AuthorDTO amorTowles = new AuthorDTO("Amor", "Towles", LocalDate.of(1964, 10, 22),
                "Author of The Lincoln Highway", null);

        AuthorDTO rfKuang = new AuthorDTO("R.F.", "Kuang", LocalDate.of(1996, 5, 29),
                "Author of Babel", null);

        // Then create books with author sets
        return List.of(
                createBook("To Kill a Mockingbird", "978-0061120084", "HarperCollins", "Classic", harperLee, georgeOrwell),
                createBook("1984", "978-0451524935", "Signet Classics", "Dystopian", georgeOrwell),
                createBook("The Great Gatsby", "978-0743273565", "Scribner", "Classic", fScottFitzgerald),
                createBook("The Hobbit", "978-0547928227", "Houghton Mifflin", "Fantasy", jrrTolkien),
                createBook("Pride and Prejudice", "978-0486280615", "Dover Publications", "Romance", janeAusten),
                createBook("The Diary of a Young Girl", "978-0553577129", "Bantam", "Biography", anneFrank),
                createBook("Animal Farm", "978-0451526342", "Signet Classics", "Political Satire", georgeOrwell),
                createBook("The Alchemist", "978-0062315007", "HarperOne", "Fantasy", pauloCoelho),
                createBook("Brave New World", "978-0060850524", "Harper Perennial", "Dystopian", aldousHuxley),
                createBook("The Grapes of Wrath", "978-0143039433", "Penguin Classics", "Historical", johnSteinbeck),
                createBook("The Book Thief", "978-0375842207", "Knopf", "Historical", markusZusak),
                createBook("The Kite Runner", "978-1594631931", "Riverhead Books", "Historical Fiction", khaledHosseini),
                createBook("The Hunger Games", "978-0439023481", "Scholastic Press", "Dystopian", suzanneCollins),
                createBook("The Fault in Our Stars", "978-0525478812", "Dutton Books", "Young Adult", johnGreen),
                createBook("Gone Girl", "978-0307588371", "Crown Publishing", "Thriller", gillianFlynn),
                createBook("The Girl on the Train", "978-1594634024", "Riverhead Books", "Mystery", paulaHawkins),
                createBook("The Da Vinci Code", "978-0307474278", "Anchor", "Thriller", danBrown),
                createBook("The Shining", "978-0307743657", "Anchor", "Horror", stephenKing),
                createBook("The Road", "978-0307387899", "Vintage", "Post-Apocalyptic", cormacMcCarthy),
                createBook("A Game of Thrones", "978-0553103540", "Bantam", "Fantasy", georgeMartin),
                createBook("The Handmaid's Tale", "978-0385490818", "Anchor", "Dystopian", margaretAtwood),
                createBook("The Martian", "978-0553418026", "Broadway Books", "Science Fiction", andyWeir),
                createBook("Educated", "978-0399590504", "Random House", "Memoir", taraWestover),
                createBook("Sapiens", "978-0062316097", "Harper", "Anthropology", yuvalHarari),
                createBook("The Silent Patient", "978-1250301697", "Celadon Books", "Psychological Thriller", alexMichaelides),
                createBook("Where the Crawdads Sing", "978-0735219090", "G.P. Putnam's Sons", "Mystery", deliaOwens),
                createBook("Atomic Habits", "978-0735211292", "Avery", "Self-Help", jamesClear),
                createBook("Becoming", "978-1524763138", "Crown", "Memoir", michelleObama),
                createBook("The Midnight Library", "978-0525559474", "Viking", "Fantasy", mattHaig),
                createBook("Circe", "978-0316556347", "Little, Brown", "Mythology", madelineMiller),
                createBook("Normal People", "978-1984822185", "Hogarth", "Literary Fiction", sallyRooney),
                createBook("The Seven Husbands of Evelyn Hugo", "978-1501161933", "Washington Square Press", "Historical Fiction", taylorReid),
                createBook("Project Hail Mary", "978-0593135204", "Ballantine Books", "Science Fiction", andyWeir),
                createBook("Dune", "978-0441172719", "Ace", "Science Fiction", frankHerbert),
                createBook("The Vanishing Half", "978-0525536291", "Riverhead Books", "Literary Fiction", britBennett),
                createBook("Malibu Rising", "978-1524798659", "Ballantine Books", "Historical Fiction", taylorReid),
                createBook("Klara and the Sun", "978-0593318171", "Knopf", "Science Fiction", kazuoIshiguro),
                createBook("The Invisible Life of Addie LaRue", "978-0765387561", "Tor Books", "Fantasy", veSchwab),
                createBook("Crying in H Mart", "978-1984820365", "Knopf", "Memoir", michelleZauner),
                createBook("The House in the Cerulean Sea", "978-1250217288", "Tor Books", "Fantasy", tjKlune),
                createBook("Pachinko", "978-1455563937", "Grand Central Publishing", "Historical Fiction", minJinLee),
                createBook("The Song of Achilles", "978-0062060624", "Ecco", "Mythology", madelineMiller),
                createBook("All the Light We Cannot See", "978-1501173219", "Scribner", "Historical Fiction", anthonyDoerr),
                createBook("Little Fires Everywhere", "978-0735224292", "Penguin Press", "Literary Fiction", celesteNg),
                createBook("The Goldfinch", "978-0316055437", "Little, Brown", "Literary Fiction", donnaTartt),
                createBook("The Thursday Murder Club", "978-1984880983", "Penguin Books", "Mystery", richardOsman),
                createBook("Cloud Cuckoo Land", "978-1982168438", "Scribner", "Historical Fiction", anthonyDoerr),
                createBook("The Midnight Rose", "978-1471133433", "Atria Books", "Historical Romance", lucindaRiley),
                createBook("The Lincoln Highway", "978-0735222359", "Viking", "Literary Fiction", amorTowles),
                createBook("Babel", "978-0063021426", "Harper Voyager", "Fantasy", rfKuang)
        );
    }

    private Book createBook(String title, String isbn, String publisher, String genre, AuthorDTO... authors) {
        Book book = new Book(title, isbn, new HashSet<>(), publisher, genre);
        for (AuthorDTO author : authors) {
            book.addAuthor(new Author(author));
        }
        return book;
    }
}
