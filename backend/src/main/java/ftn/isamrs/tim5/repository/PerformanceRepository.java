package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    @Query(value = "SELECT * FROM psbase pb LEFT OUTER JOIN performance p ON pb.id = p.id WHERE pb.show_id = :showId", nativeQuery = true)
    List<Performance> findByShowId (@Param("showId") Long id);

    List<Performance> findAll();
}
