package ftn.isamrs.tim5.repository;

import ftn.isamrs.tim5.model.MovieReservation;
import ftn.isamrs.tim5.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieReservationRepository extends JpaRepository<MovieReservation, Long> {


    @Query(value = "SELECT mr.ID, mr.TOTAL_PRICE, mr.SCREENING_ID FROM MOVIE_RESERVATION mr RIGHT OUTER JOIN (\n" +
            "SELECT * FROM ACCOUNT_MOVIE_RESERVATIONS amr WHERE amr.account_id = :id AND " +
            "amr.MOVIE_RESERVATIONS_ID IN (SELECT mr.ID FROM MOVIE_RESERVATION mr WHERE mr.SCREENING_ID IN (SELECT p.ID " +
            "FROM PSBASE p WHERE p.DATE < CURRENT_TIME() AND p.HALL_ID IN (SELECT h.ID FROM HALL h WHERE h.CINETER_ID = :cineterId)))) " +
            "AS a ON a.movie_reservations_id = mr.id", nativeQuery = true)
    List<MovieReservation> findMyReservationsForCineter(@Param("id") Long id, @Param("cineterId") Long cineterId);

    List<MovieReservation> findMovieReservationsByScreening_Id(long id);
}
