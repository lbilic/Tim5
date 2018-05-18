package ftn.isamrs.tim5.controller;


import ftn.isamrs.tim5.dto.*;
import ftn.isamrs.tim5.model.*;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/admin")
@CrossOrigin(value = "http://localhost:4200")
public class AdminController {

    @Autowired
    CineterService cineterService;

    @Autowired
    ShowService showService;

    @Autowired
    AdminService adminService;

    @Autowired
    AccountService accountService;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    PropsService propsService;

    @Autowired
    PerformanceService performanceService;

    @Autowired
    MovieScreeningService movieScreeningService;

    @RequestMapping(value = "/create_cinetar",
                    method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTheater(@RequestBody CineterCreateDTO cineter)
    {
        return new ResponseEntity<>(cineterService.save(cineter), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/create_show",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createShow(@RequestHeader(value = "Authentication-Token") String token,
                                     @RequestBody ShowCreateDTO show)
    {
        Account account = accountService.findByUsername(jwtUtils.getUsernameFromToken(token));
        Show s = showService.save(show, account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping (value = "/create_performance",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPerformance(@RequestBody PerformanceCreateDTO projection)
    {
        Performance performance = performanceService.savePerformance(projection);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping (value = "/create_movie_screening",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createMovieScreening(@RequestBody MovieScreeningCreateDTO dto)
    {
        MovieScreening movieScreening = movieScreeningService.saveMovieScreening(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/create_cineter_admin",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTheaterAdmin(@RequestBody CineterAdminCreateDTO admin)
    {
        CineterAdmin cineterAdmin = adminService.saveTheaterAdmin(admin);
        return new ResponseEntity<>(/*cineterAdmin, */HttpStatus.CREATED);
    }


    @RequestMapping(value = "/create_props",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createProps(@RequestHeader(value = "Authentication-Token") String token,
                                      @RequestBody PropsCreateDTO prop)
    {
        if(token == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Account account = accountService.findByUsername(jwtUtils.getUsernameFromToken(token));
        Props props = propsService.saveProps(prop, account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete_show",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteShow(@RequestHeader(value = "Authentication-Token") String token,
                                     @RequestBody ShowCreateDTO show)
    {
        Account account = accountService.findByUsername(jwtUtils.getUsernameFromToken(token));
        showService.delete(show, account);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
