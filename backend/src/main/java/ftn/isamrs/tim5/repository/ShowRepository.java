package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query(value = "SELECT * FROM SHOW  s where s.name = :name and s.cineter_id = :cineter_id", nativeQuery = true)
    Show findByNameAndCineterId(@Param("name") String name, @Param("cineter_id") Long cineter_id);
}
