package hu.NeptunApi.repositories;

import hu.NeptunApi.domain.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer> {
    @Query(value="SELECT* FROM classroom", nativeQuery = true)
    public List<Object[]> getClassRooms();


}