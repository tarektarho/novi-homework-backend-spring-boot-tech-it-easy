package nl.novi.techiteasy.mappers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.televisionDtos.TelevisionCreateDTO;
import nl.novi.techiteasy.dtos.televisionDtos.TelevisionResponseDTO;
import nl.novi.techiteasy.models.Television;

public class TelevisionMapper {

    public static TelevisionResponseDTO toResponseDTO(Television television) {
        // Fallback to null if television is null
        if (television == null) {
            return null;
        }

        TelevisionResponseDTO dto = new TelevisionResponseDTO();
        dto.setId(television.getId());
        dto.setName(television.getName());
        dto.setBrand(television.getBrand());
        dto.setType(television.getType());
        dto.setYear(television.getAge());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setBluetooth(television.isBluetooth());
        dto.setWifi(television.isWifi());
        dto.setHdr(television.isHdr());
        dto.setSold(television.getSold());
        dto.setSoldAt(television.getSoldAt());
        dto.setBoughtAt(television.getBoughtAt());

        // Map RemoteController to RemoteControllerResponseDTO
        if (television.getRemoteController() != null) {
            dto.setRemoteController(RemoteControllerMapper.toResponseDTO(television.getRemoteController()));
        }

        if (television.getCiModule() != null) {
            dto.setCiModule(CIModuleMapper.toRequestDTO(television.getCiModule()));
        }

        return dto;
    }

    public static Television toEntity(@Valid TelevisionCreateDTO televisionCreateDTO) {
        if (televisionCreateDTO == null) {
            return null;
        }

        var result = new Television();
        result.setBrand(televisionCreateDTO.getBrand());
        result.setName(televisionCreateDTO.getName());
        result.setType(televisionCreateDTO.getType());
        result.setPrice(televisionCreateDTO.getPrice());
        result.setAvailableSize(televisionCreateDTO.getAvailableSize());
        result.setRefreshRate(televisionCreateDTO.getRefreshRate());
        result.setScreenType(televisionCreateDTO.getScreenType());
        result.setScreenQuality(televisionCreateDTO.getScreenQuality());
        result.setBluetooth(televisionCreateDTO.isBluetooth());
        result.setWifi(televisionCreateDTO.isWifi());
        result.setHdr(televisionCreateDTO.isHdr());
        result.setSold(televisionCreateDTO.getSold());
        result.setSoldAt(televisionCreateDTO.getSoldAt());
        result.setBoughtAt(televisionCreateDTO.getBoughtAt());

        // Assigning the RemoteController is not done in this method. That linkage happens later.
        return result;
    }
}
