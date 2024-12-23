package nl.novi.techiteasy.services;

import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.models.WallBracket;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import nl.novi.techiteasy.repositories.WallBracketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class TelevisionWallBracketServiceTest {

    private final TelevisionRepository televisionRepository = Mockito.mock(TelevisionRepository.class);
    private final WallBracketRepository wallBracketRepository = Mockito.mock(WallBracketRepository.class);
    private final TelevisionWallBracketService wallBracketService = new TelevisionWallBracketService(televisionRepository, wallBracketRepository);

    @Test
    void testAddTelevisionWallBracket() {
        Television television = new Television();
        television.setId(1L);

        WallBracket wallBracket = new WallBracket();
        wallBracket.setId(1L);

        Mockito.when(televisionRepository.findById(1L)).thenReturn(Optional.of(television));
        Mockito.when(wallBracketRepository.findById(1L)).thenReturn(Optional.of(wallBracket));
        Mockito.when(wallBracketRepository.save(ArgumentMatchers.any(WallBracket.class))).thenReturn(wallBracket);

        Long result = wallBracketService.addTelevisionWallBracket(1L, 1L);

        assertNotNull(result);
        assertEquals(1L, result);
        Mockito.verify(wallBracketRepository, times(1)).save(wallBracket);
        // print the result
        System.out.println(result);
    }

}