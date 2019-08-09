package hu.hwsw.airportapp.mapper;


//mapstruct: kód-generátor, ami a mapping-et megkönnyíti java bean-ek között
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import hu.hwsw.airportapp.model.Airport;
import hu.hwsw.airportapp.web.dto.airport.AirportDTO;
import hu.hwsw.airportapp.web.dto.airport.NewAirportDTO;



@Mapper
public interface AirportMapper {

	//ezt kötelező leírni, ezen keresztül lehet a Mappers class-hoz hozzáférni és a mappelést elvgezni
	AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class); 
	
	//az Airport-ot AirportDTO-ra lehet alakítani ha ezt meghívjuk
	AirportDTO airportToDto(Airport airport);
	
	//ez update során használatos, a DTO-ból alakítunk vissza Airport-ra
	void updateFromDto(NewAirportDTO dto, @MappingTarget Airport airport);
	
	//az Airport tartalma: id, cretedAt, modifiedAt, name, iata
	//az AirportDTO tartalma: name, iata
	
}
