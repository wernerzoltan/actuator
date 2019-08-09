package hu.hwsw.airportapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import hu.hwsw.airportapp.security.UsersInitializer;

@SpringBootApplication
@EnableJpaAuditing
public class Airport2Application implements CommandLineRunner{

	@Autowired
	UsersInitializer usersInitializer;
	
	public static void main(String[] args) {
		SpringApplication.run(Airport2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		usersInitializer.createUsersWithAuth(); //ez meghívja ezt a metódust, ami létrehozza a role-okat és usereket
	}
	
	//a password dekódolására szolgál
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(9); //9-es erősségű a password hash
	}

}
