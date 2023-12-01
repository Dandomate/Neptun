package hu.NeptunApi.repositories;


import hu.NeptunApi.domain.Course;
import hu.NeptunApi.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    //public List<Teacher> findAllByOrderByName();

    @Query(value = "SELECT * FROM Course ORDER BY ID ASC;", nativeQuery = true)
    public List<Course> nativeCoursers();

    @Query(nativeQuery = true, value = "SELECT * FROM course")
    List<Course> nativeFindCourse();

    @Query(value = "SELECT course.ID AS course_id, course.name AS course_name, course.description AS course_description, course.day AS course_day, equipment.designation AS equipment_designation, classroom.door AS classroom_door, teacher.name AS teacher_name, student.name AS student_name FROM course INNER JOIN classroom ON classroom.ID = course.classroom_ID INNER JOIN equipment ON equipment.ID = course.equipment_ID INNER JOIN teacher ON teacher.ID = course.teacher_ID INNER JOIN student ON student.ID = course.student_ID WHERE student.neptun_code = :neptun_code AND course.day = :days ORDER BY student.name;", nativeQuery = true)
    List<Object[]> getStudentCourseList(@Param("neptun_code") String neptun_code, @Param("days") String days);

    @Query(value = "SELECT course.ID AS course_id, course.name AS course_name, course.description AS course_description, course.day AS course_day, equipment.designation AS equipment_designation, classroom.door AS classroom_door, teacher.name AS teacher_name, student.name AS student_name FROM course INNER JOIN classroom ON classroom.ID = course.classroom_ID INNER JOIN equipment ON equipment.ID = course.equipment_ID INNER JOIN teacher ON teacher.ID = course.teacher_ID INNER JOIN student ON student.ID = course.student_ID WHERE teacher.neptun_code = :neptun_code AND course.day = :days ORDER BY student.name;", nativeQuery = true)
    List<Object[]> getTeacherCourseList(@Param("neptun_code") String neptun_code, @Param("days") String days);
}