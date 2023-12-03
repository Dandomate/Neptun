package hu.NeptunApi.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Test
    public void testHome() {
        // Arrange
        Model model = mock(Model.class);

        // Act
        String viewName = homeController.home();

        // Assert
        assertEquals("index", viewName);
    }
}