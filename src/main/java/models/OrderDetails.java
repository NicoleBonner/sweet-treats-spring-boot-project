package models;

import java.time.LocalTime;

public class OrderDetails {

    private LocalTime deliveryTime;
    private boolean fridgeRequired;
    private double distance;

    public OrderDetails(LocalTime deliveryTime, boolean fridgeRequired, double distance) {
        this.deliveryTime = deliveryTime;
        this.fridgeRequired = fridgeRequired;
        this.distance = distance;
    }

    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public boolean isFridgeRequired() {
        return fridgeRequired;
    }

    public void setFridgeRequired(boolean fridgeRequired) {
        this.fridgeRequired = fridgeRequired;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
