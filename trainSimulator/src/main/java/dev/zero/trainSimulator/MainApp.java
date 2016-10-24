package dev.zero.trainSimulator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("trainSimulator.xml");

	      AppStarter obj = (AppStarter) context.getBean("AppStarter");

	      obj.start();
	}

}
