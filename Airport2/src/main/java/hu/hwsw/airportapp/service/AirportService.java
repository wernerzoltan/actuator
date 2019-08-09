package hu.hwsw.airportapp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import hu.hwsw.airportapp.model.Airport;
import hu.hwsw.airportapp.web.dto.airport.AirportDTO;
import hu.hwsw.airportapp.web.dto.airport.NewAirportDTO;

public interface AirportService {

	List<Airport> getAirports(String iata, Pageable pageable);

	Airport createAirport(NewAirportDTO newAirport);

	Airport getAirportById(Long id);

	Airport updateAirport(Long id, NewAirportDTO newAirport);
}
