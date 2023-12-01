package hu.NeptunFrontend.services;

import hu.NeptunFrontend.domain.ClassRoom;
import hu.NeptunFrontend.domain.ClassRoomList;
import hu.NeptunFrontend.domain.Department;
import hu.NeptunFrontend.domain.DepartmentList;
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
public class DepartmentService {

    @Autowired
    private RestTemplate restTemplate;
    private final String API_URL = "http://localhost:8095";

    public List<DepartmentList> getDepartmentList() {
        String url = API_URL+"/departments";
        DepartmentList[] departmentLists = restTemplate.getForObject(url,DepartmentList[].class);
        return Arrays.asList(departmentLists);
    }

    public Department getlDepartment(int ID) {
        String url = API_URL+"/department/{ID}";
        Department department = restTemplate.getForObject(url, Department.class, ID	);
        return department;
    }

    public int addDepartment(String name) {
        String url = API_URL+"/departments/add";
        Department department = new Department(name);
        HttpEntity<Department> requestEntity = new HttpEntity<>(department);
        try {
            ResponseEntity<Department> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Department.class);
            return responseEntity.getStatusCodeValue();
        } catch(HttpClientErrorException ex){
            return ex.getStatusCode().value(); // conflict ( létező start number)
        }

    }

    public int updateDepartment(int ID, String name) {
        String url = API_URL+"/department/updatename/{ID}";
        Department department = new Department(ID,name);

        // az alábbi két sorral állítjuk be a restTemplate példányt arra, hogy tudja kezelni a patch kérést
        // ezért kellett a httpclient dependency a pom.xml-be
        CloseableHttpClient client = HttpClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));

        HttpEntity<Department> requestEntity = new HttpEntity<>(department);
        ResponseEntity<Department> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Department.class, ID);
        return responseEntity.getStatusCodeValue();
    }

    public int deleteDepartment(int ID) {
        System.out.println("Service: "+(ID-1));
        String url = API_URL+"/department/delete/{ID}";
        restTemplate.delete(url,ID);
        System.out.println("return 100");
        return 100;
    }
}
