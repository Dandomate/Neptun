package hu.NeptunFrontend.services;

import hu.NeptunFrontend.domain.AdminTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminTeacherService {

    @Autowired
    private RestTemplate restTemplate;
    private final String API_URL = "http://localhost:8095";

    public List<AdminTeacher> getAdminTeachers() {
        String url = API_URL + "/adminteachers";
        AdminTeacher[] adminteachers = restTemplate.getForObject(url, AdminTeacher[].class);
        return Arrays.asList(adminteachers);
    }

    public AdminTeacher getAdminTeacher(long OMA_TEACHER) {
        String url = API_URL+"/adminteachers/{OMA_TEACHER}";
        AdminTeacher adminteacher = restTemplate.getForObject(url, AdminTeacher.class, OMA_TEACHER);
        return adminteacher;
    }
}
