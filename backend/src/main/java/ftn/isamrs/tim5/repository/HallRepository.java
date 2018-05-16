package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Long> {

    List <Hall> findAll();

    /*
    @Query(value = "SELECT * FROM Hall h where h.number = :number" +
            " and h.seatlayout = :seatlayout", nativeQuery = true)

    Hall findByNumberAAndSeatLayout(@Param("number") int number,
                                    @Param("seatlayout") String seatlayout);*/
}
