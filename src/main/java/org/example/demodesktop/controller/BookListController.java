package org.example.demodesktop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.example.demodesktop.config.SessionManager;
import org.example.demodesktop.model.Book;
import org.example.demodesktop.view.CreateBookPage;
import org.example.demodesktop.view.LoginPage;
import org.example.demodesktop.view.BookDetailPage;
import org.example.demodesktop.repository.BookRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class BookListController {

    private static final Logger log = Logger.getLogger(BookListController.class.getName());
    private final BookRepository bookRepository = new BookRepository();

    @FXML
    private TableView<Book> bookTableView;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> nameColumn;
    @FXML
    private TableColumn<Book, String> instansiColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, LocalDateTime> borrowDateColumn;
    @FXML
    private TableColumn<Book, LocalDateTime> returnDateColumn;
    @FXML
    private Button createBookButton;
    @FXML
    private Button logoutButton;

    @FXML
    public void initialize() throws IOException {
        bookTableView.setItems(getBooks());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
        instansiColumn.setCellValueFactory(new PropertyValueFactory<>("instansi"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("judulBuku"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("tanggalPeminjaman"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("tanggalPengembalian"));

        bookTableView.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    Book clickedBook = row.getItem();
                    log.info("Book clicked: " + clickedBook);
                    showBookDetailPage((Stage) bookTableView.getScene().getWindow(), clickedBook);
                }
            });
            return row;
        });
    }

    @FXML
    private void handleCreateBook() throws IOException {
        new CreateBookPage().start((Stage) createBookButton.getScene().getWindow());
    }

    @FXML
    private void handleLogout() throws IOException {
        SessionManager.clearSession();
        new LoginPage().start((Stage) logoutButton.getScene().getWindow());
    }

    private ObservableList<Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        return FXCollections.observableList(books);
    }

    private void showBookDetailPage(Stage primaryStage, Book book) {
        BookDetailPage detailPage = new BookDetailPage(book);
        try {
            detailPage.start(primaryStage);
        } catch (Exception e) {
            log.warning("Error while showing book detail page");
        }
    }
}
