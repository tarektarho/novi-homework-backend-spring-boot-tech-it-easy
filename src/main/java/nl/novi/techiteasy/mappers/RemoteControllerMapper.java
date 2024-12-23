package nl.novi.techiteasy.mappers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.remoteControllerDtos.RemoteControllerCreateDTO;
import nl.novi.techiteasy.dtos.remoteControllerDtos.RemoteControllerResponseDTO;
import nl.novi.techiteasy.models.RemoteController;

import java.util.List;
import java.util.stream.Collectors;

public class RemoteControllerMapper {

    public static RemoteControllerResponseDTO toResponseDTO(RemoteController remoteController) {
        RemoteControllerResponseDTO dto = new RemoteControllerResponseDTO();
        dto.setId(remoteController.getId());
        dto.setBrand(remoteController.getBrand());
        dto.setBatteryType(remoteController.getBatteryType());
        dto.setCompatibleWith(remoteController.getCompatibleWith());
        dto.setName(remoteController.getName());
        dto.setPrice(remoteController.getPrice());
        dto.setOriginalStock(remoteController.getOriginalStock());

        return dto;
    }

    public static RemoteController toEntity(@Valid RemoteControllerCreateDTO remoteControllerCreateDTO) {
        RemoteController remoteController = new RemoteController();
        remoteController.setBrand(remoteControllerCreateDTO.getBrand());
        remoteController.setBatteryType(remoteControllerCreateDTO.getBatteryType());
        remoteController.setCompatibleWith(remoteControllerCreateDTO.getCompatibleWith());
        remoteController.setName(remoteControllerCreateDTO.getName());
        remoteController.setPrice(remoteControllerCreateDTO.getPrice());
        remoteController.setOriginalStock(remoteControllerCreateDTO.getOriginalStock());
        return remoteController;
    }

    public static List<RemoteControllerResponseDTO> toResponseDTOList(List<RemoteController> remoteControllers) {
        return remoteControllers.stream()
                .map(RemoteControllerMapper::toResponseDTO)
                .collect(Collectors.toList()).reversed();
    }
}
