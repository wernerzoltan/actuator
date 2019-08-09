package hu.hwsw.airportapp.web.dto.airport;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class NewAirportDTO {

	@NotEmpty
	private String name;
	
	@NotEmpty
	@Size(min = 3, max = 3)
	private String iata;
	
	
	
	public NewAirportDTO() {
		super();
	}



	public NewAirportDTO(String name, String iata) {
		super();
		this.name = name;
		this.iata = iata;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getIata() {
		return iata;
	}



	public void setIata(String iata) {
		this.iata = iata;
	}
}
