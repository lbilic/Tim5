package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query(value = "SELECT * FROM SHOW  s where s.name = :name and s.cineter_id = :cineter_id", nativeQuery = true)
    Show findByNameAndCineterId(@Param("name") String name, @Param("cineter_id") Long cineter_id);

    @Query(value = "SELECT * FROM SHOW  s where s.name = :name and s.description = :description", nativeQuery = true)
    Show findByNameAndDescription(@Param("name") String name, @Param("description") String description);

    @Query(value="SELECT * from show s where s.cineter_id=:cineter_id", nativeQuery = true)
    List<Show> findByCineterId(@Param("cineter_id") Long cineter_id);
}
