package hu.NeptunApi.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hu.NeptunApi.domain.ClassRoom;
import hu.NeptunApi.domain.ClassRoomList;
import hu.NeptunApi.dto.NewClassRoomRequest;
import hu.NeptunApi.repositories.ClassRoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class ClassRoomServiceTest {

    @InjectMocks
    private ClassRoomService classRoomService;

    @Mock
    private ClassRoomRepository classRoomRepository;

    @Test
    void testGetClassRooms() {
        // Szimuláljuk az osztályszobák listáját, ami visszajön a repositoryból
        Object[] classRoom1Data = {1, "Door1", 30};
        Object[] classRoom2Data = {2, "Door2", 25};
        List<Object[]> mockData = Arrays.asList(classRoom1Data, classRoom2Data);

        // A repository szimulálása
        when(classRoomRepository.getClassRooms()).thenReturn(mockData);

        // Teszteljük a metódust
        List<ClassRoomList> result = classRoomService.getClassRooms();

        // Ellenőrizzük, hogy a várt eredmény megfelel-e
        assertEquals(2, result.size());

        ClassRoomList classRoomList1 = result.get(0);
        assertEquals(1, classRoomList1.getID());
        assertEquals("Door1", classRoomList1.getDoor());
        assertEquals(30, classRoomList1.getSpace());

        ClassRoomList classRoomList2 = result.get(1);
        assertEquals(2, classRoomList2.getID());
        assertEquals("Door2", classRoomList2.getDoor());
        assertEquals(25, classRoomList2.getSpace());
    }

    @Test
    void testGetClassRoomById() {
        // Szimuláljuk egy osztályszoba adatbázisból való lekérését
        int classRoomId = 1;
        ClassRoom mockClassRoom = new ClassRoom(classRoomId, "Door1", 30);

        // A repository szimulálása
        when(classRoomRepository.findById(classRoomId)).thenReturn(Optional.of(mockClassRoom));

        // Teszteljük a metódust
        ClassRoom result = classRoomService.getClassRoom(classRoomId);

        // Ellenőrizzük, hogy a várt eredmény megfelel-e
        assertEquals(classRoomId, result.getID());
        assertEquals("Door1", result.getDoor());
        assertEquals(30, result.getSpace());
    }

    @Test
    void testUpdateClassRoom() {
        // Szimuláljuk egy osztályszoba adatbázisból való lekérését és módosítását
        int classRoomId = 1;
        int newSpace = 40;

        ClassRoom mockClassRoom = new ClassRoom(classRoomId, "Door1", 30);
        ClassRoom updatedClassRoom = new ClassRoom(classRoomId, "Door1", newSpace);

        // A repository szimulálása
        when(classRoomRepository.findById(classRoomId)).thenReturn(Optional.of(mockClassRoom));
        when(classRoomRepository.save(Mockito.any(ClassRoom.class))).thenReturn(updatedClassRoom);

        // Teszteljük a metódust
        ClassRoom result = classRoomService.updateClassRoom(classRoomId, newSpace);

        // Ellenőrizzük, hogy a várt eredmény megfelel-e
        assertEquals(classRoomId, result.getID());
        assertEquals("Door1", result.getDoor());
        assertEquals(newSpace, result.getSpace());
    }

    @Test
    void testAddClassRoom() {
        // Szimuláljuk az új osztályszoba létrehozását
        NewClassRoomRequest newClassRoomRequest = new NewClassRoomRequest();
        newClassRoomRequest.setDoor("NewDoor");
        newClassRoomRequest.setSpace(35);

        // A repository szimulálása
        when(classRoomRepository.save(Mockito.any(ClassRoom.class))).thenAnswer(invocation -> {
            ClassRoom savedClassRoom = invocation.getArgument(0);
            savedClassRoom.setID(1);  // Szimulált ID beállítása
            return savedClassRoom;
        });

        // Teszteljük a metódust
        ClassRoom result = classRoomService.addClassRoom(newClassRoomRequest);

        // Ellenőrizzük, hogy a várt eredmény megfelel-e
        assertEquals(1, result.getID());  // Szimulált ID ellenőrzése
        assertEquals("NewDoor", result.getDoor());
        assertEquals(35, result.getSpace());

        // Ellenőrizzük, hogy a save metódus meghívódott-e
        verify(classRoomRepository, Mockito.times(1)).save(Mockito.any(ClassRoom.class));
    }

    @Test
    void testDeleteClassRoom() {
        // Szimuláljuk az osztályszoba meglétét
        ClassRoom existingClassRoom = new ClassRoom();
        existingClassRoom.setID(1);

        // Szimuláljuk a repository findById műveletét
        when(classRoomRepository.findById(1)).thenReturn(Optional.of(existingClassRoom));

        // Teszteljük a metódust
        classRoomService.deleteClassRoom(1);

        // Ellenőrizzük, hogy a repository deleteById metódusa meghívódott-e
        verify(classRoomRepository, Mockito.times(1)).deleteById(1);
    }
}