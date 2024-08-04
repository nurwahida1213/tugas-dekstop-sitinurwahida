package org.example.demodesktop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.demodesktop.view.LoginPage;

import java.io.IOException;
import java.util.logging.Logger;

public class WelcomeController {

    private static final Logger log = Logger.getLogger(WelcomeController.class.getName());

    @FXML
    private Button loginButton;

    @FXML
    private void nextPage() throws IOException {
        log.info("Login Button clicked");
        new LoginPage().start((Stage) loginButton.getScene().getWindow());
    }
}