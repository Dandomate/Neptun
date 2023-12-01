package hu.NeptunFrontend.services;

import hu.NeptunFrontend.domain.*;
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
public class EquipmentService {

    @Autowired
    private RestTemplate restTemplate;
    private final String API_URL = "http://localhost:8095";

    public List<EquipmentList> getEquipmentList() {
        String url = API_URL+"/equipments";
        EquipmentList[] equipmentLists = restTemplate.getForObject(url,EquipmentList[].class);
        return Arrays.asList(equipmentLists);
    }

    public Equipment getlEquipment(int ID) {
        String url = API_URL+"/equipment/{ID}";
        Equipment equipment = restTemplate.getForObject(url, Equipment.class, ID	);
        return equipment;
    }

    public int addEquipment(String designation,int quantity,String description) {
        String url = API_URL+"/equipments/add";
        Equipment equipment = new Equipment(designation,quantity,description);
        HttpEntity<Equipment> requestEntity = new HttpEntity<>(equipment);
        try {
            ResponseEntity<Equipment> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Equipment.class);
            return responseEntity.getStatusCodeValue();
        } catch(HttpClientErrorException ex){
            return ex.getStatusCode().value(); // conflict ( létező start number)
        }

    }

    public int updateEquipment(int ID, String designation,int quantity,String description) {
        String url = API_URL+"/equipments/update/{ID}";
        Equipment equipment = new Equipment(ID,designation,quantity,description);

        // az alábbi két sorral állítjuk be a restTemplate példányt arra, hogy tudja kezelni a patch kérést
        // ezért kellett a httpclient dependency a pom.xml-be
        CloseableHttpClient client = HttpClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));

        HttpEntity<Equipment> requestEntity = new HttpEntity<>(equipment);
        ResponseEntity<Equipment> responseEntity = restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Equipment.class, ID);
        return responseEntity.getStatusCodeValue();
    }

    public int deleteEquipment(int ID) {
        System.out.println("Service: "+(ID-1));
        String url = API_URL+"/equipment/delete/{ID}";
        restTemplate.delete(url,ID);
        System.out.println("return 100");
        return 100;
    }
}
