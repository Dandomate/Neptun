package hu.NeptunFrontend.services;


import hu.NeptunFrontend.domain.*;
import hu.NeptunFrontend.domain.TeacherSubjectList;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private RestTemplate restTemplate;
    private final String API_URL = "http://localhost:8095";

    public List<Teacher> getTeachers() {
        String url = API_URL + "/teachers";
        Teacher[] teachers = restTemplate.getForObject(url, Teacher[].class);
        return Arrays.asList(teachers);
    }

    public Teacher getTeacher(int ID) {
        String url = API_URL+"/teachers/{ID}";
        Teacher teacher = restTemplate.getForObject(url, Teacher.class, ID);
        return teacher;
    }



/*
    public List<TeacherSubjectList> getTeacherSubjectList(long OMA_TEACHER) {
        String url = API_URL+"/teachersubjectlist/{OMA_TEACHER}";
        TeacherSubjectList[] list = restTemplate.getForObject(url, TeacherSubjectList[].class, OMA_TEACHER);
        return Arrays.asList(list);
    }


 */

    //ezt tuti javitani kell
    public int addTeacher(int ID, String name,String birth_date,String neptun_code ) {
        String url = API_URL+"/teachers/add";
        Teacher teacher = new Teacher(ID,name,birth_date,neptun_code);
        HttpEntity<Teacher> requestEntity = new HttpEntity<>(teacher);
        try {
            ResponseEntity<Teacher> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Teacher.class);
            return responseEntity.getStatusCodeValue();
        } catch(HttpClientErrorException ex){
            return ex.getStatusCode().value(); // conflict ( létező start number)
        }

    }

    public int updateTeacher(int ID,String name) {
        String url = API_URL+"/teachers/name/update/{ID}";
        Teacher teacher = new Teacher(ID,name);

        // az alábbi két sorral állítjuk be a restTemplate példányt arra, hogy tudja kezelni a patch kérést
        // ezért kellett a httpclient dependency a pom.xml-be
        CloseableHttpClient client = HttpClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));

        HttpEntity<Teacher> requestEntity = new HttpEntity<>(teacher);
        ResponseEntity<Teacher> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Teacher.class, ID);
        return responseEntity.getStatusCodeValue();
    }

    public int deleteTeacher(int ID) {
        System.out.println("Service: "+(ID-1));
        String url = API_URL+"/teachers/delete/{ID}";
        restTemplate.delete(url, ID);
        System.out.println("return 100");
        return 100;
    }

}