package hu.NeptunFrontend.services;

import hu.NeptunFrontend.domain.StudentCourseList;
import hu.NeptunFrontend.domain.Teacher;
import hu.NeptunFrontend.domain.TeacherCourseList;
import hu.NeptunFrontend.domain.TimeTableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Service
public class CourseService {
    @Autowired
    private RestTemplate restTemplate;
    private final String API_URL = "http://localhost:8095";
    public List<StudentCourseList> getStudentCourseList(String neptun_code, String day) {
        String url = API_URL+"//student/course/{neptun_code}/{day}";
        StudentCourseList[] list = restTemplate.getForObject(url, StudentCourseList[].class, neptun_code,day);
        return Arrays.asList(list);
    }

    public List<TeacherCourseList> getTeacherCourseList(String neptun_code, String day) {
        String url = API_URL+"//teacher/course/{neptun_code}/{day}";
        TeacherCourseList[] list = restTemplate.getForObject(url, TeacherCourseList[].class, neptun_code,day);
        return Arrays.asList(list);
    }
}
