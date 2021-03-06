package ftn.isamrs.tim5.controller;

import ftn.isamrs.tim5.dto.MovieScreeningCreateDTO;
import ftn.isamrs.tim5.dto.PerformanceCreateDTO;
import ftn.isamrs.tim5.dto.ShowCreateDTO;

import ftn.isamrs.tim5.model.MovieScreening;
import ftn.isamrs.tim5.model.PSBase;
import ftn.isamrs.tim5.model.Performance;
import ftn.isamrs.tim5.model.Show;

import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.ShowService;
import ftn.isamrs.tim5.service.MovieScreeningService;
import ftn.isamrs.tim5.service.PerformanceService;

import org.hibernate.JDBCException;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/show")
public class ShowController {


    @Autowired
    ShowService showService;

    @Autowired
    PerformanceService performanceService;

    @Autowired
    MovieScreeningService movieScreeningService;

    @Autowired
    JWTUtils jwtUtils;

    @RequestMapping(value = "/get_all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllShows() {
        List<Show> shows = showService.findAll();
        ArrayList<ShowCreateDTO> dtos = new ArrayList<>();

        for (Show show : shows)
            dtos.add(new ShowCreateDTO(show));

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/get_shows",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getShowsByCineterId(@RequestParam("id") Long cineter_id) {

        List<Show> shows = this.showService.findByCineterId(cineter_id);

        List<ShowCreateDTO> dtos = new ArrayList<>();

        for (Show show : shows)
            dtos.add(new ShowCreateDTO(show));

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/get_performances",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPerformances(@RequestParam("id") Long id) {

        Show show = showService.findById(id);

        //ako je film dodaj movie screenings
        if (show.getIsMovie()){

            List<MovieScreening> movieScreenings = movieScreeningService.findAllByShowId(id);
            //List<MovieScreening> movieScreenings = movieScreeningService.findAll();
            ArrayList<MovieScreeningCreateDTO> dtos = new ArrayList<>();
            for (MovieScreening ms : movieScreenings)
                dtos.add(new MovieScreeningCreateDTO(ms));

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        // inace trazi performances
        else {

            List<Performance> performances = performanceService.findAllByShowId(id);
            //List<Performance> performances = performanceService.findAll();
            ArrayList<PerformanceCreateDTO> dtos = new ArrayList<>();
            for (Performance p : performances)
                dtos.add(new PerformanceCreateDTO(p));

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "get_show",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findProp(@RequestParam("id") Long id){

        PSBase show = movieScreeningService.findById(id);

        return new ResponseEntity<>(new MovieScreeningCreateDTO(show), HttpStatus.OK);
    }

    // delete shows and movie projections

    @Transactional
    @RequestMapping(value = "/delete_show",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteShow(@RequestBody ShowCreateDTO show)
    {
        try {

            showService.deleteShow(show);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (OptimisticEntityLockException | NullPointerException | JDBCException e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    // update shows and movie projections

    @Transactional
    @RequestMapping(value = "/update_show",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateShow(@RequestBody ShowCreateDTO dto){

        try {

            Show show = showService.findById(dto.getId());

            if(show.getVersion() != dto.getVersion())
                throw new OptimisticEntityLockException(show, "");

            show = showService.updateShow(dto);

            if (show == null) return new ResponseEntity(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(new ShowCreateDTO(show), HttpStatus.OK);
        }
        catch (OptimisticEntityLockException | NullPointerException | JDBCException e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }
}
