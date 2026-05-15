package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.List;

import javafx.scene.control.Alert.AlertType;

import data.Barang;
import data.Manager;

public class deleteController {
    
    @FXML
    private void initialize() throws Exception{
        // inisialisasi
        barangToTable();
    }

    public void barangToTable() throws Exception{
        List<Barang> list = Manager.load(Manager.filePath);
        list.removeIf(b -> !b.status);
        ObservableList<Barang> data = FXCollections.observableArrayList(list);
        tableBarang.setItems(data);
        tableBarang.refresh();
    }

    @FXML private Label pageTitle;
    @FXML private TableView<Barang> tableBarang;
    @FXML private TextField namaBarangInput;

    @FXML
    private void showDashboard(ActionEvent event) throws Exception { SwitchHelper.switchScene("Main.fxml", event); }

    @FXML
    private void showAdd(ActionEvent event) throws Exception { SwitchHelper.switchScene("add.fxml", event); }
    
    @FXML
    private void showDelete(ActionEvent event) throws Exception { SwitchHelper.switchScene("delete.fxml", event); }
    
    @FXML

    public void deleteBarang() throws Exception {
        String nama = namaBarangInput.getText();
        if (nama.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Masukkan nama barang.");
            return;
        }

        Manager.delete(nama);
        showAlert(AlertType.INFORMATION, "Sukses", "Barang dihapus.");
        barangToTable();
    }


    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
