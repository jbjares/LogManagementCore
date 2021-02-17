package ai.clarity.challenge.pojo;

import java.io.Serializable;
import java.util.Properties;

public class GenericDataLog implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4993809192445374499L;

	private Long id;
	
	private Properties properties;
	

	
	public GenericDataLog() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}




}
