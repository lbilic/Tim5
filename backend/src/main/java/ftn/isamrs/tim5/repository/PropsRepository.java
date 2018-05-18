package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Props;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropsRepository extends JpaRepository<Props, Long>{

    @Query(value = "SELECT * FROM Props p WHERE p.cineter_id = :pera", nativeQuery = true)
    List<Props> findAllByCineterId(@Param("pera") Long id);

    @Query(value = "SELECT PROP FROM Props p where p.id = :propId", nativeQuery = true)
    Props findPropById(@Param("propId") Long id);

    @Query(value = "DELETE from Props p where p.id = :propId", nativeQuery = true)
    Boolean deleteProp(@Param("propId") Long id);
}
