package com.example.demo.mapper;

import com.example.demo.dto.ShipmentDTO;
import com.example.demo.model.Shipment;
import com.example.demo.model.Trip;

public class ShipmentMapper {

	public static ShipmentDTO toDTO(Shipment shipment) {
		ShipmentDTO dto = new ShipmentDTO();
		dto.setShipmentId(shipment.getShipmentId());
		dto.setGoodsDescription(shipment.getGoodsDescription());
		dto.setWeight(shipment.getWeight());
		dto.setDeliveryStatus(shipment.getDeliveryStatus());
		if(shipment.getTrip() !=null) {
			dto.setTripId(shipment.getTrip().getTripId());
		}
		return dto;
	}
	
	public static Shipment toEntity(ShipmentDTO dto, Trip trip) {
		
		if(dto == null) {
			return null;
		}
		Shipment shipment = new Shipment();
		
		shipment.setGoodsDescription(dto.getGoodsDescription());
		shipment.setWeight(dto.getWeight());
		shipment.setDeliveryStatus(dto.getDeliveryStatus());
		shipment.setShipmentId(dto.getShipmentId());
		
		shipment.setTrip(trip);

		return shipment;
	}
}
