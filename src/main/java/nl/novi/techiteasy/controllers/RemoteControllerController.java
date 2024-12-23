package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.remoteControllerDtos.RemoteControllerCreateDTO;
import nl.novi.techiteasy.dtos.remoteControllerDtos.RemoteControllerResponseDTO;
import nl.novi.techiteasy.dtos.remoteControllerDtos.RemoteControllerUpdateDTO;
import nl.novi.techiteasy.services.RemoteControllerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/remoteControllers")
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }


    @PostMapping
    public ResponseEntity<Object> createRemoteController(
            @Valid @RequestBody RemoteControllerCreateDTO remoteControllerCreateDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        RemoteControllerResponseDTO remoteControllerResponseDTO = remoteControllerService.addRemoteController(remoteControllerCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(remoteControllerResponseDTO);
    }

    @GetMapping("/{remoteControllerId}")
    public ResponseEntity<RemoteControllerResponseDTO> getRemoteController(
            @PathVariable Long remoteControllerId) {
        RemoteControllerResponseDTO remoteControllerResponseDTO = remoteControllerService.getRemoteControllerById(remoteControllerId);
        return ResponseEntity.ok(remoteControllerResponseDTO);
    }

    @PutMapping("/{remoteControllerId}")
    public ResponseEntity<RemoteControllerResponseDTO> updateRemoteController(
            @PathVariable Long remoteControllerId,
            @Valid @RequestBody RemoteControllerUpdateDTO remoteControllerUpdateDTO) {

        RemoteControllerResponseDTO remoteControllerResponseDTO = remoteControllerService.updateRemoteController(remoteControllerId, remoteControllerUpdateDTO);
        return ResponseEntity.ok(remoteControllerResponseDTO);
    }

    @DeleteMapping("/{remoteControllerId}")
    public ResponseEntity<Void> deleteRemote(
            @PathVariable Long remoteControllerId) {
        boolean isDeleted = remoteControllerService.deleteRemote(remoteControllerId);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<RemoteControllerResponseDTO>> getAllRemoteControllers() {

        List<RemoteControllerResponseDTO> dtos = remoteControllerService.getAllRemoteControllers();

        return ResponseEntity.ok(dtos);
    }

}
