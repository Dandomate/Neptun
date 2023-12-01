package hu.NeptunFrontend.services;

import hu.NeptunFrontend.domain.TimeTableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Service
public class TimeTableService {

    @Autowired
    private RestTemplate restTemplate;
    private final String API_URL = "http://localhost:8095";

    public List<TimeTableList> getTimeTableList(int CLASS_ID, String day) {
        String url = API_URL+"/timetablelists/{CLASS_ID}/{day}";
        TimeTableList[] list = restTemplate.getForObject(url, TimeTableList[].class, CLASS_ID,day);
        return Arrays.asList(list);
    }

    public List<TimeTableList> getTimeTableListTeacher(long OMA_TEACHER, String day) {
        String url = API_URL+"/timetablelist/{OMA_TEACHER}/{day}";
        TimeTableList[] list = restTemplate.getForObject(url, TimeTableList[].class, OMA_TEACHER,day);
        return Arrays.asList(list);
    }
}
