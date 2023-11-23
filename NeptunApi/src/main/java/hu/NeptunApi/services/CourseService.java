package hu.NeptunApi.services;

import hu.NeptunApi.domain.Course;

import hu.NeptunApi.repositories.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
@Autowired
    private  CourseRepository repository;


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
/*
    public Map<String, String> getTeacherDetails(int ID) {
        Teacher teacher = repository.findById(ID).orElse(null);

        if (teacher != null) {
            Map<String, String> details = new HashMap<>();
            details.put("teacherName", teacher.getName());

            if (teacher.getDepartment() != null) {
                details.put("departmentName", teacher.getDepartment().getName());
            }

            return details;
        } else {
            return null;
        }
    }




 */

    //add teacher, update teacher department, remove teacher department.....
}
