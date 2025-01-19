package nl.novi.techiteasy.models;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TelevisionWallBracket {


    @EmbeddedId
    private TelevisionWallBracketKey id;

    @ManyToOne
    @MapsId("televisionId")
    @JoinColumn(name = "television_id")
    private Television television;

    @ManyToOne
    @MapsId("wallBracketId")
    @JoinColumn(name = "wall_bracket_id")
    private WallBracket wallBracket;

    public TelevisionWallBracketKey getId() {
        return id;
    }

    public Television getTelevision() {
        return television;
    }

    public WallBracket getWallBracket() {
        return wallBracket;
    }

    public void setId(TelevisionWallBracketKey id) {
        this.id = id;
    }

    public void setTelevision(Television television) {
        this.television = television;
    }

    public void setWallBracket(WallBracket wallBracket) {
        this.wallBracket = wallBracket;
    }
}
