package keti.iot;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import keti.model.Device;
import keti.model.Sensor;

public class dataStore { // �� ���������� ��� Ŭ����  (�����͸� �� ���� ����)
//   public static String list = new String();
	
	public static List<Device> deviceList = new ArrayList<>();
	
	public static List<Sensor> sensorList = new ArrayList<>();
	
	public static Device infoDevice = new Device();
	
	public static List<Sensor> infoSensorList = new ArrayList<>();
	
	//0은 cnt, 1은 mean, 2는 min, 3은 max
	public static float[] infoSensorAggregation = new float[4];
}
 