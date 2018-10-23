package controle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import modelo.Baia;

/**
 * FXML Controller class
 *
 * @author arthur
 */
public class FormBaiaController implements Initializable {

    private Baia baia;
    @FXML
    private AnchorPane window;
    @FXML
    private TextField tamanho;
    @FXML
    private CheckBox limpo;
    @FXML
    private void onCancel() {
        window.getScene().getWindow().hide();
    }
    @FXML
    private void onSaveBaia() {
        String tam = tamanho.getText();
        boolean lim = limpo.isSelected();
        if (baia == null) {
            baia = new Baia(tam, lim);
            baia.save();
            tamanho.setText("");
            limpo.setSelected(false);
        } else {
            baia.setTamanho(tam);
            baia.setLimpo(lim);
            baia.save();
            window.getScene().getWindow().hide();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void setBaia(Baia b) { 
        this.baia = b;
        tamanho.setText(b.getTamanho());
        limpo.setSelected(b.getLimpo());
    }
}
