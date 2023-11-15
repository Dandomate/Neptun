package hu.NeptunApi.repositories;

import hu.NeptunApi.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    @Query(value="SELECT* FROM equipment", nativeQuery = true)
    public List<Object[]> getEquipment();
}
