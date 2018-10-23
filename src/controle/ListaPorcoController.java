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
import modelo.Porco;
import util.Report;

/**
 * FXML Controller class
 *
 * @author arthur
 */
public class ListaPorcoController implements Initializable {

    public static final String DEST = "/home/arthur/pdflegal.pdf";
    private final Report report = new Report();
    
    @FXML
    private TableView<Porco> porcos;

    @FXML
    private void onDeletePorco() {
        Porco porco = porcos.getSelectionModel().getSelectedItem();
        porco.delete();
        allPorcos();
    }

    @FXML
    private void onEditPorco() throws IOException {
        Porco porco = porcos.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/FormPorco.fxml"));
        Parent parent = loader.load();
        FormPorcoController controller = loader.getController();
        controller.setPorco(porco);
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Editar Porco");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                allPorcos();
            }
        });
        stage.show();
    }

    @FXML
    private void onGenerateReport() throws IOException, DocumentException {
       report.setTitle("Relatório de porcos cadastrados.");
        PdfPTable newTable = report.newTable(5);
        newTable.addCell("Codigo");
        newTable.addCell("Nome");
        newTable.addCell("Nascimento");
        newTable.addCell("Aquisição");
        newTable.addCell("Baia");
        for (Porco p : Porco.all()) {
            newTable.addCell(p.getCodigo() + "");
            newTable.addCell(p.getNome());
            newTable.addCell(p.getNascimento() + "");
            newTable.addCell(p.getAquisicao() + "");
            newTable.addCell(p.getBaia()+ "");
        }
        report.addTable(newTable);
        report.generate();
        report.open();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allPorcos();
    }

    private void allPorcos() {
        ObservableList<Porco> porcoList = FXCollections.observableArrayList(Porco.all());
        porcos.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("codigo"));
        porcos.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nome"));
        porcos.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nascimento"));
        porcos.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("aquisicao"));
        porcos.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("baia"));
        porcos.setItems(porcoList);
    }
}
