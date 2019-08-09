package hu.hwsw.airportapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import hu.hwsw.airportapp.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long>{
	
	//iata alapján keres airportot
	Page<Airport> findByIata(String iata, Pageable pageable);

}
