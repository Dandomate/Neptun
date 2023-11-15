package hu.NeptunApi.services;

import hu.NeptunApi.domain.Grade;
import hu.NeptunApi.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
public class GradeService {
    @Autowired
    private GradeRepository repository;
    public Grade getGrade(int ID){
        Optional<Grade> grade = repository.findById(ID);
        if(grade.isPresent())
            return grade.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
