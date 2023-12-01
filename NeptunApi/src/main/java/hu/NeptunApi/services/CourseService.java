package hu.NeptunApi.services;

import hu.NeptunApi.domain.*;

import hu.NeptunApi.dto.NewCourseRequest;

import hu.NeptunApi.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private  CourseRepository repository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private StudentRepository studentRepository;



    public List<Course> getCourse() {
        return repository.nativeFindCourse();
    }

    public Course getCourse(int ID) {
        Optional<Course> course = repository.findById(ID);
        if (course.isPresent())
            return course.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }


    public Course updateCourse(int ID, String name) {
        Optional<Course> optionalCourse = repository.findById(ID);
        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            course.setName(name);
            return repository.save(course);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    public void deleteCourse(int ID) {
        Optional<Course> optionalCourse = repository.findById(ID);
        if(optionalCourse.isPresent()){
            repository.deleteById(ID);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<StudentCourseList> getStudentCourseList(String neptun_code, String day) {
        List<StudentCourseList> result = new ArrayList<>();
        List<Object[]> studentCourseLists = repository.getStudentCourseList(neptun_code, day);
        for (Object[] tomb : studentCourseLists) {
            int ID = (Integer) tomb[0];
            String names = (String) tomb[1];
            String description = (String) tomb[2];
            String days = (String) tomb[3];
            String designation = (String) tomb[4];
            String door = (String) tomb[5];
            String teachername = (String) tomb[6];
            String studentname = (String) tomb[7];
            result.add(new StudentCourseList(ID, names, description, days, designation, door, teachername, studentname));
        }
        return result;
    }

    public List<TeacherCourseList> getTeacherCourseList(String neptun_code, String day) {
        List<TeacherCourseList> result = new ArrayList<>();
        List<Object[]> teacherCourseLists = repository.getTeacherCourseList(neptun_code, day);
        for (Object[] tomb : teacherCourseLists) {
            int ID = (Integer) tomb[0];
            String names = (String) tomb[1];
            String description = (String) tomb[2];
            String days = (String) tomb[3];
            String designation = (String) tomb[4];
            String door = (String) tomb[5];
            String teachername = (String) tomb[6];
            String studentname = (String) tomb[7];
            result.add(new TeacherCourseList(ID, names, description, days, designation, door, teachername, studentname));
        }
        return result;
    }


    @Transactional
    public void addCourse(NewCourseRequest newCourseRequest) {
        try {
            Equipment equipment = equipmentRepository.findById(newCourseRequest.getEquipment_ID())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found"));

            Teacher teacher = teacherRepository.findById(newCourseRequest.getTeacher_ID())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

            Student student = studentRepository.findById(newCourseRequest.getStudent_ID())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

            ClassRoom classroom = classRoomRepository.findById(newCourseRequest.getClassroom_ID())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom not found"));

            Course course = new Course();
            course.setName(newCourseRequest.getName());
            course.setDescription(newCourseRequest.getDescription());
            course.setDay(newCourseRequest.getDay());


            // Beállítjuk az entitások közötti kapcsolatokat
            course.setEquipment(equipment);
            course.setClassroom(classroom);
            course.setTeacher(teacher);
            course.setStudent(student);

            repository.save(course);

            equipment.getCourses().add(course);
            classroom.getCourses().add(course);
            teacher.getCourses().add(course);
            student.getCourses().add(course);

            equipmentRepository.save(equipment);
            classRoomRepository.save(classroom);
            teacherRepository.save(teacher);
            studentRepository.save(student);



        } catch (Exception e) {
            // Kezeljük a kivételeket, ha szükséges
            throw new RuntimeException("Hiba történt a kurzus hozzáadása közben.", e);
        }
    }
/*
    public Course updateCourseAll(int ID,String name,String description,String day, int equipment_ID,int classroom_ID,int teacher_ID,int student_ID) {
        Course course = repository.findById(ID)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + ID));
        if (name != null) {
            course.setName(name);
        }

        if (description != null) {
            course.setDescription(description);
        }

        if (day != null) {
            course.setDay(day);
        }
            if (equipment_ID !=0){
                Equipment newEquipment = equipmentRepository.findById(equipment_ID)
                        .orElseThrow(() -> new EntityNotFoundException("Equipment not found with ID: " + equipment_ID));
                course.setEquipment(newEquipment);
            }
            if(classroom_ID !=0) {
                ClassRoom newClassroom = classRoomRepository.findById(classroom_ID)
                        .orElseThrow(() -> new EntityNotFoundException("Classroom not found with ID: " + classroom_ID));
                course.setClassroom(newClassroom);
            }
            if (teacher_ID != 0) {
                Teacher newTeacher = teacherRepository.findById(teacher_ID)
                        .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " +teacher_ID));
                course.setTeacher(newTeacher);
            }
            if (student_ID !=0) {
                Student newStudent = studentRepository.findById((Integer) student_ID)
                        .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + student_ID));
                course.setStudent(newStudent);
            }

        return repository.save(course);
    }

 */
}
