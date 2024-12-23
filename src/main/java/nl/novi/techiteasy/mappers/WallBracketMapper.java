package nl.novi.techiteasy.mappers;

import nl.novi.techiteasy.dtos.WallBracketDTO;
import nl.novi.techiteasy.models.WallBracket;

public class WallBracketMapper {

    public static WallBracketDTO toResponseDTO(WallBracket wallBracket) {
        WallBracketDTO dto = new WallBracketDTO();
        dto.setId(wallBracket.getId());
        dto.setSize(wallBracket.getSize());
        dto.setAdjustable(wallBracket.getAdjustable());
        dto.setName(wallBracket.getName());
        dto.setPrice(wallBracket.getPrice());
        return dto;
    }

    public static WallBracket toEntity(WallBracketDTO wallBracketDTO) {
        WallBracket wallBracket = new WallBracket();
        wallBracket.setId(wallBracketDTO.getId());
        wallBracket.setSize(wallBracketDTO.getSize());
        wallBracket.setAdjustable(wallBracketDTO.getAdjustable());
        wallBracket.setName(wallBracketDTO.getName());
        wallBracket.setPrice(wallBracketDTO.getPrice());
        return wallBracket;
    }
}
