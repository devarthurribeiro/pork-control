package modelo;

import javafx.scene.control.Alert;

public class AlertBox extends Alert {

    public AlertBox(String message, String title, Alert alert) {
        super(alert.getAlertType());
        this.setTitle(title);
        this.setHeaderText(message);
        this.showAndWait();
    }

}