package nl.novi.techiteasy.models;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class WallBracket {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Size is mandatory")
    private String size;
    @NotNull(message = "Adjustable is mandatory")
    private Boolean adjustable;
    @NotBlank(message = "Name Cannot be Null")
    private String name;
    private Double price;

    @ManyToMany(mappedBy = "wallBrackets")
    List<Television> televisions = new ArrayList<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public Boolean getAdjustable() {
        return adjustable;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public List<Television> getTelevisions() {
        return televisions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAdjustable(Boolean adjustable) {
        this.adjustable = adjustable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTelevisions(List<Television> televisions) {
        this.televisions = televisions;
    }


}
