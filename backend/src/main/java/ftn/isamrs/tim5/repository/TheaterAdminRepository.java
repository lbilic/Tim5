package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.CineterAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterAdminRepository extends JpaRepository<CineterAdmin, Long> {

    @Query(value = "SELECT * FROM Cineter_Admin ca WHERE ca.cineter_id = :CinId and ca.is_fan_zone = true", nativeQuery = true)
    List<CineterAdmin> findFanZoneAdmins(@Param("CinId") Long id);

}
