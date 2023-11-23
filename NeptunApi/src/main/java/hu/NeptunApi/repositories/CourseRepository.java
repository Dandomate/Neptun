package hu.NeptunApi.repositories;


import hu.NeptunApi.domain.Course;
import hu.NeptunApi.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    //public List<Teacher> findAllByOrderByName();

    @Query(value = "SELECT * FROM Course ORDER BY ID ASC;", nativeQuery = true)
    public List<Course> nativeCoursers();

    @Query(nativeQuery = true, value = "SELECT * FROM course")
    List<Course> nativeFindCourse();
}