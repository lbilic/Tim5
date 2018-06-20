package ftn.isamrs.tim5.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import ftn.isamrs.tim5.dto.*;
import ftn.isamrs.tim5.model.*;
import ftn.isamrs.tim5.model.System;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.*;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.hibernate.JDBCException;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
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
    SystemService systemService;

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

    @Autowired
    EmailService emailService;

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
                                            @RequestParam() Long id,
                                            @RequestParam() String time)
    {

        Performance performance = performanceService.savePerformance(projection, id, time);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping (value = "/create_movie_screening",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createMovieScreening(@RequestBody MovieScreeningCreateDTO dto,
                                               @RequestParam() Long id,
                                               @RequestParam() String time)
    {
        MovieScreening movieScreening = movieScreeningService.saveMovieScreening(dto, id, time);
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
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity accept_request(@RequestParam("id") Long id) throws MessagingException {


        try {
            PropRequest request = propsRequestService.findRequestById(id);
            String email = accountService.findEmailById(request.getUserAccount().getId());
            emailService.sendMail("Congratulations! Your prop has been approved for sale!",
                    "Hi! We are pleased to inform you that our costumers can now see your prop and bid for it!" +
                            "We wish you high bidding! Best regards, Admin Team", email);

            if(request == null) {
                throw new NullPointerException();
            }

            List<CineterAdmin> admins = request.getAdminAccounts();

            for (CineterAdmin admin:
                 admins) {
                if(admin.getPropRequests().contains(request))
                    admin.getPropRequests().remove(request);
                adminService.saveTheaterAdmin(admin);
            }



            Props prop = request.getProps();
            propsRequestService.deleteRequest(request);

            propsService.saveProp(prop);

            return new ResponseEntity(HttpStatus.OK);
        }
        catch (OptimisticEntityLockException | NullPointerException | JDBCException e)
        { return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @Transactional
    @RequestMapping(value = "/deny_request",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deny_request(@RequestParam("id") Long id) throws MessagingException {


        try {
            PropRequest request = propsRequestService.findRequestById(id);

            String email = accountService.findEmailById(request.getUserAccount().getId());
            emailService.sendMail("We are sorry. Your prop has been denied for sale!",
                    "Hi. We are sorry to inform you that your prop did not meet our standards and was not approved for sale!" +
                            "We wish you better luck next time! Best regards, Admin Team", email);

            List<CineterAdmin> admins = request.getAdminAccounts();

            for (CineterAdmin admin:
                    admins) {
                if(admin.getPropRequests().contains(request))
                    admin.getPropRequests().remove(request);
                adminService.saveTheaterAdmin(admin);
            }

            Props prop = request.getProps();
            propsRequestService.deleteRequest(request);
            propsService.deleteProp(prop.getId());

            return new ResponseEntity(HttpStatus.OK);
        }
        catch (OptimisticEntityLockException | NullPointerException | JDBCException e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

    }

    @Transactional
    @RequestMapping(value = "/update_scale",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update_scale(@RequestBody ScaleDTO scale) {
        try {
            System system = systemService.findAll().get(0);

            system.setScale(scale.getScale());

            systemService.save(system);

            return new ResponseEntity<>(scale, HttpStatus.OK);
        } catch(OptimisticEntityLockException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/get_scale",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get_scale() {
        System system = systemService.findAll().get(0);

        ScaleDTO scale = new ScaleDTO(system.getScale());

        return new ResponseEntity<>(scale, HttpStatus.OK);
    }

    }
