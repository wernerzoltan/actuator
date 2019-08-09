package hu.hwsw.airportapp.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hu.hwsw.airportapp.mapper.FlightMapper;
import hu.hwsw.airportapp.security.MyUserDetails;
import hu.hwsw.airportapp.service.FlightService;
import hu.hwsw.airportapp.web.dto.flight.FlightDTO;
import hu.hwsw.airportapp.web.dto.flight.NewFlightDTO;
import hu.hwsw.airportapp.web.dto.flight.NewFlightWithNewAirportsDTO;

@RestController
@RequestMapping("/api/v1") //ezen az URL-en lehet elérni az adatokat
public class FlightController {
		
    private FlightService flightService;

    //konstruktor
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    
    
    
    /* Test data:
     * {
	"fromAirport":{"name": "Liszt Ferenc", "iata": "BUD"},
	"toAirport":{"name": "Kennedy", "iata": "JFK"},
	"newFlight": {
		"flightNumber": "ABC123",
		"capacity": 120,
		"flightTime": 8,
		"averageDelay": 0.5,
		"fromAirportId": -1,
		"toAirportId": -1
	}
}
     */
    
    
    //Flight-ot hozunk létre Airporttal együtt
    @PostMapping("/flights/withAirports")
    public FlightDTO createFlight(@RequestBody NewFlightWithNewAirportsDTO newFlightWithNewAirportsDTO){
        return FlightMapper.INSTANCE.flightToDto(flightService.createFlightWithAirports(newFlightWithNewAirportsDTO));
    }
    
    //flight-ot keresünk
    @PostMapping("/flights/search")
    public List<FlightDTO> searchFlights(@RequestBody FlightDTO example){
    	FlightMapper mapper = FlightMapper.INSTANCE;
		return mapper.flightsToDto(flightService.searchFlights(mapper.dtoToFlight(example)));
    }
    
    //airportId alapján keresünk flight-ot
    @GetMapping("/airports/{airportId}/arriving-flights")
    public List<FlightDTO> getArrivingFlightsByAirportId(@PathVariable Long airportId) {
        return FlightMapper.INSTANCE.flightsToDto(flightService.getArrivingFlightsByAirportId(airportId));
    }

    //airportId alapján keresünk flight-ot
    @GetMapping("/airports/{airportId}/departing-flights")
    public List<FlightDTO> getDepartingFlightsByAirportId(@PathVariable Long airportId) {
        return FlightMapper.INSTANCE.flightsToDto(flightService.getDepartingFlightsByAirportId(airportId));
    }

  //Flight-ot hozunk létre Airport nélkül
    @PostMapping("/flights")
    public FlightDTO createFlight(@RequestBody NewFlightDTO newFlight){
        return FlightMapper.INSTANCE.flightToDto(flightService.createFlight(newFlight));
    }

  //user alapján keresünk flight-ot
    @GetMapping("/flights")
    public List<FlightDTO> getFlights(@AuthenticationPrincipal MyUserDetails userDetails) {
    	System.out.println(userDetails.getUsername());
        return FlightMapper.INSTANCE.flightsToDto(flightService.getFlights());
    }

    //id alapján keresünk flight-ot Airport nélkül
    @GetMapping("/flights/{id}")
    public FlightDTO getFlightById(@PathVariable Long id) {
        return FlightMapper.INSTANCE.flightToDto(flightService.getFlightById(id));
    }

    //id alapján update flight
    @PutMapping("/flights/{id}")
    public FlightDTO updateFlightById(@PathVariable Long id, @RequestBody @Valid NewFlightDTO newFlight) {
        return FlightMapper.INSTANCE.flightToDto(flightService.updateFlightById(id, newFlight));
    }

    //idalapján delete flight
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/flights/{id}")
    public void deleteFlightById(@PathVariable Long id){
        flightService.deleteFlightById(id);
    }
}
