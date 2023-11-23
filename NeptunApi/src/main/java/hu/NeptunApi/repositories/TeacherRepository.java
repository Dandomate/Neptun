package hu.NeptunApi.repositories;


import hu.NeptunApi.domain.Department;
import hu.NeptunApi.domain.Student;
import hu.NeptunApi.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    //public List<Teacher> findAllByOrderByName();

    @Query(value = "SELECT * FROM Teacher ORDER BY Name ASC;", nativeQuery = true)
    public List<Teacher> nativeFindTeachers();




}