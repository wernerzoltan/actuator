package hu.hwsw.airportapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hu.hwsw.airportapp.mapper.AirportMapper;
import hu.hwsw.airportapp.model.Airport;
import hu.hwsw.airportapp.repository.AirportRepository;
import hu.hwsw.airportapp.web.dto.airport.AirportDTO;
import hu.hwsw.airportapp.web.dto.airport.NewAirportDTO;

@Service
public class AirportServiceImpl implements AirportService {
	
	@Autowired
	AirportRepository airportRepository;

	//az Airportokat adja vissza
	@Override
	public List<Airport> getAirports(String iata, Pageable pageable) {
		
		//ha az iata String értéke üres, akkor az adatbázis összes adatát adja vissza
		if (StringUtils.isEmpty(iata)) {
			return airportRepository.findAll(pageable).getContent();
		//ha nem üres az iata értéke, akkor az iata-hoz tartozó adatokat adja vissza
		} else {
			return airportRepository.findByIata(iata, pageable).getContent();
		}
		
	}

	//új Airportot csinál (a NewAIportDTO-ban megadott iata és name változókat adjuk át neki
	@Override
	public Airport createAirport(NewAirportDTO newAirport) {
		//új Aiport-ot hozunk létre (POJO-t)
		Airport airport = new Airport();
		airport.setIata(newAirport.getIata());
		airport.setName(newAirport.getName());
		return airportRepository.save(airport);
	}
	
	//ID alapján kérjük le az Aiportot 
	@Override
	public Airport getAirportById(Long id) {
		
		//optional =  object which may or may not contain a non-null value. 
		//If a value is present, isPresent() will return true and get() will return the value.
		Optional<Airport> airportOptional = airportRepository.findById(id);

		//ha üres értéket ad vissza, exception-ra szöveget ír ki
		if (airportOptional.isEmpty()) {
			throw new NoSuchElementException(String.format("Airport not found with id %d", id));
		}

		//máskülönben átadja az Airport értékeit
		return airportOptional.get();
	}

	//Airport-ot updatelünk
	@Override
	@Transactional
	public Airport updateAirport(Long id, NewAirportDTO newAirport) {
		Airport airport = getAirportById(id);
		//itt történik meg a mappelés: NewAirportDTO-ból Airportot készítünk
		AirportMapper.INSTANCE.updateFromDto(newAirport, airport);
		return airport;
	}

}
