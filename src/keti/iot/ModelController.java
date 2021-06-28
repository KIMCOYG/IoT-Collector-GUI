package keti.iot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import keti.model.Device;
import keti.model.Sensor;

//import keti.model.Device;

public class ModelController {
	
//	private final HttpClient httpClient = HttpClient.newBuilder()
//			.version(HttpClient.Version.HTTP_2)
//			.build();
	
	private String getJson(String url) throws Exception {
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(url))
//				.setHeader("User-Agent", "Java 11 HttpClient Bot")
				.build();
		
		HttpResponse<String> res = httpClient.send(req, BodyHandlers.ofString());
		
		if(res.statusCode() != 200) {
			System.out.println("발송 실");
		}
		
		String jsonString = res.body();
		
		return jsonString;
	}
	
	public List<Device> getDevice() throws Exception {
		
		try {
			String jsonString = getJson("http://10.0.6.37:2000/get-devices");
			
			Gson gson = new Gson();
			Device[] dList = gson.fromJson(jsonString, Device[].class);
			dataStore.deviceList = new ArrayList<>(Arrays.asList(dList));
			for(Device d : dataStore.deviceList) {
				System.out.println(d.getTime() + "/" + d.getIp() + "/" + d.getLocation() + "/" + d.getServer());
			}
			System.out.println();
		} catch(Exception e) {
			System.out.println("getDevice 전송 실패");
		}
		
		return dataStore.deviceList;
	}
	
	public List<Sensor> getSensor() throws Exception {
		
		try {
			String jsonString = getJson("http://10.0.6.37:2000/get-sensors");
			
			Gson gson = new Gson();
			Sensor[] sList = gson.fromJson(jsonString, Sensor[].class);
			dataStore.sensorList = new ArrayList<>(Arrays.asList(sList));
			for(Sensor s : dataStore.sensorList) {
				System.out.println(s.getTime() + "/" + s.getId() + "/" + s.getType() + "/" + s.getValue());
			}
			System.out.println();			
		} catch(Exception e) {
			System.out.println("getSensor 전송 실패");
		}
		
		return dataStore.sensorList;
	}
	
	public List<Sensor> getSensorValue(String ip) throws Exception {
		try {
			String jsonString = getJson("http://10.0.6.37:2000/get-sensor/"+ip);
			
			Gson gson = new Gson();
			Sensor[] sList = gson.fromJson(jsonString, Sensor[].class);
			dataStore.infoSensorList = new ArrayList<>(Arrays.asList(sList));
			
			System.out.println("infoSensorList");
			for(Sensor s : dataStore.infoSensorList) {
				System.out.println(s.getTime() + "/" + s.getId() + "/" + s.getIp() + "/" + s.getType() + "/" + s.getValue());
			}
			System.out.println();
		} catch(Exception e) {
			System.out.println("GetSensorValue 전송 실패");
		}
		
		return dataStore.infoSensorList;
	}
	
	public void getAllAggregationSensor(String ip) {
		try {
			getCountSensor(ip);
			getMeanSensor(ip);
			getMinSensor(ip);
			getMaxSensor(ip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("getAllAggregationSensor 실패");
			e.printStackTrace();
		}
	}
	
	public Float getAggregationSensor(int i, String cmd) throws Exception {
		String jsonString = null;
		
		try {
			jsonString = getJson(cmd);
			
			System.out.println(jsonString);
			
		} catch(Exception e) {
			System.out.println("GetSensorValue 전송 실패");
		}
		
		float value = Float.valueOf(jsonString);
		
		//0은 cnt, 1은 mean, 2는 min, 3은 max
		dataStore.infoSensorAggregation[i] = value;
		
		return value;
	}
	
	public Float getCountSensor(String id) throws Exception {
		String cmd = "http://10.0.6.37:2000/get-sensor-count/"+ id;
		
		float value = getAggregationSensor(0, cmd);
		
		return value;
	}
	
	public Float getMeanSensor(String id) throws Exception {
		String cmd = "http://10.0.6.37:2000/get-sensor-mean/"+ id;
		
		float value = getAggregationSensor(1, cmd);
		
		return value;
	}
	
	public Float getMinSensor(String id) throws Exception {
		String cmd = "http://10.0.6.37:2000/get-sensor-min/"+ id;
		
		float value = getAggregationSensor(2, cmd);
		
		return value;
	}
	
	public Float getMaxSensor(String id) throws Exception {
		String cmd = "http://10.0.6.37:2000/get-sensor-max/"+ id;
		
		float value = getAggregationSensor(3, cmd);
		
		return value;
	}
	
	public void createDevice(String[] regist) throws Exception {
		
		try {
			Map<String, String> data = new HashMap<>();
			data.put("id", regist[0]);
			data.put("ip", regist[1]);
			data.put("sensor", regist[2]);
			
			System.out.println(data);
			System.out.println();
			
			Gson gson = new Gson();
			String json = gson.toJson(data);
			System.out.println(json);
			
			HttpClient httpClient = HttpClient.newBuilder().build();
			HttpRequest req = HttpRequest.newBuilder()
					.uri(URI.create("http://10.0.6.37:2000/create-device"))
					.POST(BodyPublishers.ofString(json))
					.build();
			
			HttpResponse<?> res = httpClient.send(req, BodyHandlers.discarding());
			System.out.println(res.statusCode());
		
		} catch(Exception e) {
			System.out.println("POST 전송 실패");
		}
				
	}
	
}
