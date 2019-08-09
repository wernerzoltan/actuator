package hu.hwsw.airportapp.web.dto.flight;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import hu.hwsw.airportapp.web.dto.airport.NewAirportDTO;

public class NewFlightWithNewAirportsDTO {

    @NotNull 
    @Valid
	private NewFlightDTO newFlight;
	
    @NotNull 
    @Valid
    private NewAirportDTO fromAirport;
    
    @NotNull
    @Valid
    private NewAirportDTO toAirport;

	public NewFlightDTO getNewFlight() {
		return newFlight;
	}

	public void setNewFlight(NewFlightDTO newFlight) {
		this.newFlight = newFlight;
	}

	public NewAirportDTO getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(NewAirportDTO fromAirport) {
		this.fromAirport = fromAirport;
	}

	public NewAirportDTO getToAirport() {
		return toAirport;
	}

	public void setToAirport(NewAirportDTO toAirport) {
		this.toAirport = toAirport;
	}
    
}
