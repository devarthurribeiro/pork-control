/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modelo.Baia;
import util.Report;

/**
 * FXML Controller class
 *
 * @author arthur
 */
public class ListaBaiaController implements Initializable {
    private final Report report = new Report();

    @FXML
    private TableView<Baia> baias;

    @FXML
    private void onDeleteBaia() {
        Baia baia = baias.getSelectionModel().getSelectedItem();
        baia.delete();
        allBaias();
    }

    @FXML
    private void onEditBaia() throws IOException {
        Baia baia = baias.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/FormBaia.fxml"));
        Parent parent = loader.load();
        FormBaiaController controller = loader.getController();
        controller.setBaia(baia);
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Editar Baia");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                allBaias();
            }
        });
        stage.show();
    }
    @FXML
    private void onGenerateReport() throws IOException, DocumentException {
       report.setTitle("Relatório de baias.");
        PdfPTable newTable = report.newTable(3);
        newTable.addCell("Codigo");
        newTable.addCell("Tamanho");
        newTable.addCell("Limpo");
        for (Baia b : Baia.all()) {
            newTable.addCell(b.getCodigo() + "");
            newTable.addCell(b.getTamanho());
            newTable.addCell(b.getLimpo() ? "SIM" : "NÃO");
        }
        report.addTable(newTable);
        report.generate();
        report.open();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allBaias();
    }

    private void allBaias() {
        ObservableList<Baia> baiaList = FXCollections.observableArrayList(Baia.all());
        baias.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("codigo"));
        baias.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        baias.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("limpo"));
        baias.setItems(baiaList);
    }
}
