package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.WallBracketDTO;
import nl.novi.techiteasy.dtos.televisionDtos.TelevisionResponseDTO;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.mappers.TelevisionMapper;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.models.TelevisionWallBracket;
import nl.novi.techiteasy.models.TelevisionWallBracketKey;
import nl.novi.techiteasy.models.WallBracket;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import nl.novi.techiteasy.repositories.TelevisionWallBracketRepository;
import nl.novi.techiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.*;

// Deze klasse bevat de service methodes van TelevisionWallBracketController.
// Deze klasse wijkt af van de andere service-klassen, omdat deze in 3 verschillende controllers wordt ge-autowired.

@Service
public class TelevisionWallBracketService{

    private TelevisionRepository televisionRepository;

    private WallBracketRepository wallBracketRepository;

    private TelevisionWallBracketRepository televisionWallBracketRepository;

    public TelevisionWallBracketService(TelevisionRepository televisionRepository, WallBracketRepository wallBracketRepository, TelevisionWallBracketRepository televisionWallBracketRepository) {
        this.televisionRepository = televisionRepository;
        this.wallBracketRepository = wallBracketRepository;
        this.televisionWallBracketRepository = televisionWallBracketRepository;
    }

    public Collection<TelevisionResponseDTO> getTelevisionsByWallBracketId(Long wallBracketId) {
        Collection<TelevisionResponseDTO> dtos = new HashSet<>();
        Collection<TelevisionWallBracket> televisionWallbrackets = televisionWallBracketRepository.findAllByWallBracketId(wallBracketId);
        for (TelevisionWallBracket televisionWallbracket : televisionWallbrackets) {
            Television television = televisionWallbracket.getTelevision();
            TelevisionResponseDTO televisionDto = new TelevisionResponseDTO();

            televisionDto.setId(television.getId());
            televisionDto.setType(television.getType());
            televisionDto.setBrand(television.getBrand());
            televisionDto.setName(television.getName());
            televisionDto.setPrice(television.getPrice());
            televisionDto.setAvailableSize(television.getAvailableSize());
            televisionDto.setRefreshRate(television.getRefreshRate());
            televisionDto.setScreenType(television.getScreenType());
            televisionDto.setScreenQuality(television.getScreenQuality());
            televisionDto.setOriginalStock(television.getOriginalStock());
            televisionDto.setSold(television.getSold());

            dtos.add(televisionDto);
        }
        return dtos;
    }

    // Collection is de super klasse van zowel List als Set.
    public Collection<WallBracketDTO> getWallBracketsByTelevisionId(Long televisionId) {
        //We gebruiken hier Set om te voorkomen dat er dubbele entries in staan.
        Set<WallBracketDTO> dtos = new HashSet<>();
        List<TelevisionWallBracket> televisionWallbrackets = televisionWallBracketRepository.findAllByTelevisionId(televisionId);
        for (TelevisionWallBracket televisionWallbracket : televisionWallbrackets) {
            WallBracket wallBracket = televisionWallbracket.getWallBracket();
            var dto = new WallBracketDTO();

            dto.setId(wallBracket.getId());
            dto.setName(wallBracket.getName());
            dto.setSize(wallBracket.getSize());
            dto.setAdjustable(wallBracket.getAdjustable());
            dto.setPrice(wallBracket.getPrice());

            dtos.add(dto);
        }
        return dtos;
    }


    public TelevisionWallBracketKey addTelevisionWallBracket(Long televisionId, Long wallBracketId) {
        var televisionWallBracket = new TelevisionWallBracket();
        if (!televisionRepository.existsById(televisionId)) {throw new RecordNotFoundException();}
        Television television = televisionRepository.findById(televisionId).orElse(null);
        if (!wallBracketRepository.existsById(wallBracketId)) {throw new RecordNotFoundException();}
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElse(null);
        televisionWallBracket.setTelevision(television);
        televisionWallBracket.setWallBracket(wallBracket);
        TelevisionWallBracketKey id = new TelevisionWallBracketKey(televisionId, wallBracketId);
        televisionWallBracket.setId(id);
        televisionWallBracketRepository.save(televisionWallBracket);
        return id;
    }
}
