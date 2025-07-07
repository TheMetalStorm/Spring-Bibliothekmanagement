package com.themetalstorm.bibliothekssystem.config;

import com.themetalstorm.bibliothekssystem.dto.AuthorDTO;
import com.themetalstorm.bibliothekssystem.dto.BookDTO;
import com.themetalstorm.bibliothekssystem.dto.GenreDTO;

import com.themetalstorm.bibliothekssystem.model.User;
import com.themetalstorm.bibliothekssystem.repository.BookRepository;
import com.themetalstorm.bibliothekssystem.repository.UserRepository;
import com.themetalstorm.bibliothekssystem.service.AuthorService;
import com.themetalstorm.bibliothekssystem.service.BookService;
import com.themetalstorm.bibliothekssystem.service.MyUserService;
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
    CommandLineRunner initDatabase(AuthorService authorService, BookRepository bookRepository, BookService bookService, MyUserService userService) {
        return args -> {
            bookRepository.deleteAll();
            authorService.deleteAll();

            getSampleBooks().forEach(bookService::addBook);
            userService.registerUser(new User("simon", "simon"));
        };
    }

    private List<BookDTO> getSampleBooks() {
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

        // Create genre DTOs
        GenreDTO classicGenre = new GenreDTO("Classic", "Works of enduring artistic quality and timeless appeal");
        GenreDTO dystopianGenre = new GenreDTO("Dystopian", "Fictional societies that are undesirable or frightening");
        GenreDTO fantasyGenre = new GenreDTO("Fantasy", "Imaginary worlds and magical elements");
        GenreDTO romanceGenre = new GenreDTO("Romance", "Stories focusing on love and relationships");
        GenreDTO biographyGenre = new GenreDTO("Biography", "Non-fictional accounts of people's lives");
        GenreDTO politicalSatireGenre = new GenreDTO("Political Satire", "Uses humor to critique politics");
        GenreDTO historicalGenre = new GenreDTO("Historical", "Set in the past with historical context");
        GenreDTO historicalFictionGenre = new GenreDTO("Historical Fiction", "Fictional stories set in historical periods");
        GenreDTO youngAdultGenre = new GenreDTO("Young Adult", "Books aimed at teenage readers");
        GenreDTO thrillerGenre = new GenreDTO("Thriller", "Fast-paced, suspenseful stories");
        GenreDTO mysteryGenre = new GenreDTO("Mystery", "Stories involving puzzles or crimes to solve");
        GenreDTO horrorGenre = new GenreDTO("Horror", "Intended to scare or unsettle readers");
        GenreDTO postApocalypticGenre = new GenreDTO("Post-Apocalyptic", "Set after a catastrophic event");
        GenreDTO scienceFictionGenre = new GenreDTO("Science Fiction", "Futuristic technology and scientific concepts");
        GenreDTO memoirGenre = new GenreDTO("Memoir", "Personal accounts of specific life experiences");
        GenreDTO anthropologyGenre = new GenreDTO("Anthropology", "Study of human societies and cultures");
        GenreDTO psychologicalThrillerGenre = new GenreDTO("Psychological Thriller", "Focuses on unstable emotional states");
        GenreDTO selfHelpGenre = new GenreDTO("Self-Help", "Books intended to help readers improve themselves");
        GenreDTO mythologyGenre = new GenreDTO("Mythology", "Based on or relating to myths");
        GenreDTO literaryFictionGenre = new GenreDTO("Literary Fiction", "Character-driven stories with artistic merit");
        GenreDTO historicalRomanceGenre = new GenreDTO("Historical Romance", "Romance novels set in historical periods");

        // Then create books with author sets and genres
        return List.of(
                createBook("To Kill a Mockingbird", "978-0061120084", "HarperCollins",
                        new HashSet<>(Set.of(classicGenre)),
                        new HashSet<>(Set.of(harperLee, georgeOrwell))),
                createBook("1984", "978-0451524935", "Signet Classics",
                        new HashSet<>(Set.of(dystopianGenre)),
                        new HashSet<>(Set.of(georgeOrwell))),
                createBook("The Great Gatsby", "978-0743273565", "Scribner",
                        new HashSet<>(Set.of(classicGenre)),
                        new HashSet<>(Set.of(fScottFitzgerald))),
                createBook("The Hobbit", "978-0547928227", "Houghton Mifflin",
                        new HashSet<>(Set.of(fantasyGenre)),
                        new HashSet<>(Set.of(jrrTolkien))),
                createBook("Pride and Prejudice", "978-0486280615", "Dover Publications",
                        new HashSet<>(Set.of(romanceGenre)),
                        new HashSet<>(Set.of(janeAusten))),
                createBook("The Diary of a Young Girl", "978-0553577129", "Bantam",
                        new HashSet<>(Set.of(biographyGenre)),
                        new HashSet<>(Set.of(anneFrank))),
                createBook("Animal Farm", "978-0451526342", "Signet Classics",
                        new HashSet<>(Set.of(politicalSatireGenre)),
                        new HashSet<>(Set.of(georgeOrwell))),
                createBook("The Alchemist", "978-0062315007", "HarperOne",
                        new HashSet<>(Set.of(fantasyGenre)),
                        new HashSet<>(Set.of(pauloCoelho))),
                createBook("Brave New World", "978-0060850524", "Harper Perennial",
                        new HashSet<>(Set.of(dystopianGenre)),
                        new HashSet<>(Set.of(aldousHuxley))),
                createBook("The Grapes of Wrath", "978-0143039433", "Penguin Classics",
                        new HashSet<>(Set.of(historicalGenre)),
                        new HashSet<>(Set.of(johnSteinbeck))),
                createBook("The Book Thief", "978-0375842207", "Knopf",
                        new HashSet<>(Set.of(historicalGenre)),
                        new HashSet<>(Set.of(markusZusak))),
                createBook("The Kite Runner", "978-1594631931", "Riverhead Books",
                        new HashSet<>(Set.of(historicalFictionGenre)),
                        new HashSet<>(Set.of(khaledHosseini))),
                createBook("The Hunger Games", "978-0439023481", "Scholastic Press",
                        new HashSet<>(Set.of(dystopianGenre)),
                        new HashSet<>(Set.of(suzanneCollins))),
                createBook("The Fault in Our Stars", "978-0525478812", "Dutton Books",
                        new HashSet<>(Set.of(youngAdultGenre)),
                        new HashSet<>(Set.of(johnGreen))),
                createBook("Gone Girl", "978-0307588371", "Crown Publishing",
                        new HashSet<>(Set.of(thrillerGenre)),
                        new HashSet<>(Set.of(gillianFlynn))),
                createBook("The Girl on the Train", "978-1594634024", "Riverhead Books",
                        new HashSet<>(Set.of(mysteryGenre)),
                        new HashSet<>(Set.of(paulaHawkins))),
                createBook("The Da Vinci Code", "978-0307474278", "Anchor",
                        new HashSet<>(Set.of(thrillerGenre)),
                        new HashSet<>(Set.of(danBrown))),
                createBook("The Shining", "978-0307743657", "Anchor",
                        new HashSet<>(Set.of(horrorGenre)),
                        new HashSet<>(Set.of(stephenKing))),
                createBook("The Road", "978-0307387899", "Vintage",
                        new HashSet<>(Set.of(postApocalypticGenre)),
                        new HashSet<>(Set.of(cormacMcCarthy))),
                createBook("A Game of Thrones", "978-0553103540", "Bantam",
                        new HashSet<>(Set.of(fantasyGenre)),
                        new HashSet<>(Set.of(georgeMartin))),
                createBook("The Handmaid's Tale", "978-0385490818", "Anchor",
                        new HashSet<>(Set.of(dystopianGenre)),
                        new HashSet<>(Set.of(margaretAtwood))),
                createBook("The Martian", "978-0553418026", "Broadway Books",
                        new HashSet<>(Set.of(scienceFictionGenre)),
                        new HashSet<>(Set.of(andyWeir))),
                createBook("Educated", "978-0399590504", "Random House",
                        new HashSet<>(Set.of(memoirGenre)),
                        new HashSet<>(Set.of(taraWestover))),
                createBook("Sapiens", "978-0062316097", "Harper",
                        new HashSet<>(Set.of(anthropologyGenre)),
                        new HashSet<>(Set.of(yuvalHarari))),
                createBook("The Silent Patient", "978-1250301697", "Celadon Books",
                        new HashSet<>(Set.of(psychologicalThrillerGenre)),
                        new HashSet<>(Set.of(alexMichaelides))),
                createBook("Where the Crawdads Sing", "978-0735219090", "G.P. Putnam's Sons",
                        new HashSet<>(Set.of(mysteryGenre)),
                        new HashSet<>(Set.of(deliaOwens))),
                createBook("Atomic Habits", "978-0735211292", "Avery",
                        new HashSet<>(Set.of(selfHelpGenre)),
                        new HashSet<>(Set.of(jamesClear))),
                createBook("Becoming", "978-1524763138", "Crown",
                        new HashSet<>(Set.of(memoirGenre)),
                        new HashSet<>(Set.of(michelleObama))),
                createBook("The Midnight Library", "978-0525559474", "Viking",
                        new HashSet<>(Set.of(fantasyGenre)),
                        new HashSet<>(Set.of(mattHaig))),
                createBook("Circe", "978-0316556347", "Little, Brown",
                        new HashSet<>(Set.of(mythologyGenre)),
                        new HashSet<>(Set.of(madelineMiller))),
                createBook("Normal People", "978-1984822185", "Hogarth",
                        new HashSet<>(Set.of(literaryFictionGenre)),
                        new HashSet<>(Set.of(sallyRooney))),
                createBook("The Seven Husbands of Evelyn Hugo", "978-1501161933", "Washington Square Press",
                        new HashSet<>(Set.of(historicalFictionGenre)),
                        new HashSet<>(Set.of(taylorReid))),
                createBook("Project Hail Mary", "978-0593135204", "Ballantine Books",
                        new HashSet<>(Set.of(scienceFictionGenre)),
                        new HashSet<>(Set.of(andyWeir))),
                createBook("Dune", "978-0441172719", "Ace",
                        new HashSet<>(Set.of(scienceFictionGenre)),
                        new HashSet<>(Set.of(frankHerbert))),
                createBook("The Vanishing Half", "978-0525536291", "Riverhead Books",
                        new HashSet<>(Set.of(literaryFictionGenre)),
                        new HashSet<>(Set.of(britBennett))),
                createBook("Malibu Rising", "978-1524798659", "Ballantine Books",
                        new HashSet<>(Set.of(historicalFictionGenre)),
                        new HashSet<>(Set.of(taylorReid))),
                createBook("Klara and the Sun", "978-0593318171", "Knopf",
                        new HashSet<>(Set.of(scienceFictionGenre)),
                        new HashSet<>(Set.of(kazuoIshiguro))),
                createBook("The Invisible Life of Addie LaRue", "978-0765387561", "Tor Books",
                        new HashSet<>(Set.of(fantasyGenre)),
                        new HashSet<>(Set.of(veSchwab))),
                createBook("Crying in H Mart", "978-1984820365", "Knopf",
                        new HashSet<>(Set.of(memoirGenre)),
                        new HashSet<>(Set.of(michelleZauner))),
                createBook("The House in the Cerulean Sea", "978-1250217288", "Tor Books",
                        new HashSet<>(Set.of(fantasyGenre)),
                        new HashSet<>(Set.of(tjKlune))),
                createBook("Pachinko", "978-1455563937", "Grand Central Publishing",
                        new HashSet<>(Set.of(historicalFictionGenre)),
                        new HashSet<>(Set.of(minJinLee))),
                createBook("The Song of Achilles", "978-0062060624", "Ecco",
                        new HashSet<>(Set.of(mythologyGenre)),
                        new HashSet<>(Set.of(madelineMiller))),
                createBook("All the Light We Cannot See", "978-1501173219", "Scribner",
                        new HashSet<>(Set.of(historicalFictionGenre)),
                        new HashSet<>(Set.of(anthonyDoerr))),
                createBook("Little Fires Everywhere", "978-0735224292", "Penguin Press",
                        new HashSet<>(Set.of(literaryFictionGenre)),
                        new HashSet<>(Set.of(celesteNg))),
                createBook("The Goldfinch", "978-0316055437", "Little, Brown",
                        new HashSet<>(Set.of(literaryFictionGenre)),
                        new HashSet<>(Set.of(donnaTartt))),
                createBook("The Thursday Murder Club", "978-1984880983", "Penguin Books",
                        new HashSet<>(Set.of(mysteryGenre)),
                        new HashSet<>(Set.of(richardOsman))),
                createBook("Cloud Cuckoo Land", "978-1982168438", "Scribner",
                        new HashSet<>(Set.of(historicalFictionGenre)),
                        new HashSet<>(Set.of(anthonyDoerr))),
                createBook("The Midnight Rose", "978-1471133433", "Atria Books",
                        new HashSet<>(Set.of(historicalRomanceGenre)),
                        new HashSet<>(Set.of(lucindaRiley))),
                createBook("The Lincoln Highway", "978-0735222359", "Viking",
                        new HashSet<>(Set.of(literaryFictionGenre)),
                        new HashSet<>(Set.of(amorTowles))),
                createBook("Babel", "978-0063021426", "Harper Voyager",
                        new HashSet<>(Set.of(fantasyGenre)),
                        new HashSet<>(Set.of(rfKuang)))
        );
    }

    private BookDTO createBook(String title, String isbn, String publisher,
                               HashSet<GenreDTO> genreDTOs, HashSet<AuthorDTO> authorDTOs) {

        return new BookDTO(title, isbn,publisher, genreDTOs,  authorDTOs, 1,1 );
    }
}