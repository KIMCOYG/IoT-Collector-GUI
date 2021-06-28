package keti.model;

public class Sensor {
	
	private String time;
	
	private String ip;
	
	private String id;
	
	private String type;
	
	private Float value;
	
	
	public String getTime() {
		return this.time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Float getValue() {
		return this.value;
	}
	
	public void setValue(Float value) {
		this.value = value;
	}
}
