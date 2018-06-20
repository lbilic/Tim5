package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT * FROM REVIEW R WHERE R.ACCOUNT_ID = :accountId AND R.CINETER_ID = :cineterId", nativeQuery = true)
    Review alreadyVoted(@Param("accountId") Long accountId,@Param("cineterId") Long cineterId);

    @Query(value = "SELECT * FROM REVIEW R WHERE R.CINETER_ID = :cineterId", nativeQuery = true)
    List<Review> findByCinterId(@Param("cineterId") Long cineterId);

    @Query(value = "SELECT * FROM REVIEW R WHERE R.SHOW_ID = :showId", nativeQuery = true)
    List<Review> findByShowId(@Param("showId")Long id);
}
