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

    //konstruktor
	public FlightServiceImpl(AirportService airportService, FlightRepository flightRepository) {
		this.airportService = airportService;
		this.flightRepository = flightRepository;
	}

    
	//flight-ot hozunk létre aiportokkal együtt
	@Override
	@Transactional
	public Flight createFlightWithAirports(NewFlightWithNewAirportsDTO newFlightWithNewAirportsDTO) {
		
		//fromAirport és toAirport értékeket átvesszük a DTO-ból
		Airport fromAirport = airportService.createAirport(newFlightWithNewAirportsDTO.getFromAirport());
		Airport toAirport = airportService.createAirport(newFlightWithNewAirportsDTO.getToAirport());
		
		//létrehozunk egy új flight-ot
		Flight flight = new Flight();
		//DTO értékeit átalakítjuk flight-ra és elmentjük az adatbázisban
		FlightMapper.INSTANCE.updateFromDto(newFlightWithNewAirportsDTO.getNewFlight(), flight);
		flight = flightRepository.save(flight);
		
		//a flight 2 mező értékét beállítjuk
		flight.setFromAirport(fromAirport);
		flight.setToAirport(toAirport);
		
		return flight;
	}


	//flight-ot hozunk létre, de jelenleg nincs kidolgozva
	@Override
    public Flight createFlight(@Valid NewFlightDTO newFlight) {
    	return null;
    }


    //jelenleg nincs kidolgozva
	@Override
    public List<Flight> getArrivingFlightsByAirportId(Long airportId) {
    	return null;
    }

    //jelenleg nincs kidolgozva
	@Override
    public List<Flight> getDepartingFlightsByAirportId(Long airportId) {
    	return null;
    }

    //visszaadja a flight-okat
	@Override
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

	//jelenleg nincs kidolgozva
	@Override
    public Flight getFlightById(Long id) {
        return null;
    }

    //jelenleg nincs kidolgozva
	@Override
    public Flight updateFlightById(Long id, NewFlightDTO newFlight) {
    	 return null;
    }

    //jelenleg nincs kidolgozva
	@Override
    public void deleteFlightById(Long id) {
    }


	//visszaadja a specifikációban rögzített elvek szerinti (+-1 óra, stb.) flight-okat
	@Override
	public List<Flight> searchFlights(Flight flight) {
		return flightRepository.findAll(new FlightSpecification(flight));
	}

}
