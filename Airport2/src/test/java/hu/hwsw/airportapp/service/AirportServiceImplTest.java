package hu.hwsw.airportapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import hu.hwsw.airportapp.model.Airport;
import hu.hwsw.airportapp.repository.AirportRepository;
import hu.hwsw.airportapp.web.dto.airport.NewAirportDTO;

@ExtendWith(MockitoExtension.class)
public class AirportServiceImplTest {
	
	@InjectMocks
	AirportServiceImpl airportService;
	
	@Mock
	AirportRepository airportRepository;
	
	
	@Test
	void createAirport() {
		NewAirportDTO newAirport = new NewAirportDTO("Budapest", "BUD");
		
		when(airportRepository.save(any())).thenAnswer( i-> {
			Airport arg = i.getArgument(0);
			return new Airport(100L, arg.getCreatedAt(), arg.getModifiedAt(), arg.getName(), arg.getIata());
		});
		
		Airport airportSaved = airportService.createAirport(newAirport);
		
		assertEquals(newAirport.getName(), airportSaved.getName());
		assertEquals(newAirport.getIata(), airportSaved.getIata());
		assertNotNull(airportSaved.getModifiedAt());
		assertNotNull(airportSaved.getCreatedAt());
		assertEquals(100L, airportSaved.getId().longValue());
	}

}
