package hu.NeptunApi.dto;

import hu.NeptunApi.domain.ClassRoom;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class NewClassRoomRequestTest {
    @Test
    public void testToClassRoom() {
        // Arrange
        NewClassRoomRequest request = new NewClassRoomRequest();
        request.setDoor("TestDoor");
        request.setSpace(42);

        // Act
        ClassRoom classRoom = request.toClassRoom();

        // Assert
        assertThat(classRoom).isNotNull();
        assertThat(classRoom.getDoor()).isEqualTo("TestDoor");
        assertThat(classRoom.getSpace()).isEqualTo(42);
    }
}