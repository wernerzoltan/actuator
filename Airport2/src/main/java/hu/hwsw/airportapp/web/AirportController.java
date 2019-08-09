package hu.hwsw.airportapp.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hu.hwsw.airportapp.mapper.AirportMapper;
import hu.hwsw.airportapp.model.Airport;
import hu.hwsw.airportapp.repository.AirportRepository;
import hu.hwsw.airportapp.service.AirportService;
import hu.hwsw.airportapp.web.dto.airport.AirportDTO;
import hu.hwsw.airportapp.web.dto.airport.NewAirportDTO;

@RestController
@RequestMapping("/api/v1/airports") //ezen az URL-en lehet elérni az adatokat
public class AirportController {

	private final AirportService airportService;
	private final AirportRepository airportRepository;
	
	//konstruktor (A konstruktor inicializálja az új objektumot.)
	public AirportController(AirportService airportService, AirportRepository airportRepository) {
		super();
		this.airportService = airportService;
		this.airportRepository = airportRepository;
	}


	//aiportok lekérdezése (iata-val kérdezünk le, oldalakra osztva adjuk vissza (Pageable))
	@GetMapping
	List<AirportDTO> getAirports(@RequestParam(name = "iata", required = false) String iata, Pageable pageable) {
		return airportService.getAirports(iata, pageable)
				.stream() //összállítjuk a választ
				.map(airport -> mapAirportToDto(airport)) //az airportból DTO-t mappelünk
				.collect(Collectors.toList()); //LIST-be összetesszük az összes Airportot
	}

	//az airportból DTO-t mappelünk
	private AirportDTO mapAirportToDto(Airport airport) {
		return new AirportDTO(airport.getId(), airport.getCreatedAt(), airport.getModifiedAt(), airport.getName(), airport.getIata());
	}

	//Airportot hozunk létre, validáljuk a DTO-t, azaz nem lehet empty a két mező
	@PostMapping
	AirportDTO createAirport(@RequestBody @Valid NewAirportDTO newAirport) {
		//az airportService-t hívjuk meg és átadjuk neki a NewAirportDTO-t
		return mapAirportToDto(airportService.createAirport(newAirport));
	}

	//id alapjánkérjük le az Aiport-ot
	@GetMapping("/{id}")
	ResponseEntity<AirportDTO> getAirportById(@PathVariable Long id) {
	    return ResponseEntity //HTTP státus kódot is visszaad
	    		.status(HttpStatus.OK) //itt adjuk vissza a HTTP státuszt
	    		.body(mapAirportToDto(airportService.getAirportById(id))); //itt adjuk vissza a törzset

	}

	//Airportot updatelünk
	@PutMapping("/{id}")
	AirportDTO updateAirport(@PathVariable Long id, @RequestBody @Valid NewAirportDTO newAirport) {
		return AirportMapper.INSTANCE.airportToDto(airportService.updateAirport(id, newAirport));
	}

	//Airport törlése
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteAirport(@PathVariable Long id) {
		airportRepository.deleteById(id);
	}
}
