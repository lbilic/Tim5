package ftn.isamrs.tim5.controller;

import ftn.isamrs.tim5.dto.PropsCreateDTO;
import ftn.isamrs.tim5.dto.ShowReservationDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.MovieReservation;
import ftn.isamrs.tim5.model.Performance;
import ftn.isamrs.tim5.model.ShowReservation;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.*;
import ftn.isamrs.tim5.dto.MovieReservationDTO;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/reservations")
public class ReservationController {
    @Autowired
    private MovieReservationService movieReservationService;

    @Autowired
    private ShowReservationService showReservationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private MovieScreeningService movieScreeningService;

    @RequestMapping(value = "/get_all_movie_reservations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllMovieReservations(@RequestHeader("Authentication-Token")String token){

        String username = jwtUtils.getUsernameFromToken(token);
        Account account = (Account)accountService.findByUsername(username);


        List<MovieReservation> movieReservations = this.accountService.findAllMovieReservations(account.getId());
        List<MovieReservationDTO> dtos = new ArrayList<>();

        for (MovieReservation mr : movieReservations)
            dtos.add(new MovieReservationDTO(mr));

        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/get_all_show_reservations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllShowReservations(@RequestHeader("Authentication-Token")String token){

        String username = jwtUtils.getUsernameFromToken(token);
        Account account = (Account)accountService.findByUsername(username);


        List<ShowReservation> showReservations = this.accountService.findAllShowReservations(account.getId());
        List<ShowReservationDTO> dtos = new ArrayList<>();

        for (ShowReservation sr : showReservations)
            dtos.add(new ShowReservationDTO(sr));

        return new ResponseEntity(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/reserve",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity reserve(@RequestHeader("Authentication-Token") String token) {
        return null;
    }

    @RequestMapping(value = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllReservations(@RequestParam("id") Long id){

        List<MovieReservation> mr = this.movieReservationService.findMovieReservationsByScreening_Id(id);
        List<MovieReservationDTO> dtos;
        if(mr != null) {
            // vrati rezervacije za taj film
        } else {
            //ShowReservation sr = this.showReservationService.getByPerformanceId(id);
            // vrati rezervacije za taj show
        }

        return new ResponseEntity(null, HttpStatus.OK);
    }
}
