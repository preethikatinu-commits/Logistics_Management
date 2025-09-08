package com.example.demo.dto;

public class ShipmentDTO {

	 private Long shipmentId;
	    private String goodsDescription;
	    private double weight;
	    private String deliveryStatus;
	    private Long tripId;
	    
	    
		public Long getShipmentId() {
			return shipmentId;
		}
		public void setShipmentId(Long shipmentId) {
			this.shipmentId = shipmentId;
		}
		public String getGoodsDescription() {
			return goodsDescription;
		}
		public void setGoodsDescription(String goodsDescription) {
			this.goodsDescription = goodsDescription;
		}
		public double getWeight() {
			return weight;
		}
		public void setWeight(double weight) {
			this.weight = weight;
		}
		public String getDeliveryStatus() {
			return deliveryStatus;
		}
		public void setDeliveryStatus(String deliveryStatus) {
			this.deliveryStatus = deliveryStatus;
		}
		public Long getTripId() {
			return tripId;
		}
		public void setTripId(Long tripId) {
			this.tripId = tripId;
		}
	    
	    
}
