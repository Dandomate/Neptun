package hu.NeptunFrontend.controllers;



import hu.NeptunFrontend.domain.StudentCourseList;
import hu.NeptunFrontend.services.CourseService;
import hu.NeptunFrontend.services.TeacherService;
import hu.NeptunFrontend.services.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/studenthome")
    public String home(Model model){
        model.addAttribute("activemenu", 1);
            return "studenthome";
    }

/*
    @GetMapping("/timetablelist/{OMA_TEACHER}/{day}")
    public String getTimeTableListTeacher(@PathVariable("OMA_TEACHER") long OMA_TEACHER, @PathVariable("day") String day,
                                   Model model){
        List<TimeTableList> timetablelist = timetableService.getTimeTableListTeacher(OMA_TEACHER,day);
        model.addAttribute("timetablelist", timetablelist);
        model.addAttribute("activemenu", 2);
        return "timetablelist";
    }


 */

    @GetMapping("/studentcourselist/{neptun_code}/{day}")
    public String getStudentCourseList(@PathVariable("neptun_code") String neptun_code, @PathVariable("day") String day,
                                          Model model){
        List<StudentCourseList> studentCourseList = courseService.getStudentCourseList(neptun_code,day);
        model.addAttribute("studentcourselist", studentCourseList);
        model.addAttribute("activemenu", 2);
        return "studentcourselist";
    }
    @GetMapping("/teachers/{ID}")
    public String Teachers(@PathVariable("ID") int ID,Model model){
        model.addAttribute("teacher",teacherService.getTeacher(ID));
        model.addAttribute("activemenu", 3);
        return "teacher";
    }


}
