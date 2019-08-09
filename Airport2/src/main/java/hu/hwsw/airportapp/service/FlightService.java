package hu.hwsw.airportapp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import hu.hwsw.airportapp.model.Flight;
import hu.hwsw.airportapp.web.dto.flight.NewFlightDTO;
import hu.hwsw.airportapp.web.dto.flight.NewFlightWithNewAirportsDTO;

@Service
public interface FlightService {
    List<Flight> getArrivingFlightsByAirportId(Long airportId);

    List<Flight> getDepartingFlightsByAirportId(Long airportId);

    Flight createFlight(@Valid NewFlightDTO newFlight);

    List<Flight> getFlights();

    Flight getFlightById(Long id);

    Flight updateFlightById(Long id, NewFlightDTO newFlight);

    void deleteFlightById(Long id);

	Flight createFlightWithAirports(NewFlightWithNewAirportsDTO newFlightWithNewAirportsDTO);
	
	List<Flight> searchFlights(Flight flight);
}
