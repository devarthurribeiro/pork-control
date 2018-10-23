package controle;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Dictionary;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import modelo.AlertBox;
import modelo.Baia;

import modelo.Porco;

/**
 * FXML Controller class
 *
 * @author arthur
 */
public class FormPorcoController implements Initializable {

    private Porco porco;
    @FXML 
    private AnchorPane window;
    @FXML
    private TextField nome;
    @FXML
    private DatePicker nascimento;
    @FXML
    private DatePicker aquisicao;
    @FXML
    private ComboBox<Baia> select;
    @FXML
    private void onCancel() {
        window.getScene().getWindow().hide();
    }
    @FXML
    private void cadastrarPorco() {
        String nomeTxt = nome.getText();
        String nasc = nascimento.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String aqui = aquisicao.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Baia baia = select.getSelectionModel().getSelectedItem();
        if (porco == null) {
            porco = new Porco(nomeTxt, parseDate(nasc), parseDate(aqui), baia);
            porco.save();
            nome.setText("");
            nascimento.setValue(null);
            aquisicao.setValue(null);
            select.getSelectionModel().select(null);
            porco = null;
        } else {
            porco.setNome(nomeTxt);
            porco.setNascimento(parseDate(nasc));
            porco.setAquisicao(parseDate(aqui));
            porco.setBaia(baia);
            porco.save();
            window.getScene().getWindow().hide();
        }
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static LocalDate parseDateToLocalDate(Date date) {
        return LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Baia> baiaList = FXCollections.observableArrayList(Baia.all());
        select.getItems().addAll(baiaList);
        //new AlertBox("texto", "Titulo", new Alert(Alert.AlertType.WARNING));
    }
    
    public void setPorco(Porco p) {
        this.porco = p;
        nome.setText(p.getNome());
        nascimento.setValue(parseDateToLocalDate(p.getNascimento()));
        aquisicao.setValue(parseDateToLocalDate(p.getAquisicao()));
        select.getSelectionModel().select(p.getBaia());
    }
}
