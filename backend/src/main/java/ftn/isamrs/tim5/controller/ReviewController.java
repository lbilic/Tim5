package ftn.isamrs.tim5.controller;

import ftn.isamrs.tim5.dto.ReviewDTO;
import ftn.isamrs.tim5.model.*;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/rate")
public class ReviewController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieReservationService movieReservationService;

    @Autowired
    private ShowReservationService showReservationService;

    @Autowired
    private ShowService showService;

    @Autowired
    private CineterService cineterService;

    @RequestMapping
            (
                    value = "can_rate_cineter",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE
            )
    public ResponseEntity CanRate(@RequestParam Long cineterId, @RequestHeader("Authentication-Token")
            String token) {

        Account account = accountService.findByUsername(jwtUtils.getUsernameFromToken(token));

        if (this.reviewService.alreadyVoted(account.getId(), cineterId))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Cineter cineter = cineterService.findById(cineterId);
        List<MovieReservation> movies = null;
        List<ShowReservation> shows = null;
        if (cineter.isTheater()) {
            shows = showReservationService.findMyReservations(account.getId(), cineterId);
        } else {
            movies = movieReservationService.findMyReservationsForCineter(account.getId(), cineterId);
        }

        movies = movies == null ? new ArrayList<>() : movies;
        shows = shows == null ? new ArrayList<>() : shows;

        return new ResponseEntity(movies.size() == 0
                && shows.size() == 0 ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @RequestMapping
            (
                    value = "/cineter",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE
            )
    public ResponseEntity rate(@RequestParam Long id, int rate, @RequestHeader("Authentication-Token")
            String token) {
        if(rate > 5 || rate < 1)  return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Cineter cineter = cineterService.findById(id);
        Account account = accountService.findByUsername(jwtUtils.getUsernameFromToken(token));
        if (cineter == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Review review = new Review();
        review.setAccount(account);
        review.setCineter(cineter);
        review.setDate(new Date());
        review.setScore(rate);

        this.reviewService.save(review);

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping
            (
                    value = "/show",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE
            )
    public ResponseEntity rateShow(@RequestParam Long id, int rate, @RequestHeader("Authentication-Token")
            String token) {

        if(rate > 5 || rate < 1)  return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Show show = showService.findById(id);
        Account account = accountService.findByUsername(jwtUtils.getUsernameFromToken(token));
        if (show == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Review review = new Review();
        review.setAccount(account);
        review.setShow(show);
        review.setDate(new Date());
        review.setScore(rate);

        this.reviewService.save(review);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping
            (
                    value = "/cineter",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE
            )
    public ResponseEntity rate(@RequestParam Long id) {

        List<Review> reviews = this.reviewService.findByCineterId(id);
        double sum = 0;
        for (Review review : reviews) {
            sum += review.getScore();
        }
        if (sum == 0) return new ResponseEntity<>(new ReviewDTO(0), HttpStatus.OK);

        sum /= reviews.size();

        return new ResponseEntity<>(new ReviewDTO(sum), HttpStatus.OK);
    }

    @RequestMapping
            (
                    value = "/show",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE
            )
    public ResponseEntity show(@RequestParam Long id) {

        List<Review> reviews = this.reviewService.findByShowId(id);
        double sum =0;
        for (Review review : reviews) {
            sum += review.getScore();
        }
        if(sum == 0) return new ResponseEntity<>(new ReviewDTO(0), HttpStatus.OK);

        sum /= reviews.size();

        return new ResponseEntity<>(new ReviewDTO(sum), HttpStatus.OK);
    }

}
