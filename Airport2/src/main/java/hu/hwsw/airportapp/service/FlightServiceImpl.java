package hu.hwsw.airportapp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.hwsw.airportapp.mapper.FlightMapper;
import hu.hwsw.airportapp.model.Airport;
import hu.hwsw.airportapp.model.Flight;
import hu.hwsw.airportapp.repository.AirportRepository;
import hu.hwsw.airportapp.repository.FlightRepository;
import hu.hwsw.airportapp.repository.FlightSpecification;
import hu.hwsw.airportapp.web.dto.flight.NewFlightDTO;
import hu.hwsw.airportapp.web.dto.flight.NewFlightWithNewAirportsDTO;

@Service
public class FlightServiceImpl implements FlightService {

    
    private AirportService airportService;
    
    private FlightRepository flightRepository;

	public FlightServiceImpl(AirportService airportService, FlightRepository flightRepository) {
		this.airportService = airportService;
		this.flightRepository = flightRepository;
	}

    
	@Override
	@Transactional
	public Flight createFlightWithAirports(NewFlightWithNewAirportsDTO newFlightWithNewAirportsDTO) {
		
		Airport fromAirport = airportService.createAirport(newFlightWithNewAirportsDTO.getFromAirport());
		Airport toAirport = airportService.createAirport(newFlightWithNewAirportsDTO.getToAirport());
		Flight flight = new Flight();
		FlightMapper.INSTANCE.updateFromDto(newFlightWithNewAirportsDTO.getNewFlight(), flight);
		flight = flightRepository.save(flight);
		
		flight.setFromAirport(fromAirport);
		flight.setToAirport(toAirport);
		
		return flight;
	}


	@Override
    public Flight createFlight(@Valid NewFlightDTO newFlight) {
    	return null;
    }


    @Override
    public List<Flight> getArrivingFlightsByAirportId(Long airportId) {
    	return null;
    }

    @Override
    public List<Flight> getDepartingFlightsByAirportId(Long airportId) {
    	return null;
    }

    @Override
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long id) {
        return null;
    }

    @Override
    public Flight updateFlightById(Long id, NewFlightDTO newFlight) {
    	 return null;
    }

    @Override
    public void deleteFlightById(Long id) {
    }


	@Override
	public List<Flight> searchFlights(Flight flight) {
		return flightRepository.findAll(new FlightSpecification(flight));
	}

}
