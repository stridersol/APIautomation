package pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersCreate {
	
	public UsersCreate() {
		
	}
	public UsersCreate(String name,String job,List<String> languages,List<cityRequest> cityRequest) {
		this.name = name;
		this.job = job;
		this.languages=languages;
		this.cityRequest = cityRequest;
	}
	
	private String name;
	private String job;
	private List<String>  languages;
	private List<cityRequest> cityRequest;
	
	public List<cityRequest> getCityRequest() {
		return cityRequest;
	}
	public void setCityRequest(List<cityRequest> cityRequest) {
		this.cityRequest = cityRequest;
	}

	public List<String> getLanguages() {
		return languages;
	}
	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
}
