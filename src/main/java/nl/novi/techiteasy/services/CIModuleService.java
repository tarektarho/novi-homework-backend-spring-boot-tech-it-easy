package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.CIModuleDTO;
import nl.novi.techiteasy.mappers.CIModuleMapper;
import nl.novi.techiteasy.models.CIModule;
import nl.novi.techiteasy.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CIModuleService {

    private CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<CIModuleDTO> getAllCIModules() {
        List<CIModule> ciModules = ciModuleRepository.findAll();
        List<CIModuleDTO> dtos = new ArrayList<>();
        for (CIModule ci : ciModules) {
            dtos.add(CIModuleMapper.toRequestDTO(ci));
        }
        return dtos;
    }

    public CIModuleDTO getCIModuleById(Long id) {
        CIModule ciModule = ciModuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CIModule not found with id " + id));
        return CIModuleMapper.toRequestDTO(ciModule);
    }

    public CIModuleDTO saveCIModule(CIModuleDTO ciModuleDTO) {
        CIModule ciModule = CIModuleMapper.toEntity(ciModuleDTO);
        CIModule savedCIModule = ciModuleRepository.save(ciModule);
        return CIModuleMapper.toRequestDTO(savedCIModule);
    }

    public CIModuleDTO updateCIModule(Long id, CIModuleDTO ciModuleDTO) {
        CIModule ciModule = ciModuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CIModule not found with id " + id));
        CIModule updatedCIModule = CIModuleMapper.toEntity(ciModuleDTO);
        updatedCIModule.setId(ciModule.getId());
        CIModule savedCIModule = ciModuleRepository.save(updatedCIModule);
        return CIModuleMapper.toRequestDTO(savedCIModule);
    }

    public void deleteCIModuleById(Long id) {
        CIModule ciModule = ciModuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CIModule not found with id " + id));
        ciModuleRepository.delete(ciModule);
    }

}
