package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Props;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropsRepository extends JpaRepository<Props, Long>{

    @Query(value = "SELECT * FROM Props p WHERE p.cineter_id = :cinId", nativeQuery = true)
    List<Props> findAllByCineterId(@Param("cinId") Long id);

    @Query(value = "SELECT * FROM Props p WHERE p.id = :propId", nativeQuery = true)
    Props findPropById(@Param("propId") Long id);

    @Query(value = "SELECT * FROM Props p WHERE p.account_id = :accId", nativeQuery = true)
    List<Props> findMyProps(@Param("accId") Long id);

    @Query(value = "SELECT * FROM BOUGHT_PROPS bp WHERE bp.account_id = :accId", nativeQuery = true)
    List<Props> findBoughtProps(@Param("accId") Long id);

}
