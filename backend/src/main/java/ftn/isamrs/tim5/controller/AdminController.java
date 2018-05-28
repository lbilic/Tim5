package ftn.isamrs.tim5.controller;


import ftn.isamrs.tim5.dto.*;
import ftn.isamrs.tim5.model.*;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.*;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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

    @Autowired
    PropsRequestService propsRequestService;

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
    public ResponseEntity createPerformance(@RequestBody PerformanceCreateDTO projection,
                                            @RequestParam() Long id)
    {

        Performance performance = performanceService.savePerformance(projection, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping (value = "/create_movie_screening",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createMovieScreening(@RequestBody MovieScreeningCreateDTO dto,
                                               @RequestParam() Long id)
    {
        MovieScreening movieScreening = movieScreeningService.saveMovieScreening(dto, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/create_cineter_admin",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTheaterAdmin(@RequestBody CineterAdminCreateDTO admin)
    {
        CineterAdmin cineterAdmin = adminService.saveTheaterAdmin(admin);
        return new ResponseEntity<>(HttpStatus.CREATED);
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

    @RequestMapping(value = "/get_all_requests",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllRequests(@RequestHeader("Authentication-Token")String token){

        String username = jwtUtils.getUsernameFromToken(token);

        CineterAdmin account = (CineterAdmin)accountService.findByUsername(username);

        Long id = account.getId();

        List<PropRequest> requests = propsRequestService.getAllByAdminId(id);

        List<PropsRequestDTO> dto = new ArrayList<>();

        for (PropRequest request : requests)
            dto.add(new PropsRequestDTO(request));

        return new ResponseEntity(dto, HttpStatus.OK);

    }

    @Transactional
    @RequestMapping(value = "/accept_request",
                    method = RequestMethod.GET,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity accept_request(@RequestParam("id") Long id){


        try {
            PropRequest request = propsRequestService.findRequestById(id);

            Props prop = request.getProps();


            propsRequestService.deleteRequest(request);

            propsService.saveProp(prop);

            return new ResponseEntity(HttpStatus.OK);
        }
        catch (OptimisticEntityLockException e){ return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }


}
