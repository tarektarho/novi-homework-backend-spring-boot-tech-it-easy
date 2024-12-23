package nl.novi.techiteasy.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import nl.novi.techiteasy.models.WallBracket;

public class WallBracketDTO {

    private Long id;
    @NotBlank(message = "Size is mandatory")
    private String size;
    @NotNull(message = "Adjustable is mandatory")
    private Boolean adjustable;
    @NotBlank(message = "Name Cannot be Null")
    private String name;
    private Double price;

    public WallBracketDTO () {
    }

    public WallBracketDTO (WallBracket wallBracket) {
        this.id = wallBracket.getId();
        this.size = wallBracket.getSize();
        this.adjustable = wallBracket.getAdjustable();
        this.name = wallBracket.getName();
        this.price = wallBracket.getPrice();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getAdjustable() {
        return adjustable;
    }

    public void setAdjustable(Boolean adjustable) {
        this.adjustable = adjustable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
