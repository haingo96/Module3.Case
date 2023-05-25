package model;

import java.time.LocalDate;

public class House {
    private int houseId;
    private double price;
    private LocalDate viewDate;
    private LocalDate unavailableUntil;
    private String area;
    private String type;
    private boolean status;
    private Address addressId;
    private int renterId;
    private int ownerId;
    private String description;

    public House() {
    }

    public House(int houseId, double price, LocalDate viewDate, LocalDate unavailableUntil, String area, String type, boolean status, Address addressId, int renterId, int ownerId, String description) {
        this.houseId = houseId;
        this.price = price;
        this.viewDate = viewDate;
        this.unavailableUntil = unavailableUntil;
        this.area = area;
        this.type = type;
        this.status = status;
        this.addressId = addressId;
        this.renterId = renterId;
        this.ownerId = ownerId;
        this.description = description;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getViewDate() {
        return viewDate;
    }

    public void setViewDate(LocalDate viewDate) {
        this.viewDate = viewDate;
    }

    public LocalDate getUnavailableUntil() {
        return unavailableUntil;
    }

    public void setUnavailableUntil(LocalDate unavailableUntil) {
        this.unavailableUntil = unavailableUntil;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}