package org.example.demodesktop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.demodesktop.model.Book;

public class BookDetailController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField instansiField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField borrowDateField;
    @FXML
    private TextField returnDateField;

    private Book book;

    @FXML
    public void initialize() {
        // This method is called after the FXML file has been loaded
    }

    public void setBook(Book book) {
        this.book = book;
        populateFields();
    }

    private void populateFields() {
        if (book != null) {
            idField.setText(String.valueOf(book.getId()));
            nameField.setText(book.getNama());
            instansiField.setText(book.getInstansi());
            titleField.setText(book.getJudulBuku());
            borrowDateField.setText(book.getTanggalPeminjaman().toString());
            returnDateField.setText(book.getTanggalPengembalian().toString());
        }
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
}
