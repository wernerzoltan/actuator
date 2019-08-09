package hu.hwsw.airportapp.web.dto;

import java.time.LocalDateTime;

public class BaseEntityDTO {

	private Long id;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	
	
	public BaseEntityDTO() {
		super();
	}


	public BaseEntityDTO(Long id, LocalDateTime createdAt, LocalDateTime modifiedAt) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
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
