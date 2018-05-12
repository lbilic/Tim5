package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Props;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropsRepository extends JpaRepository<Props, Long>{

    @Query(value = "SELECT * FROM Props p WHERE p.cineter_id = :pera", nativeQuery = true)
    List<Props> findAllByCineterId(@Param("pera") Long id);
}
