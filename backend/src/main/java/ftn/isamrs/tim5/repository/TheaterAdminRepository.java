package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.CineterAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterAdminRepository extends JpaRepository<CineterAdmin, Long> {


    //Ili da ne bude native query nego da bude hibernate ili da odradim inner join sa account jer on pravi objekat
    // a nema sve jer gleda samo cineter_admin tabelu jer je native query
    //@Query(value = "SELECT * FROM Cineter_Admin ca WHERE ca.cineter_id = :CinId and ca.is_fan_zone = true", nativeQuery = true)
    //List<CineterAdmin> findFanZoneAdmins(@Param("CinId") Long id);


    @Query(value = "SELECT * from Cineter_Admin ca INNER JOIN ACCOUNT A ON ca.id = a.id WHERE ca.cineter_id=:CinId " +
            "and ca.is_fan_zone = true", nativeQuery = true)
    List<CineterAdmin> findFanZoneAdmins(@Param("CinId") Long id);
}
