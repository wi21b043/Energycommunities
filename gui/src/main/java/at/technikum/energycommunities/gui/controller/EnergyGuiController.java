package at.technikum.energycommunities.gui.controller;

import at.technikum.energycommunities.gui.model.CurrentEnergy;
import at.technikum.energycommunities.gui.model.HistoricalEnergy;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class EnergyGuiController {

    @FXML private Label currentLabel;
    @FXML private TextField startField;
    @FXML private TextField endField;
    @FXML private TableView<HistoricalEnergy> historyTable;
    @FXML private TableColumn<HistoricalEnergy, String> colHour;
    @FXML private TableColumn<HistoricalEnergy, Double> colCommunityUse;
    @FXML private TableColumn<HistoricalEnergy, Double> colGridUse;

    private final HttpClient http = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        // 初始化表格列绑定 model 属性
        colHour.setCellValueFactory(new PropertyValueFactory<>("hour"));
        colCommunityUse.setCellValueFactory(new PropertyValueFactory<>("communityUsed"));
        colGridUse.setCellValueFactory(new PropertyValueFactory<>("gridUsed"));
    }

    @FXML
    public void onFetchCurrent() {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/energy/current"))
                    .GET().build();

            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            CurrentEnergy ce = mapper.readValue(resp.body(), CurrentEnergy.class);

            currentLabel.setText(String.format(
                    "hour: %s\nCommunity depletion: %.2f%%\nGrid: %.2f%%",
                    ce.getHour(), ce.getCommunityDepleted(), ce.getGridPortion()
            ));
        } catch (Exception e) {
            currentLabel.setText("Failed to get: " + e.getMessage());
        }
    }

    @FXML
    public void onFetchHistorical() {
        try {
            String start = startField.getText();
            String end = endField.getText();
            String url = String.format(
                    "http://localhost:8080/energy/historical?start=%s&end=%s",
                    start, end
            );

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET().build();

            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            List<HistoricalEnergy> list = mapper.readValue(
                    resp.body(), new TypeReference<List<HistoricalEnergy>>() {});

            historyTable.getItems().setAll(list);
        } catch (Exception e) {
            historyTable.getItems().clear();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Historische Daten konnten nicht abgerufen werden:\n" + e.getMessage());
            alert.showAndWait();
        }
    }
}
