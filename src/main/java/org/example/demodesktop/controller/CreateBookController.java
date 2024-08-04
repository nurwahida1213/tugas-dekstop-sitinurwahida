package org.example.demodesktop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.demodesktop.model.Book;
import org.example.demodesktop.repository.BookRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Logger;

public class CreateBookController {

    private static final Logger log = Logger.getLogger(CreateBookController.class.getName());
    private final BookRepository bookRepository = new BookRepository();

    @FXML
    private TextField nameField;
    @FXML
    private TextField instansiField;
    @FXML
    private TextField titleField;
    @FXML
    private DatePicker borrowDateField;
    @FXML
    private DatePicker returnDateField;

    @FXML
    private void handleSave() throws IOException {
        String name = nameField.getText();
        String instansi = instansiField.getText();
        String title = titleField.getText();
        LocalDate borrowDate = borrowDateField.getValue();
        LocalDate returnDate = returnDateField.getValue();

        if (name == null || name.isEmpty() ||
                instansi == null || instansi.isEmpty() ||
                title == null || title.isEmpty() ||
                borrowDate == null || returnDate == null) {
            showAlert(Alert.AlertType.WARNING, "Form Error", "Please fill all fields");
            return;
        }

        Book book = new Book();
        book.setNama(name);
        book.setInstansi(instansi);
        book.setJudulBuku(title);
        book.setTanggalPeminjaman(borrowDate.atStartOfDay());
        book.setTanggalPengembalian(returnDate.atStartOfDay());

        bookRepository.save(book);
        log.info("Book saved: " + book);

        closeWindow();
    }

    @FXML
    private void handleCancel() throws IOException {
        closeWindow();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
