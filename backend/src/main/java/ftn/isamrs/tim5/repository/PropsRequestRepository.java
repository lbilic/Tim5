package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.PropRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropsRequestRepository extends JpaRepository<PropRequest, Long> {

    // Je l' dobro ovo adminAccounts_id jer ja u propRequest imam listu admin account-eva i onda ne znam kako cuva u tabeli

    @Query(value = "SELECT * FROM PropRequest p WHERE p.adminAccounts_id = :AdminId", nativeQuery = true)
    List<PropRequest> findAllByAdminId(@Param("AdminId") Long id);

    @Query(value = "SELECT * FROM PropRequest WHERE p.id = :reqId", nativeQuery = true)
    PropRequest findRequestById(@Param("reqId") Long id);

}
