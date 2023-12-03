package hu.NeptunApi.dto;


import hu.NeptunApi.domain.Department;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NewDepartmentRequestTest {

    @Test
    public void testToDepartment() {
        // Arrange
        NewDepartmentRequest request = new NewDepartmentRequest();
        request.setName("TestDepartment");

        // Act
        Department department = request.toDepartment();

        // Assert
        assertThat(department).isNotNull();
        assertThat(department.getName()).isEqualTo("TestDepartment");
    }
}