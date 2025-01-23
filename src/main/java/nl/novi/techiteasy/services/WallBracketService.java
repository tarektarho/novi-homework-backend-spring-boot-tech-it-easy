package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.WallBracketDTO;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.mappers.WallBracketMapper;
import nl.novi.techiteasy.models.WallBracket;
import nl.novi.techiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    public List<WallBracketDTO> getAllWallBrackets() {
        List<WallBracket> wallBracketList = wallBracketRepository.findAll();
        List<WallBracketDTO> dtos = new ArrayList<>();
        for (WallBracket wb : wallBracketList) {
            dtos.add(WallBracketMapper.toResponseDTO(wb));
        }
        return dtos;
    }

    public WallBracketDTO getWallBracketById(long id) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);
        if(wallBracket.isPresent()) {
            return WallBracketMapper.toResponseDTO(wallBracket.get());
        } else {
            throw new RecordNotFoundException("No wallbracket found with id " + id);
        }
    }

    public WallBracketDTO addWallBracket(WallBracketDTO wallBracketDto) {
        WallBracket wallBracket = WallBracketMapper.toEntity(wallBracketDto);
        wallBracketRepository.save(wallBracket);
        return WallBracketMapper.toResponseDTO(wallBracket);
    }


    public void deleteWallBracket(long id) {
        wallBracketRepository.deleteById(id);
    }

    public WallBracketDTO updateWallBracket(long id, WallBracketDTO wallBracketDTO) {
        if(!wallBracketRepository.existsById(id)) {
            throw new RuntimeException("WallBracket not found with id " + id);
        }
        WallBracket storedWallBracket = wallBracketRepository.findById(id).orElse(null);
        storedWallBracket.setId(wallBracketDTO.getId());
        storedWallBracket.setSize(wallBracketDTO.getSize());
        storedWallBracket.setAdjustable(wallBracketDTO.getAdjustable());
        storedWallBracket.setName(wallBracketDTO.getName());
        storedWallBracket.setPrice(wallBracketDTO.getPrice());



        return WallBracketMapper.toResponseDTO(wallBracketRepository.save(storedWallBracket));
    }
}
