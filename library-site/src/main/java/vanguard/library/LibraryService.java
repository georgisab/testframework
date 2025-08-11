package vanguard.library;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LibraryService {
    private final Map<Integer, Book> books = new LinkedHashMap<>();
    private final Set<String> users = new HashSet<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    public void registerUser(String username) {
        if (username != null && !username.isBlank()) {
            users.add(username.trim());
        }
    }

    public Book addBook(String title, String author) {
        int id = nextId.getAndIncrement();
        Book book = new Book(id, title, author);
        books.put(id, book);
        return book;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<Book> searchBooks(String query) {
        if (query == null || query.isBlank()) {
            return getAllBooks();
        }
        String q = query.toLowerCase();
        return books.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(q) ||
                             b.getAuthor().toLowerCase().contains(q))
                .collect(Collectors.toList());
    }

    public boolean rentBook(int id, String username) {
        if (!users.contains(username)) {
            return false;
        }
        Book book = books.get(id);
        if (book == null || book.getRentedBy() != null) {
            return false;
        }
        book.setRentedBy(username);
        return true;
    }
}
