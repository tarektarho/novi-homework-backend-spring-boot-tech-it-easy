package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.dtos.WallBracketDTO;
import nl.novi.techiteasy.dtos.televisionDtos.TelevisionResponseDTO;
import nl.novi.techiteasy.services.TelevisionWallBracketService;
import nl.novi.techiteasy.services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/wallbrackets")
public class WallBracketController {
    private final WallBracketService wallBracketService;
    private final TelevisionWallBracketService televisionWallBracketService;

    public WallBracketController(WallBracketService wallBracketService, TelevisionWallBracketService televisionWallBracketService) {
        this.wallBracketService = wallBracketService;
        this.televisionWallBracketService = televisionWallBracketService;
    }

    @GetMapping()
    public ResponseEntity<List<WallBracketDTO>> getAllWallBrackets() {
        List<WallBracketDTO> wallBrackets = wallBracketService.getAllWallBrackets();
        return ResponseEntity.ok(wallBrackets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDTO> getWallBracket(@PathVariable("id") Long id) {
        WallBracketDTO wallBracketDTO = wallBracketService.getWallBracketById(id);
        return ResponseEntity.ok(wallBracketDTO);
    }

    @PostMapping()
    public ResponseEntity<WallBracketDTO> addWallBracket(@RequestBody WallBracketDTO dto) {
        WallBracketDTO wallBracket = wallBracketService.addWallBracket(dto);
        return ResponseEntity.created(null).body(wallBracket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable("id") Long id) {
        wallBracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<WallBracketDTO> updateWallBracket(@PathVariable("id") Long id, @RequestBody WallBracketDTO dto) {
        WallBracketDTO returnDto = wallBracketService.updateWallBracket(id, dto);
        return ResponseEntity.ok(returnDto);
    }

    //This method retrieves all televisions that are linked to a specific wall bracket.
    @GetMapping("/{wallBracketId}/televisions")
    public ResponseEntity<Collection<TelevisionResponseDTO>> getTelevisionsByWallBracketId(@PathVariable Long wallBracketId) {
        Collection<TelevisionResponseDTO> dtos = televisionWallBracketService.getTelevisionsByWallBracketId(wallBracketId);
        return ResponseEntity.ok(dtos);
    }
}
