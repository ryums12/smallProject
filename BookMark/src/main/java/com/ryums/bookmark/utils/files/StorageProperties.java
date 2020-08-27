package com.ryums.bookmark.utils.files;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageProperties {

	private String location = "upload-dir/";

	public String getLocation() {
		return this.location;
	}

//	public void setLocation(String location) {
//		this.location = location;
//	}

}
