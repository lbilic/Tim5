package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.MovieScreening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieScreeningRepository extends JpaRepository<MovieScreening, Long> {

    @Query(value = "SELECT * FROM psbase pb LEFT OUTER JOIN MOVIE_SCREENING ms ON pb.id = ms.id WHERE pb.show_id = :showId", nativeQuery = true)
    List<MovieScreening> findByShowId (@Param("showId") Long id);
}
