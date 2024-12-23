package nl.novi.techiteasy.repositories;

import nl.novi.techiteasy.models.RemoteController;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {
}
