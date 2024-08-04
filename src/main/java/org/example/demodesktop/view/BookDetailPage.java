package org.example.demodesktop.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demodesktop.App;
import org.example.demodesktop.model.Book;
import org.example.demodesktop.controller.BookDetailController;

import java.io.IOException;

public class BookDetailPage {

    private final Book book;

    public BookDetailPage(Book book) {
        this.book = book;
    }

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("book-detail-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300); // Ukuran bisa disesuaikan sesuai kebutuhan

        BookDetailController controller = fxmlLoader.getController();
        controller.setBook(book);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Details");
        primaryStage.show();
    }
}
