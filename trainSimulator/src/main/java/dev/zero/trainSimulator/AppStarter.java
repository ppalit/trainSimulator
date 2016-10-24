package dev.zero.trainSimulator;

import org.springframework.stereotype.Component;

/**
 * Spring bean
 *
 */
@Component
public class AppStarter {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public void start() {
		System.out.println("Starting...");
		Logon start = new Logon();
	}
}