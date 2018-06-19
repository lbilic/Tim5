package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.BoughtProps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoughtPropsRepository extends JpaRepository<BoughtProps, Long> {

    @Query(value = "SELECT * FROM Bought_Props p WHERE p.id = :propId", nativeQuery = true)
    List<BoughtProps> findBoughtPropById(@Param("propId") Long id);

    @Query(value = "SELECT * FROM Bought_Props p WHERE p.account_id = :accId", nativeQuery = true)
    List<BoughtProps> findAllByUserId(@Param("accId") Long id);
}
