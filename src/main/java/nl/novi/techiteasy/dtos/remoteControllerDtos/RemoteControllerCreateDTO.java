package nl.novi.techiteasy.dtos.remoteControllerDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RemoteControllerCreateDTO {
    @NotBlank(message = "compatibleWith cannot be empty")
    private String compatibleWith;
    @NotBlank(message = "batteryType cannot be empty")
    private String batteryType;
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "brand cannot be empty")
    private String brand;
    @NotNull
    private Double price;
    @NotNull
    private int originalStock;

    // Getters and Setters
    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(int originalStock) {
        this.originalStock = originalStock;
    }

}
