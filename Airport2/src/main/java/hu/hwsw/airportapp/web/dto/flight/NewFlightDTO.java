package hu.hwsw.airportapp.web.dto.flight;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewFlightDTO {

    @NotEmpty
    private String flightNumber;
    @NotNull
    private int capacity;
    @NotNull
    private int flightTime;
    @NotNull
    private double averageDelay;
    @NotNull
    private Long fromAirportId;
    @NotNull
    private Long toAirportId;

    public NewFlightDTO() {
        super();
    }

    public NewFlightDTO(String flightNumber, int capacity, int flightTime,
                        double averageDelay, Long fromAirportId, Long toAirportId) {
        this.flightNumber = flightNumber;
        this.capacity = capacity;
        this.flightTime = flightTime;
        this.averageDelay = averageDelay;
        this.fromAirportId = fromAirportId;
        this.toAirportId = toAirportId;
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
    public Long getFromAirportId() {
        return fromAirportId;
    }
    public void setFromAirportId(Long fromAirportId) {
        this.fromAirportId = fromAirportId;
    }
    public Long getToAirportId() {
        return toAirportId;
    }
    public void setToAirportId(Long toAirportId) {
        this.toAirportId = toAirportId;
    }
}
