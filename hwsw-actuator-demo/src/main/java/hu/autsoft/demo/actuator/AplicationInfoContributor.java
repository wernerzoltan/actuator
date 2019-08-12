package hu.autsoft.demo.actuator;

import org.springframework.beans.factory.annotation.Value;

public class AplicationInfoContributor implements InfoContributor {
	
	@Value("${spring.application.name:unknown}") //az application.proprties-ből szedünk át értéket. A default értéke unknown
	private String applicationName;
	
	@Override
	public void contribute(Builder builder) {
		builder.withDetail("name", applicationName);
	}
	
}