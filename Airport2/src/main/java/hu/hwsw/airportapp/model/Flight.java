package hu.hwsw.airportapp.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Flight {

	@Id
	@GeneratedValue
	private Long id;
	private String flightNumber;
	private int capacity;
	private int flightTime;
	private double averageDelay;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime modifiedAt;

	@ManyToOne
	private Airport fromAirport;
	@ManyToOne
	private Airport toAirport;

	public Flight() {
		super();
	}

	public Flight(Long id, LocalDateTime createdAt, LocalDateTime modifiedAt, String flightNumber, int capacity,
			int flightTime, double averageDelay, Airport fromAirport, Airport toAirport) {
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

	public Airport getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(Airport fromAirport) {
		this.fromAirport = fromAirport;
	}

	public Airport getToAirport() {
		return toAirport;
	}

	public void setToAirport(Airport toAirport) {
		this.toAirport = toAirport;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
