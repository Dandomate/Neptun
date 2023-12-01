package hu.NeptunFrontend.controllers;

import hu.NeptunFrontend.config.Counter;
import hu.NeptunFrontend.domain.*;
import hu.NeptunFrontend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TimeTableService timetableService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("activemenu", 1);
        return "index";
    }

    @GetMapping("/c-students")
    public String getStudentList(Model model){
        List<StudentList> studentList = studentService.getStudentList();
        if(studentList.size()>0) {
            model.addAttribute("counter", new Counter());
            model.addAttribute("studentlist", studentList);
            model.addAttribute("name", studentList.get(0).getID());
        }
        model.addAttribute("activemenu", "2");
        return "studentlist";
    }




//!!!!
    /*
    @GetMapping("/c-timetablelists")
    public String getStudentClass(Model model){
        List<StudentClassList> studentClassList = studentClassService.getStudentClassList();
        if(studentClassList.size()>0) {
            model.addAttribute("counter", new Counter());
            model.addAttribute("studentclass", studentClassList);
            model.addAttribute("cname", studentClassList.get(0).getCLASS_ID());
        }
        model.addAttribute("activemenu", "7");
        return "studentclass";
    }

     */
//!!
/*
    @GetMapping("/c-timetablelists/{CLASS_ID}/{day}")
    public String getTimeTableList(@PathVariable("CLASS_ID") int CLASS_ID,@PathVariable("day") String day,
                                 Model model){
        List<TimeTableList> timetablelist = timetableService.getTimeTableList(CLASS_ID,day);
        model.addAttribute("timetablelists", timetablelist);
        model.addAttribute("activemenu", 7);
        return "timetablelists";
    }


 */


    @GetMapping("/c-classrooms")
    public String getClassRoomList(Model model){
        List<ClassRoomList> classRoomList = classRoomService.getClassRoomList();
        if(classRoomList.size()>0) {
            model.addAttribute("counter", new Counter());
            model.addAttribute("classroomlist", classRoomList);
            model.addAttribute("door", classRoomList.get(0).getID());
        }
        model.addAttribute("activemenu", "3");
        return "roomlist";
    }

@GetMapping("/c-departments")
    public String getDepartmentList(Model model){
        List<DepartmentList> departmentList = departmentService.getDepartmentList();
        if(departmentList.size()>0) {
            model.addAttribute("counter", new Counter());
            model.addAttribute("departmentlist", departmentList);
            model.addAttribute("name", departmentList.get(0).getID());
        }
        model.addAttribute("activemenu", "6");
        return "departmentlist";
    }

    @GetMapping("/c-equipments")
    public String getEquipmentList(Model model){
        List<EquipmentList> equipmentList = equipmentService.getEquipmentList();
        if(equipmentList.size()>0) {
            model.addAttribute("counter", new Counter());
            model.addAttribute("equipmentlist", equipmentList);
            model.addAttribute("designation", equipmentList.get(0).getID());
        }
        model.addAttribute("activemenu", "5");
        return "equipmentlist";
    }


    @GetMapping("/c-teachers")
    public String Teachers(Model model){
        //System.out.println(teacherService.getTeachers());
        model.addAttribute("teachers",teacherService.getTeachers());
        model.addAttribute("activemenu", 4);
        return "teachers";
    }


    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }








}
