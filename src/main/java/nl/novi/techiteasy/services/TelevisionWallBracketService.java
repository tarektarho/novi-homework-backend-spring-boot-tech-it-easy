package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.WallBracketDTO;
import nl.novi.techiteasy.dtos.televisionDtos.TelevisionResponseDTO;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.mappers.TelevisionMapper;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.models.WallBracket;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import nl.novi.techiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class TelevisionWallBracketService {
    private final TelevisionRepository televisionRepository;
    private final WallBracketRepository wallBracketRepository;

    public TelevisionWallBracketService(TelevisionRepository televisionRepository, WallBracketRepository wallBracketRepository) {
        this.televisionRepository = televisionRepository;
        this.wallBracketRepository = wallBracketRepository;
    }

    public Collection<TelevisionResponseDTO> getTelevisionsByWallBracketId(Long wallBracketId) {
        Collection<TelevisionResponseDTO> dtos = new HashSet<>();
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElseThrow(() -> {
            throw new RecordNotFoundException();
        });
        for (Television television : wallBracket.getTelevisions()) {
            dtos.add(TelevisionMapper.toResponseDTO(television));
        }
        return dtos;
    }

    public Collection<WallBracketDTO> getWallBracketsByTelevisionId(Long televisionId) {
        List<WallBracketDTO> dtos = new ArrayList<>();
        Television television = televisionRepository.findById(televisionId).orElseThrow(() -> {
            throw new RecordNotFoundException();
        });
        for (WallBracket wallBracket : television.getWallBrackets()) {
            WallBracketDTO dto = new WallBracketDTO();

            dto.setId(wallBracket.getId());
            dto.setName(wallBracket.getName());
            dto.setSize(wallBracket.getSize());
            dto.setAdjustable(wallBracket.getAdjustable());
            dto.setPrice(wallBracket.getPrice());

            dtos.add(dto);
        }
        return dtos;
    }

    public Long addTelevisionWallBracket(Long televisionId, Long wallBracketId) {
        Television television = televisionRepository.findById(televisionId).orElseThrow(() -> {
            throw new RecordNotFoundException();
        });
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElseThrow(() -> {
            throw new RecordNotFoundException();
        });

        wallBracket.getTelevisions().add(television);
        television.getWallBrackets().add(wallBracket);

        televisionRepository.save(television);
        return wallBracketRepository.save(wallBracket).getId();
    }
}
