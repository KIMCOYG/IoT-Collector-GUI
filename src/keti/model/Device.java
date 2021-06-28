package keti.model;

public class Device {
	
	private String time;
	
//	private String id;
	
	private String ip;
	
//	private String sensor;
	
	private String location;
	
	private String server;
	
	public Device() {}
	
	public Device(String ip, String location, String server){
		this.time = null;
		this.ip = ip;
		this.server = server;
		
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return this.time;
	}
	
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void setServer(String server) {
		this.server = server;
	}
	
	public String getServer() {
		return this.server;
	}
}
