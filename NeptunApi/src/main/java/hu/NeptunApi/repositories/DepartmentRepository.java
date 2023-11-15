package hu.NeptunApi.repositories;

import hu.NeptunApi.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query(value = "SELECT ID, name FROM department", nativeQuery = true)
    public List<Object[]> getDepartments();
}
