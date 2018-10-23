    package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modelo.Baia;

/**
 *
 * @author arthur
 */
public class PrincipalController implements Initializable {
    Stage mainStage = new Stage();
    @FXML
    private Label label;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox vbox;
    @FXML
    private void mostrarFormPorco(ActionEvent event) throws IOException {
        openFXML("/visao/FormPorco.fxml", "Adicionar Porco");
    }
    @FXML
    private void mostrarFormBaia(ActionEvent event) throws IOException {
        openFXML("/visao/FormBaia.fxml", "Adicionar Baia");
    }
    @FXML
    private void mostrarListaBaia(ActionEvent event) throws IOException {
        openFXML("/visao/ListaBaia.fxml", "Listar Baias");
    }
    @FXML
    private void mostrarListaPorco(ActionEvent event) throws IOException {
        openFXML("/visao/ListaPorco.fxml", "Listar Porcos");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    public void openFXML(String formName, String title) throws IOException {
        Parent form = FXMLLoader.load(getClass().getResource(formName));
        Stage stage = new Stage();
        stage.initOwner(mainStage);
        Scene scene = new Scene(form);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }
    
}
