package vn.com.irtech.irbot.business.dto;

import java.io.Serializable;

public class ProcessGslctConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String url;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
