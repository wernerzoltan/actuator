package hu.hwsw.airportapp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import hu.hwsw.airportapp.model.Flight;
import hu.hwsw.airportapp.web.dto.flight.FlightDTO;
import hu.hwsw.airportapp.web.dto.flight.NewFlightDTO;

@Mapper
public interface FlightMapper {

	FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);
	
	FlightDTO flightToDto(Flight flight);
	
	//ez listát tud fogadni és úgy alakítja át
	List<FlightDTO> flightsToDto(List<Flight> flight);
	
	void updateFromDto(NewFlightDTO dto, @MappingTarget Flight flight);

	//ez visszaalakítja DTO-ból Flight-ra (csak egy példa)
	Flight dtoToFlight(FlightDTO example);
}
