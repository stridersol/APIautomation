package pojo;

public class cityRequest {
	
	public cityRequest() {
		
	}
	
	public cityRequest(String name,String temperature) {
		this.name=name;
		this.temperature=temperature;
	}
	
	private String name;
	private String temperature;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	

}
