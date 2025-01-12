package nl.novi.techiteasy.repositories;

import nl.novi.techiteasy.models.RemoteController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {
}
