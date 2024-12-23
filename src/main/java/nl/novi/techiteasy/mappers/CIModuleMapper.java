package nl.novi.techiteasy.mappers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.CIModuleDTO;
import nl.novi.techiteasy.models.CIModule;

public class CIModuleMapper {

    public static CIModuleDTO toRequestDTO(CIModule ciModule) {
        CIModuleDTO dto = new CIModuleDTO();
        dto.setId(ciModule.getId());
        dto.setName(ciModule.getName());
        dto.setType(ciModule.getType());
        dto.setPrice(ciModule.getPrice());

        return dto;
    }

    public static CIModule toEntity(@Valid CIModuleDTO ciModuleDTO) {
        CIModule ciModule = new CIModule();
        ciModule.setId(ciModuleDTO.getId());
        ciModule.setName(ciModuleDTO.getName());
        ciModule.setType(ciModuleDTO.getType());
        ciModule.setPrice(ciModuleDTO.getPrice());

        return ciModule;
    }

}
