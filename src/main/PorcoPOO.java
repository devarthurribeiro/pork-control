package main;

import controle.PrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author arthur
 */
public class PorcoPOO extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Principal.fxml"));
        Parent root = loader.load();
        PrincipalController controller = loader.getController();
        controller.setMainStage(stage);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Peppa Ping System");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
