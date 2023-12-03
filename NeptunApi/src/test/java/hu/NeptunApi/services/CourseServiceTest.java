package hu.NeptunApi.services;

import hu.NeptunApi.domain.*;
import hu.NeptunApi.dto.NewCourseRequest;
import hu.NeptunApi.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private ClassRoomRepository classRoomRepository;

    @Mock
    private StudentRepository studentRepository;

    @Test
    void testGetCourse() {
        // Szimuláljuk a repository findAll műveletét
        List<Course> mockCourses = new ArrayList<>();
        when(courseRepository.nativeFindCourse()).thenReturn(mockCourses);

        // Teszteljük a metódust
        List<Course> result = courseService.getCourse();

        // Ellenőrizzük, hogy a várt eredmény megfelel-e
        assertEquals(mockCourses, result);
    }

    @Test
    void testGetCourseById() {
        // Szimuláljuk egy kurzus lekérését az ID alapján
        int courseId = 1;
        Course mockCourse = new Course(courseId, "Course1", "Description1", "Monday");

        // A repository szimulálása
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(mockCourse));

        // Teszteljük a metódust
        Course result = courseService.getCourse(courseId);

        // Ellenőrizzük, hogy a várt eredmény megfelel-e
        assertEquals(courseId, result.getID());
        assertEquals("Course1", result.getName());
        assertEquals("Description1", result.getDescription());
        assertEquals("Monday", result.getDay());
    }



    @Test
    void testUpdateCourseName() {
        // Szimuláljuk egy kurzus módosítását
        int courseId = 1;
        String newName = "UpdatedCourse";

        Course mockCourse = new Course(courseId, "Course1", "Description1", "Monday");
        Course updatedCourse = new Course(courseId, newName, "Description1", "Monday");

        // A repository szimulálása
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(mockCourse));
        when(courseRepository.save(Mockito.any(Course.class))).thenReturn(updatedCourse);

        // Teszteljük a metódust
        Course result = courseService.updateCourse(courseId, newName);

        // Ellenőrizzük, hogy a várt eredmény megfelel-e
        assertEquals(courseId, result.getID());
        assertEquals(newName, result.getName());
        assertEquals("Description1", result.getDescription());
        assertEquals("Monday", result.getDay());
    }




    @Test
    public void testDeleteCourse() {
        // Arrange
        when(courseRepository.findById(1)).thenReturn(Optional.of(new Course()));

        // Act
        courseService.deleteCourse(1);

        // Assert
        verify(courseRepository, times(1)).deleteById(1);
    }

    @Test
    void testAddCourse() {
        // Szimuláljuk az új kurzus létrehozását
        NewCourseRequest newCourseRequest = new NewCourseRequest();
        newCourseRequest.setName("NewCourse");
        newCourseRequest.setDescription("NewDescription");
        newCourseRequest.setDay("NewDay");
        newCourseRequest.setEquipment_ID(1);
        newCourseRequest.setClassroom_ID(2);
        newCourseRequest.setTeacher_ID(3);
        newCourseRequest.setStudent_ID(4);

        // A repository szimulálása
        when(equipmentRepository.findById(newCourseRequest.getEquipment_ID())).thenReturn(Optional.of(new Equipment()));
        when(teacherRepository.findById(newCourseRequest.getTeacher_ID())).thenReturn(Optional.of(new Teacher()));
        when(studentRepository.findById(newCourseRequest.getStudent_ID())).thenReturn(Optional.of(new Student()));
        when(classRoomRepository.findById(newCourseRequest.getClassroom_ID())).thenReturn(Optional.of(new ClassRoom()));
        when(courseRepository.save(Mockito.any(Course.class))).thenAnswer(invocation -> {
            Course savedCourse = invocation.getArgument(0);
            return savedCourse;
        });

        // Teszteljük a metódust
        courseService.addCourse(newCourseRequest);

        // Ellenőrizzük, hogy a save metódus meghívódott-e
        verify(courseRepository, Mockito.times(1)).save(Mockito.any(Course.class));
    }

    @Test
    void testGetStudentCourseList() {
        // Szimuláljuk a repository getStudentCourseList műveletét
        List<Object[]> mockStudentCourseLists = new ArrayList<>();
        when(courseRepository.getStudentCourseList("neptun_code", "days")).thenReturn(mockStudentCourseLists);

        // Teszteljük a metódust
        List<StudentCourseList> result = courseService.getStudentCourseList("neptun_code", "days");

        // Ellenőrizzük, hogy a várt eredmény megfelel-e
        assertEquals(0, result.size());  // Tesztadattól függően itt módosítandó
    }

    @Test
    void testGetTeacherCourseList() {
        // Szimuláljuk a repository getTeacherCourseList műveletét
        List<Object[]> mockTeacherCourseLists = new ArrayList<>();
        when(courseRepository.getTeacherCourseList("neptun_code", "days")).thenReturn(mockTeacherCourseLists);

        // Teszteljük a metódust
        List<TeacherCourseList> result = courseService.getTeacherCourseList("neptun_code", "days");

        // Ellenőrizzük, hogy a várt eredmény megfelel-e
        assertEquals(0, result.size());  // Tesztadattól függően itt módosítandó
    }


}