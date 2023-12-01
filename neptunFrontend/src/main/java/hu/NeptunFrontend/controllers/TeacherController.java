
package hu.NeptunFrontend.controllers;

import hu.NeptunFrontend.domain.*;
import hu.NeptunFrontend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService service;


    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private CourseService courseService;


    @GetMapping("/adminteacher") // menüpontból navigál
    public String newTeacher(Model model){
        model.addAttribute("teachers", service.getTeachers());
        return "adminteacher";
    }
/*
    @PostMapping("/addteacher") // űrlapon Add gombot megnyom
    public String addTeacher(@RequestParam("OMA_TEACHER") long OMA_TEACHER,
                            @RequestParam("Name") String Name,
                            @RequestParam("Phone") String Phone,
                             @RequestParam("Password") String Password,
                             @RequestParam("Admin") Boolean Admin,
                            Model model){
        int statusCode = service.addTeacher(OMA_TEACHER, Name, Phone,Password,Admin);
        model.addAttribute("status", statusCode);
        model.addAttribute("teachers", service.getTeachers());
        return "adminteacher";
    }

 */

    @PostMapping("updateteacher") // menüpontból navigál
    public String updatingTeacher(@RequestParam("ID") int ID, Model model){
        Teacher teacher = service.getTeacher(ID);
        model.addAttribute("teacher", teacher);
        return "updateteacher";
    }




    @PostMapping("/delete-teacher")
    public String deleteTeacher(@RequestParam("ID") int ID, Model model){
        System.out.println("@DeleteMapping: "+ID);
        int status = service.deleteTeacher(ID);
        System.out.println("status code: "+status);
        model.addAttribute("status", status);
        model.addAttribute("teachers", service.getTeachers());
        return "adminteacher";
    }

    //******************************************************************-

    @GetMapping("/adminequipment") // menüpontból navigál
    public String newEquipment(Model model){
        model.addAttribute("equipments",equipmentService.getEquipmentList());
        return "adminequipment";
    }

    @PostMapping("/addequipment") // űrlapon Add gombot megnyom
    public String addEquipment(
            @RequestParam("designation") String designation,
            @RequestParam("quantity") int quantity,
            @RequestParam("description") String description,
                                Model model){
        int statusCode = equipmentService.addEquipment(designation,quantity,description);
        model.addAttribute("status", statusCode);
        model.addAttribute("equipments", equipmentService.getEquipmentList());
        return "adminequipment";
    }


    @PostMapping("updateequipment") // menüpontból navigál
    public String updatingEquipment(@RequestParam("ID") int ID, Model model){
        Equipment equipment = equipmentService.getlEquipment(ID);
        model.addAttribute("equipment", equipment);
        return "updateequipment";
    }



    @PostMapping("/update-equipment") // nevet cserél
    public String updateEquipment(@RequestParam("ID") int ID,
                                   @RequestParam("designation") String designation,
                                   @RequestParam("quantity") int quantity,
                                   @RequestParam("description")String description,
                                   Model model){
        int status = equipmentService.updateEquipment(ID,designation,quantity,description);
        model.addAttribute("status", status);
        model.addAttribute("equipments", equipmentService.getEquipmentList());
        return "adminequipment";
    }



    @PostMapping("/delete-equipment")
    public String deleteEquipment(@RequestParam("ID") int ID, Model model){
        System.out.println("@DeleteMapping: "+ID);
        int status = equipmentService.deleteEquipment(ID);
        System.out.println("status code: "+status);
        model.addAttribute("status", status);
        model.addAttribute("equipments",equipmentService.getEquipmentList());
        return "adminequipment";
    }



    //******************************************************************-

    @GetMapping("/adminclassroom") // menüpontból navigál
    public String newClassRoom(Model model){
        model.addAttribute("classrooms", classRoomService.getClassRoomList());
        return "adminclassroom";
    }

    @PostMapping("/addclassroom") // űrlapon Add gombot megnyom
    public String addClassRoom(@RequestParam("space") int space,@RequestParam("door") String door,

                             Model model){
        int statusCode = classRoomService.addClassRoom(door,space);
        model.addAttribute("status", statusCode);
        model.addAttribute("classrooms", classRoomService.getClassRoomList());
        return "adminclassroom";
    }

    @PostMapping("updateclassroom") // menüpontból navigál
    public String updatingClassRoom(@RequestParam("ID") int ID, Model model){
        ClassRoom classroom = classRoomService.getClassRoom(ID);
        model.addAttribute("classroom", classroom);
        return "updateclassroom";
    }



    @PostMapping("/update-space")
    public String updateClassRoom(@RequestParam("ID") int ID,
                                @RequestParam("space") int space,
                                Model model){
        int status = classRoomService.updateClassRoom(ID,space);
        model.addAttribute("status", status);
        model.addAttribute("classrooms", classRoomService.getClassRoomList());
        return "adminclassroom";
    }

    @PostMapping("/delete-classroom")
    public String deleteClassRoom(@RequestParam("ID") int ID, Model model){
        System.out.println("@DeleteMapping: "+ID);
        int status = classRoomService.deleteClassRoom(ID);
        System.out.println("status code: "+status);
        model.addAttribute("status", status);
        model.addAttribute("classrooms",classRoomService.getClassRoomList());
        return "adminclassroom";
    }

    //******************************************************************-

    @GetMapping("/adminstudent") // menüpontból navigál
    public String newStudent(Model model){
        model.addAttribute("students",studentService.getStudentList());
        return "adminstudent";
    }

    @PostMapping("/addstudent") // űrlapon Add gombot megnyom
    public String addStudent(@RequestParam("name") String name,
                             @RequestParam("birth_date") String birth_date,
                             @RequestParam("neptun_code") String neptun_code,
                               Model model){
        int statusCode = studentService.addStudent(name,birth_date,neptun_code);
        model.addAttribute("status", statusCode);
        model.addAttribute("students", studentService.getStudentList());
        return "adminstudent";
    }


    @PostMapping("updatestudent") // menüpontból navigál
    public String updatingStudent(@RequestParam("ID") int ID, Model model){
        Student student = studentService.getStudent(ID);
        model.addAttribute("student", student);
        return "updatestudent";
    }



    @PostMapping("/update-student") // nevet cserél
    public String updateStudent(@RequestParam("ID") int ID,
                                  @RequestParam("name") String name,
                                  @RequestParam("birth_date") String birth_date,
                                  @RequestParam("neptun_code") String neptun_code,
                                  Model model){
        int status = studentService.updateStudent(ID,name,birth_date,neptun_code);
        model.addAttribute("status", status);
        model.addAttribute("students", studentService.getStudentList());
        return "adminstudent";
    }



    @PostMapping("/delete-student")
    public String deleteStudent(@RequestParam("ID") int ID, Model model){
        System.out.println("@DeleteMapping: "+ID);
        int status = studentService.deleteStudent(ID);
        System.out.println("status code: "+status);
        model.addAttribute("status", status);
        model.addAttribute("students",studentService.getStudentList());
        return "adminstudent";
    }

    //******************************************************************-
    @GetMapping("/admindepartment") // menüpontból navigál
    public String newDepartment(Model model){
        model.addAttribute("departments",departmentService.getDepartmentList());
        return "admindepartment";
    }

    @PostMapping("/adddepartment") // űrlapon Add gombot megnyom
    public String addDepartment(@RequestParam("name") String name,
                             Model model){
        int statusCode = departmentService.addDepartment(name);
        model.addAttribute("status", statusCode);
        model.addAttribute("departments", departmentService.getDepartmentList());
        return "admindepartment";
    }


    @PostMapping("updatedepartment") // menüpontból navigál
    public String updatingDepartment(@RequestParam("ID") int ID, Model model){
        Department department = departmentService.getlDepartment(ID);
        model.addAttribute("department", department);
        return "updatedepartment";
    }



    @PostMapping("/update-name") // nevet cserél
    public String updateDepartment(@RequestParam("ID") int ID,
                                @RequestParam("name") String name,
                                Model model){
        int status = departmentService.updateDepartment(ID,name);
        model.addAttribute("status", status);
        model.addAttribute("departments", departmentService.getDepartmentList());
        return "admindepartment";
    }



    @PostMapping("/delete-department")
    public String deleteDepartment(@RequestParam("ID") int ID, Model model){
        System.out.println("@DeleteMapping: "+ID);
        int status = departmentService.deleteDepartment(ID);
        System.out.println("status code: "+status);
        model.addAttribute("status", status);
        model.addAttribute("departments",departmentService.getDepartmentList());
        return "admindepartment";
    }


    //****************************************************************************
