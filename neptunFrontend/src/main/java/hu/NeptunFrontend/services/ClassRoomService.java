package hu.NeptunFrontend.services;

import hu.NeptunFrontend.domain.ClassRoom;
import hu.NeptunFrontend.domain.ClassRoomList;
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
public class ClassRoomService {

    @Autowired
    private RestTemplate restTemplate;
    private final String API_URL = "http://localhost:8095";

    public List<ClassRoomList> getClassRoomList() {
        String url = API_URL+"/classrooms";
        ClassRoomList[] classRoomLists = restTemplate.getForObject(url, ClassRoomList[].class);
        return Arrays.asList(classRoomLists);
    }

    public ClassRoom getClassRoom(int ID) {
        String url = API_URL+"/classroom/{ID}";
        ClassRoom classroom = restTemplate.getForObject(url, ClassRoom.class, ID	);
        return classroom;
    }

    public int addClassRoom(String door,int space) {
        String url = API_URL+"/classrooms/add";
        ClassRoom classroom = new ClassRoom(door,space);
        HttpEntity<ClassRoom> requestEntity = new HttpEntity<>(classroom);
        try {
            ResponseEntity<ClassRoom> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ClassRoom.class);
            return responseEntity.getStatusCodeValue();
        } catch(HttpClientErrorException ex){
            return ex.getStatusCode().value(); // conflict ( létező start number)
        }

    }

    public int updateClassRoom(int ID, int space) {
        String url = API_URL+"/classrooms/updatespace/{ID}";
        ClassRoom classroom = new ClassRoom(ID,space);

        // az alábbi két sorral állítjuk be a restTemplate példányt arra, hogy tudja kezelni a patch kérést
        // ezért kellett a httpclient dependency a pom.xml-be
        CloseableHttpClient client = HttpClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));

        HttpEntity<ClassRoom> requestEntity = new HttpEntity<>(classroom);
        ResponseEntity<ClassRoom> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, ClassRoom.class, ID);
        return responseEntity.getStatusCodeValue();
    }

    public int deleteClassRoom(int ID) {
        System.out.println("Service: "+(ID-1));
        String url = API_URL+"/classrooms/delete/{ID}";
        restTemplate.delete(url,ID);
        System.out.println("return 100");
        return 100;
    }
}
