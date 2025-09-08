package com.example.demo.dto;

public class TripDTO {
	
	private Long tripId;
    private String source;
    private String destination;
    private String status;
    private Long vehicleId;
    private Long shipmentId;
    
    
	public Long getTripId() {
		return tripId;
	}
	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Long getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}
    
    

}
