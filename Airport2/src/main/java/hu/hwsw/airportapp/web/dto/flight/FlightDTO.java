package hu.hwsw.airportapp.web.dto.flight;

import hu.hwsw.airportapp.web.dto.BaseEntityDTO;
import hu.hwsw.airportapp.web.dto.airport.AirportDTO;

import java.time.LocalDateTime;

public class FlightDTO extends BaseEntityDTO {

    private String flightNumber;
    private int capacity;
    private int flightTime;
    private double averageDelay;

    private AirportDTO fromAirport;
    private AirportDTO toAirport;

    public FlightDTO() {
        super();
    }

    public FlightDTO(Long id, LocalDateTime createdAt, LocalDateTime modifiedAt, String flightNumber, int capacity,
                     int flightTime, double averageDelay, AirportDTO fromAirport, AirportDTO toAirport) {
        super(id, createdAt, modifiedAt);
        this.flightNumber = flightNumber;
        this.capacity = capacity;
        this.flightTime = flightTime;
        this.averageDelay = averageDelay;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getFlightTime() {
        return flightTime;
    }
    public void setFlightTime(int flightTime) {
        this.flightTime = flightTime;
    }
    public double getAverageDelay() {
        return averageDelay;
    }
    public void setAverageDelay(double averageDelay) {
        this.averageDelay = averageDelay;
    }
    public AirportDTO getFromAirport() {
        return fromAirport;
    }
    public void setFromAirport(AirportDTO fromAirport) {
        this.fromAirport = fromAirport;
    }
    public AirportDTO getToAirport() {
        return toAirport;
    }
    public void setToAirport(AirportDTO toAirport) {
        this.toAirport = toAirport;
    }
}
