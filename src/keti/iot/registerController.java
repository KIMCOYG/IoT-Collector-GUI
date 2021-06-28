package keti.iot;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.influxdb.InfluxDB;
//import org.influxdb.InfluxDBFactory;
//import org.influxdb.annotation.Column;
//import org.influxdb.annotation.Measurement;
//import org.influxdb.dto.Query;
//import org.influxdb.dto.QueryResult;
//import org.influxdb.impl.InfluxDBResultMapper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import keti.model.Device;

public class registerController implements Initializable {
	
	@FXML
	private ScrollPane registerPane;

	@FXML
	private VBox contentVbox;

	@FXML
	private TextField idField;

	@FXML
	private TextField ipField;

	@FXML
	private TextField sensorField;

	@FXML
	private Button registButton;

	@FXML
	private ListView<String> registList;

	public Stage parentStage;

	private ObservableList<String> registItems;
	
	private HashMap<String, Device> registArray = new HashMap<String, Device>();
	
	private ModelController model = new ModelController();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		registItems = FXCollections.observableArrayList();
		
		for(Device d : dataStore.deviceList) {
			registArray.put(d.getIp()+"-"+d.getServer(), d);
			registItems.add(d.getIp() + "-" + d.getServer());
		}
		
		registList.setItems(registItems);

		// Disable buttons to start
		registButton.setDisable(false);

		// Add a ChangeListener to TextField to look for change in focus
		sensorField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if (sensorField.isFocused()) {
					registButton.setDisable(false);
				}

			}
		});
	}

	@FXML
	private void registClick(MouseEvent event) throws Exception {
		String[] regist = new String[3];
		regist[0] = idField.getText();
		regist[1] = ipField.getText();
		regist[2] = sensorField.getText();
		model.createDevice(regist);
		Device device = new Device(regist[0], regist[1], regist[2]);
		registArray.put(regist[0]+"-"+regist[2], device);
		registItems.add(regist[0]+"-"+regist[2]);
//		System.out.println(regist[0]+"-"+regist[1] + "-"+regist[2]);
	}
	@FXML
	private void listViewClick(MouseEvent event) throws Exception {
		Object obj = registList.getSelectionModel().getSelectedItem();
		String ip = registArray.get(obj).getIp();
		String location = registArray.get(obj).getLocation();
		String server = registArray.get(obj).getServer();
		System.out.println(ip + " "+ location + " "+server + " ");
		if(event.getClickCount()>1) {
			try {
				dataStore.infoDevice = registArray.get(obj);
				dataStore.infoSensorList = model.getSensorValue(dataStore.infoDevice.getIp());
				model.getAllAggregationSensor(dataStore.infoDevice.getIp());
				
				Parent root = FXMLLoader.load(getClass().getResource("info.fxml"));
				registerPane.setContent(root);
				
				System.out.println("성공!");
			} catch (IOException ex) {
				Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
			}
     }
		
	}
}