package hu.hwsw.airportapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.hwsw.airportapp.model.User;


public interface UserRepository extends JpaRepository<User, String>{
}
