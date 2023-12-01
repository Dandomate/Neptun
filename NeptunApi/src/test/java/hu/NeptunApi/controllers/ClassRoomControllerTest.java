package hu.NeptunApi.controllers;

import hu.NeptunApi.domain.ClassRoom;
import hu.NeptunApi.domain.ClassRoomList;
import hu.NeptunApi.dto.NewClassRoomRequest;
import hu.NeptunApi.services.ClassRoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ClassRoomControllerTest {

    @Mock
    private ClassRoomService classRoomService;

    @InjectMocks
    private ClassRoomController classRoomController;

    @Test
    public void testAddClassRoom() {
        // Arrange
        NewClassRoomRequest newClassRoomRequest = new NewClassRoomRequest();

        // Act
        classRoomController.addClassRoom(newClassRoomRequest);

        // Assert
        verify(classRoomService, times(1)).addClassRoom(newClassRoomRequest);
    }

    @Test
    public void testUpdateClassRoom() {
        // Arrange
        int roomId = 1;
        ClassRoom updatedClassRoom = new ClassRoom();
        // Állítsd be a frissített osztályterem attribútumait szükség esetén

        // Act
        classRoomController.updateClassRoom(roomId, updatedClassRoom);

        // Assert
        verify(classRoomService, times(1)).updateClassRoom(eq(roomId), anyInt());
    }

    @Test
    public void testDeleteClassRoom() {
        // Arrange
        int roomId = 1;

        // Act
        classRoomController.deleteClassRoom(roomId);

        // Assert
        verify(classRoomService, times(1)).deleteClassRoom(eq(roomId));
    }

    @Test
    public void testGetClassRoom() {
        // Arrange
        int roomId = 1;
        ClassRoom expectedClassRoom = new ClassRoom(roomId, "ClassRoom1", 30);
        when(classRoomService.getClassRoom(roomId)).thenReturn(expectedClassRoom);

        // Act
        ClassRoom actualClassRoom = classRoomController.getClassRoom(roomId);

        // Assert
        assertEquals(expectedClassRoom, actualClassRoom);
    }

    @Test
    public void testGetClassRooms() {
        // Arrange
        List<ClassRoomList> expectedClassRooms = Arrays.asList(
                new ClassRoomList(),
                new ClassRoomList()
        );
        when(classRoomService.getClassRooms()).thenReturn(expectedClassRooms);

        // Act
        List<ClassRoomList> actualClassRooms = classRoomController.getClassRooms();

        // Assert
        assertEquals(expectedClassRooms, actualClassRooms);
    }
}