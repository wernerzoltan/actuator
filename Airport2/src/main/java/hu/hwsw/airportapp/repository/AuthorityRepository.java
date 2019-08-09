package hu.hwsw.airportapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.hwsw.airportapp.model.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Integer>{

	//authoroty alapján keres 
	Authority findByAuthority(String name); 
}
