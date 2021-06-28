package keti.iot;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.influxdb.InfluxDB;
//import org.influxdb.InfluxDBFactory;
//import org.influxdb.annotation.Column;
//import org.influxdb.annotation.Measurement;
//import org.influxdb.dto.Query;
//import org.influxdb.dto.QueryResult;
//import org.influxdb.impl.InfluxDBResultMapper;

import javafx.application.Application;
import javafx.application.Platform;
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
import keti.model.Sensor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

public class infoController implements Initializable {
	@FXML
	private ScrollPane infoPane;

	@FXML
	private VBox contentVbox;

	@FXML
	private LineChart<String, Float> sensorChart;

	@FXML
	private Label logLabel;
	
	@FXML
	private NumberAxis yAxis;

	private Series<String, Float> series = new XYChart.Series<>();

	public Stage parentStage;
	
	private List<Sensor> sensorList = new ArrayList<>();
	private boolean stop = false;
	int value = 10;
	final int WINDOW_SIZE = 10;
	static int i = 0;

	private void drawChart() {	
		
//		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

		// setup a scheduled executor to periodically put data into the chart
		ScheduledExecutorService scheduledExecutorService;
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		sensorChart.getData().add(series);
		// put dummy data onto graph per second
		scheduledExecutorService.scheduleAtFixedRate(() -> {
			// get a random integer between 0-10
			Integer random = ThreadLocalRandom.current().nextInt(10);

			// Update the chart
			Platform.runLater(() -> {
				
				// get current time
//				Date now = new Date();
				// put random number with current time
//				series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), random));
				if(i==sensorList.size()) {
					i = 0;
				}
				
				System.out.println(sensorList.get(i).getValue());
				series.getData().add(new XYChart.Data<>(sensorList.get(i).getTime(), sensorList.get(i).getValue()));
				
				i++;
				
				if (series.getData().size() > WINDOW_SIZE)
                    series.getData().remove(0);
			});
		}, 0, 1, TimeUnit.SECONDS);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
//		yAxis.setAutoRanging(false);
//		yAxis.setLowerBound(0);
//		yAxis.setUpperBound(100);
//		yAxis.setTickUnit(20);
		
		Device device = new Device();
		device = dataStore.infoDevice;
		sensorList = dataStore.infoSensorList;
		
		logLabel.setText(device.getIp() + "/ " + device.getLocation() + "/ " + device.getServer() + "\n" +
				"Sensor Count: " + dataStore.infoSensorAggregation[0] + "\n" +
				"Sensor Mean: " + dataStore.infoSensorAggregation[1] + "\n" + 
				"Sensor Min: " + dataStore.infoSensorAggregation[2] + "\n" + 
				"Sensor Max: " + dataStore.infoSensorAggregation[3]
				);
		System.out.println(sensorList);
		drawChart();

	}

}
