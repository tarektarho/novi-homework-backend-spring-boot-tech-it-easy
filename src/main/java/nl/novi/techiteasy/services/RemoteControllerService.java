package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.remoteControllerDtos.RemoteControllerCreateDTO;
import nl.novi.techiteasy.dtos.remoteControllerDtos.RemoteControllerResponseDTO;
import nl.novi.techiteasy.dtos.remoteControllerDtos.RemoteControllerUpdateDTO;
import nl.novi.techiteasy.mappers.RemoteControllerMapper;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public RemoteControllerResponseDTO addRemoteController(RemoteControllerCreateDTO remoteControllerCreateDTO) {

        RemoteController remoteController = RemoteControllerMapper.toEntity(remoteControllerCreateDTO);

        remoteControllerRepository.save(remoteController);

        return RemoteControllerMapper.toResponseDTO(remoteController);
    }

    public RemoteControllerResponseDTO updateRemoteController(Long remoteControllerId, RemoteControllerUpdateDTO remoteControllerUpdateDTO) {
        RemoteController remoteController = remoteControllerRepository.findById(remoteControllerId)
                .orElseThrow(() -> new RuntimeException("Remote not found with id " + remoteControllerId));


        remoteController.setCompatibleWith(remoteControllerUpdateDTO.getCompatibleWith());
        remoteController.setBatteryType(remoteControllerUpdateDTO.getBatteryType());
        remoteController.setName(remoteControllerUpdateDTO.getName());
        remoteController.setBrand(remoteControllerUpdateDTO.getBrand());
        remoteController.setPrice(remoteControllerUpdateDTO.getPrice());
        remoteController.setOriginalStock(remoteControllerUpdateDTO.getOriginalStock());

        RemoteController updatedRemoteController = remoteControllerRepository.save(remoteController);

        return RemoteControllerMapper.toResponseDTO(updatedRemoteController);

    }

    public List<RemoteControllerResponseDTO> getAllRemoteControllers() {
        List<RemoteControllerResponseDTO> dtos = new ArrayList<>();
        List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
        for (RemoteController rc : remoteControllers) {
            dtos.add(RemoteControllerMapper.toResponseDTO(rc));
        }
        return dtos;
    }

    public RemoteControllerResponseDTO getRemoteControllerById(Long remoteControllerId) {
        RemoteController remoteController = remoteControllerRepository.findById(remoteControllerId)
                .orElseThrow(() -> new RuntimeException("Remote not found with id " + remoteControllerId));

        return RemoteControllerMapper.toResponseDTO(remoteController);
    }

    public boolean deleteRemote(Long remoteControllerId) {
        remoteControllerRepository.findById(remoteControllerId)
                .orElseThrow(() -> new RuntimeException("Remote not found with id " + remoteControllerId));

        if (remoteControllerRepository.existsById(remoteControllerId)) {
            remoteControllerRepository.deleteById(remoteControllerId);
            return true;
        }
        return false;
    }
}
