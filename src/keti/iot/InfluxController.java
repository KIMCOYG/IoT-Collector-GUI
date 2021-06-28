package keti.iot;
//package keti.iot;
//
//import java.io.IOException;
//import java.time.Instant;
//import java.util.List;
//import java.util.Locale.Category;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import org.influxdb.InfluxDB;
//import org.influxdb.InfluxDBFactory;
//import org.influxdb.annotation.Column;
//import org.influxdb.annotation.Measurement;
//import org.influxdb.dto.Query;
//import org.influxdb.dto.QueryResult;
//import org.influxdb.impl.InfluxDBResultMapper;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.LineChart;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//import javafx.scene.chart.XYChart.Series;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class InfluxController extends AnchorPane {
//	
//	
//	private InfluxDB influxSet(String serverURL, String username, String password, String DBName) {
//		final InfluxDB influxDB = InfluxDBFactory.connect(serverURL, username, password);
//		influxDB.setDatabase(DBName);
//		return influxDB;
//	}
//	private InfluxDB influxSet(String serverURL, String username, String password) {
//		final InfluxDB influxDB = InfluxDBFactory.connect(serverURL, username, password);
//		return influxDB;
//	}
//	
//	private List<Sensor> querryRequest(InfluxDB influxDB, String querryString) {
//		QueryResult queryResult = influxDB.query(new Query(querryString));
//		InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
//		List<Sensor> SensorList = resultMapper
//		  .toPOJO(queryResult, Sensor.class);		
//		
//		return SensorList;
//	}
//	
//
//}
//
//@Measurement(name = "Sensor")
//class Sensor{
//	@Column(name = "time")
//    private Instant time;
//	
//	@Column(name = "name")
//    private String name;
//
//    @Column(name = "id")
//    private String id;
//
//    @Column(name = "sensorValue")
//    private String sensorValue;
//}
//
//@Measurement(name = "Device")
//class Device{
//	@Column(name = "time")
//    private Instant time;
//
//    @Column(name = "id")
//    private String id;
//
//    @Column(name = "ip")
//    private String ip;
//
//    @Column(name = "sensorType")
//    private String[] sensorType;
//}