/*
    @GetMapping("/admintimetable") // menüpontból navigál
    public String newTimeTable(/*int CLASS_ID, String day,Model model){
       // model.addAttribute("timetables", timeTableService.getTimeTableList(CLASS_ID,day));
       // return "admintimetable";
        return "sajathiba";
    }

    @PostMapping("/addtimetable") // űrlapon Add gombot megnyom
        return "admintimetable";
    }


    @PostMapping("/updatetimetable") // menüpontból navigál
        return "updatetimetable";
    }



    @PostMapping("/update-timetable") // nevet cserél
        return "adminstimetable";
    }

    @PostMapping("/timetables/{TIMETABLE_ID}")
    public String deleteTimeTable(@RequestParam("TIMETABLE_ID") int TIMETABLE_ID, Model model){
        System.out.println("@DeleteMapping: "+TIMETABLE_ID);
        int status = studentclassService.deleteStudentClass(TIMETABLE_ID);
        System.out.println("status code: "+status);
        model.addAttribute("status", status);
        //model.addAttribute("timetables",timeTableService.getTimeTableList());
        return "admintimetable";
    }


    @GetMapping("/adminteachersubject") // menüpontból navigál
    public String newTeacherSubject(){
        // model.addAttribute("timetables", timeTableService.getTimeTableList(CLASS_ID,day));
        // return "admintimetable";
        return "sajathiba";
    }

    */


    @GetMapping("/teachercourselist/{neptun_code}/{day}")
    public String getTeacherCourseList(@PathVariable("neptun_code") String neptun_code, @PathVariable("day") String day,
                                       Model model){
        List<TeacherCourseList> teacherCourseList = courseService.getTeacherCourseList(neptun_code,day);
        model.addAttribute("teachercourselist", teacherCourseList);
        model.addAttribute("activemenu", 2);
        return "teachercourselist";
    }
}


