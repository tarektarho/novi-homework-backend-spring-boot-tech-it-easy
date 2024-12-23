package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.IdInputDTO;
import nl.novi.techiteasy.dtos.WallBracketDTO;
import nl.novi.techiteasy.dtos.televisionDtos.TelevisionCreateDTO;
import nl.novi.techiteasy.dtos.televisionDtos.TelevisionResponseDTO;
import nl.novi.techiteasy.services.TelevisionService;
import nl.novi.techiteasy.services.TelevisionWallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/televisions")
public class TelevisionsController {

    private final TelevisionService televisionService;
    private final TelevisionWallBracketService televisionWallBracketService;

    public TelevisionsController(TelevisionService televisionService, TelevisionWallBracketService televisionWallBracketService) {
        this.televisionService = televisionService;
        this.televisionWallBracketService = televisionWallBracketService;
    }

    @PostMapping
    public ResponseEntity<Object> createTelevision(@Valid @RequestBody TelevisionCreateDTO televisionCreateDTO) {
        TelevisionResponseDTO responseDTO = televisionService.createTelevision(televisionCreateDTO);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<TelevisionResponseDTO>> getTelevisions(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Double availableSize,
            @RequestParam(required = false) String screenType,
            @RequestParam(required = false) String screenQuality,
            @RequestParam(required = false) Boolean smartTv,
            @RequestParam(required = false) Boolean wifi,
            @RequestParam(required = false) Boolean voiceControl,
            @RequestParam(required = false) Boolean hdr,
            @RequestParam(required = false) Boolean bluetooth,
            @RequestParam(required = false) Boolean ambiLight,
            @RequestParam(required = false) Integer originalStock,
            @RequestParam(required = false) Integer sold,
            @RequestParam(required = false) String soldAt,
            @RequestParam(required = false) String boughtAt) {

        List<TelevisionResponseDTO> televisions = televisionService.getTelevisionsWithFilters(
                brand, name, type, price, availableSize, screenType, screenQuality,
                smartTv, wifi, voiceControl, hdr, bluetooth, ambiLight,
                originalStock, sold, soldAt, boughtAt);

        return ResponseEntity.ok(televisions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionResponseDTO> getTelevision(@PathVariable Long id) {
        TelevisionResponseDTO responseDTO = televisionService.getTelevisionById(id);
        return ResponseEntity.ok(responseDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(
            @PathVariable Long id,
            @Valid @RequestBody TelevisionCreateDTO televisionCreateDTO) {
        TelevisionResponseDTO responseDTO = televisionService.updateTelevision(id, televisionCreateDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/remoteController")
    public ResponseEntity<Void> assignRemoteControllerToTelevision(
            @PathVariable Long id,
            @RequestBody IdInputDTO remoteControllerIdInput) {
        televisionService.assignRemoteControllerToTelevision(id, remoteControllerIdInput.getId());
        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}/cimodule")
    public ResponseEntity<Void> assignCiModule(
            @PathVariable Long id,
            @RequestBody IdInputDTO ciModuleIdInput) {
        televisionService.assignCiModuleToTelevision(id, ciModuleIdInput.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/wallbrackets")
    public ResponseEntity<Collection<WallBracketDTO>> getWallBracketsByTelevisionId(@PathVariable Long id) {
        Collection<WallBracketDTO> wallBrackets = televisionWallBracketService.getWallBracketsByTelevisionId(id);
        return ResponseEntity.ok(wallBrackets);
    }

    @PutMapping("/{id}/wallbrackets/{wallBracketId}")
    public ResponseEntity<Long> assignWallBracketToTelevision(
            @PathVariable Long id,
            @PathVariable Long wallBracketId) {
        var AssignedId = televisionWallBracketService.addTelevisionWallBracket(id, wallBracketId);
        return ResponseEntity.ok(AssignedId);
    }
}
