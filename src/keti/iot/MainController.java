package keti.iot;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Locale.Category;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.influxdb.InfluxDB;
//import org.influxdb.InfluxDBFactory;
//import org.influxdb.annotation.Column;
//import org.influxdb.annotation.Measurement;
//import org.influxdb.dto.Query;
//import org.influxdb.dto.QueryResult;
//import org.influxdb.impl.InfluxDBResultMapper;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController extends AnchorPane {
	@FXML
	private AnchorPane rootPane;
	
	@FXML
	private StackPane contentPane;
	
	@FXML
	public Label infoLabel;

	@FXML
	private Button infoButton;

	@FXML
	private Button registButton;
	
	public Stage parentStage;

	@FXML
	private void infoClick(MouseEvent event) {
		this.infoLabel.setText("info");
		loadPage("info");
	}

	@FXML
	private void registClick(MouseEvent event) {
		this.infoLabel.setText("regist");
		loadPage("register");
	}

	public MainController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
			loadPage("register");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadPage(String page) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
			contentPane.getChildren().clear();
			contentPane.getChildren().add(root);
			
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}