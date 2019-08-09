package hu.hwsw.airportapp.security;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import hu.hwsw.airportapp.model.Authority;
import hu.hwsw.airportapp.model.User;
import hu.hwsw.airportapp.repository.AuthorityRepository;
import hu.hwsw.airportapp.repository.UserRepository;

@Component
public class UsersInitializer {

	private UserRepository userRepository;
	
	private AuthorityRepository authorityRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public UsersInitializer(UserRepository userRepository, AuthorityRepository authorityRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public void createUsersWithAuth() {
		if(authorityRepository.count() == 0) {
			authorityRepository.save(new Authority("ROLE_ADMIN"));
			authorityRepository.save(new Authority("ROLE_USER"));
		}
		
		if(userRepository.count() == 0) {
			Authority userAuth = authorityRepository.findByAuthority("ROLE_USER");
			Authority adminAuth = authorityRepository.findByAuthority("ROLE_ADMIN");
			saveUser("admin", "pass", userAuth, adminAuth);
			saveUser("user", "pass", userAuth);
		}

	}
	
	public void saveUser(String userName, String pass, Authority... authorities) {
		userRepository.save(
				new User(userName, 
						passwordEncoder.encode(pass), 
						new HashSet<>(Arrays.asList(authorities))));
	}

	
}
