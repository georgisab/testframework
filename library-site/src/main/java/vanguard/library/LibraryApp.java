package vanguard.library;

import static spark.Spark.*;

import java.util.List;

public class LibraryApp {
    public static void main(String[] args) {
        LibraryService service = new LibraryService();

        get("/", (req, res) -> "<h1>Library</h1>" +
                "<ul>" +
                "<li><a href='/register'>Register</a></li>" +
                "<li><a href='/admin'>Admin</a></li>" +
                "<li><a href='/books'>Find Books</a></li>" +
                "</ul>");

        get("/register", (req, res) ->
                "<h2>Register</h2>" +
                "<form method='post'>" +
                "<input name='username' placeholder='username'/>" +
                "<button type='submit'>Register</button>" +
                "</form>");

        post("/register", (req, res) -> {
            service.registerUser(req.queryParams("username"));
            res.redirect("/");
            return null;
        });

        get("/admin", (req, res) -> {
            StringBuilder html = new StringBuilder("<h2>Add Book</h2>" +
                    "<form method='post' action='/admin/books'>" +
                    "Title: <input name='title'/> " +
                    "Author: <input name='author'/> " +
                    "<button type='submit'>Add</button></form>" +
                    "<h3>Books</h3><ul>");
            for (Book b : service.getAllBooks()) {
                html.append("<li>").append(b.getTitle()).append(" by ")
                        .append(b.getAuthor());
                if (b.getRentedBy() != null) {
                    html.append(" (Rented by ").append(b.getRentedBy()).append(")");
                }
                html.append("</li>");
            }
            html.append("</ul>");
            return html.toString();
        });

        post("/admin/books", (req, res) -> {
            String title = req.queryParams("title");
            String author = req.queryParams("author");
            if (title != null && author != null && !title.isBlank() && !author.isBlank()) {
                service.addBook(title, author);
            }
            res.redirect("/admin");
            return null;
        });

        get("/books", (req, res) -> {
            String query = req.queryParams("q");
            List<Book> books = service.searchBooks(query);
            StringBuilder html = new StringBuilder("<h2>Books</h2>" +
                    "<form method='get'>" +
                    "<input name='q' placeholder='search'/>" +
                    "<button type='submit'>Search</button>" +
                    "</form><ul>");
            for (Book b : books) {
                html.append("<li>").append(b.getTitle()).append(" by ")
                        .append(b.getAuthor());
                if (b.getRentedBy() == null) {
                    html.append(" <form style='display:inline' method='post' action='/books/")
                            .append(b.getId()).append("/rent'>" +
                                    "<input name='username' placeholder='username'/>" +
                                    "<button type='submit'>Rent</button></form>");
                } else {
                    html.append(" (Rented by ").append(b.getRentedBy()).append(")");
                }
                html.append("</li>");
            }
            html.append("</ul>");
            return html.toString();
        });

        post("/books/:id/rent", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            String user = req.queryParams("username");
            service.rentBook(id, user);
            res.redirect("/books");
            return null;
        });
    }
}
