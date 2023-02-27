package models;

import java.time.LocalTime;
import java.util.Objects;

public class Courier {

    private String courierName;
    private LocalTime startTime;
    private LocalTime endTime;
    private double maxDistance;
    private boolean refrigeration;
    private double pricePerMile;

    //constructor to create objects
    public Courier(String courierName, LocalTime startTime, LocalTime endTime, double maxDistance, boolean refrigeration, double pricePerMile) {
        this.courierName = courierName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxDistance = maxDistance;
        this.refrigeration = refrigeration;
        this.pricePerMile = pricePerMile;
    }

    //Getters and setters

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public boolean getRefrigeration() {
        return refrigeration;
    }

    public void setRefrigeration(boolean refrigeration) {
        this.refrigeration = refrigeration;
    }

    public double getPricePerMile() {
        return pricePerMile;
    }

    public void setPricePerMile(double pricePerMile) {
        this.pricePerMile = pricePerMile;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "courierName='" + courierName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", maxDistance=" + maxDistance +
                ", refrigeration=" + refrigeration +
                ", pricePerMile=" + pricePerMile +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return Double.compare(courier.maxDistance, maxDistance) == 0 && refrigeration == courier.refrigeration && Double.compare(courier.pricePerMile, pricePerMile) == 0 && Objects.equals(courierName, courier.courierName) && Objects.equals(startTime, courier.startTime) && Objects.equals(endTime, courier.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courierName, startTime, endTime, maxDistance, refrigeration, pricePerMile);
    }
}



