package com.javatechie.spring.shell.command;

import java.util.Random;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import org.springframework.web.client.RestTemplate;

@ShellComponent
public class ApplicationCommand {

	@Autowired
	private RestTemplate template;

	@ShellMethod(value = "adding 2 numbers", key = "sum")
	public int add(int a, int b) {
		return a + b;
	}

	@ShellMethod(value = "default value test", key = "sayHello")
	public String wish(@ShellOption(defaultValue = "Java Techie User") String name) {
		return "Hello " + name;
	}

	@ShellMethod(value = "validation", key = "change")
	public String changePassword(@Size(min = 4, max = 10) String password) {
		return "password changed successfully....";
	}

	@ShellMethod(value = "connection checking ", key = "connect")
	@ShellMethodAvailability(value = "server connection establishing ...")
	public String checkConnection() {
		return isAvailable() ? Availability.available().getReason()
				: Availability.unavailable("server is not up and running").getReason();
	}

	@ShellMethod(value = "rest api call ", key = "rest call")
	public String callRestAPI(String url) {
		// https://jsonplaceholder.typicode.com/posts
		return template.getForObject(url, String.class);
	}

	private boolean isAvailable() {
		return new Random().nextBoolean();
	}
}
