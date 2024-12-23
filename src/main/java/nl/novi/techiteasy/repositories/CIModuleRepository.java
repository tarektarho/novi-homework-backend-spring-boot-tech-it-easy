package nl.novi.techiteasy.repositories;

import nl.novi.techiteasy.models.CIModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CIModuleRepository extends JpaRepository<CIModule, Long> {
    Optional<CIModule> findByIdAndTelevisionsId(Long Id, Long televisionId);
}
