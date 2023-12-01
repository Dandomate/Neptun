package hu.NeptunFrontend.services;

import hu.NeptunFrontend.domain.Student;
import hu.NeptunFrontend.domain.StudentList;
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

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private RestTemplate restTemplate;
    private final String API_URL = "http://localhost:8095";

    public List<StudentList> getStudentList() {
        String url = API_URL+"/students";
        StudentList[] studentLists = restTemplate.getForObject(url, StudentList[].class);
        return Arrays.asList(studentLists);
    }

    public Student getStudent(int ID) {
        String url = API_URL+"/student/{ID}";
        Student student = restTemplate.getForObject(url, Student.class,ID);
        return student;
    }


    public int addStudent(String name,String birth_date,String neptun_code) {
        String url = API_URL+"/students/add";
        Student student = new Student(name,birth_date,neptun_code);
        HttpEntity<Student> requestEntity = new HttpEntity<>(student);
        try {
            ResponseEntity<Student> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Student.class);
            return responseEntity.getStatusCodeValue();
        } catch(HttpClientErrorException ex){
            return ex.getStatusCode().value(); // conflict ( létező start number)
        }

    }

    public int updateStudent(int ID, String name,String birth_date,String neptun_code) {
        String url = API_URL+"/student/update/{ID}";
        Student student = new Student(ID,name,birth_date,neptun_code);

        // az alábbi két sorral állítjuk be a restTemplate példányt arra, hogy tudja kezelni a patch kérést
        // ezért kellett a httpclient dependency a pom.xml-be
        CloseableHttpClient client = HttpClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));

        HttpEntity<Student> requestEntity = new HttpEntity<>(student);
        ResponseEntity<Student> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Student.class, ID);
        return responseEntity.getStatusCodeValue();
    }



    public int deleteStudent(int ID) {
        System.out.println("Service: "+(ID/*-1*/));
        String url = API_URL+"/students/delete/{ID}";
        restTemplate.delete(url, ID);
        System.out.println("return 100");
        return 100;
    }
}
