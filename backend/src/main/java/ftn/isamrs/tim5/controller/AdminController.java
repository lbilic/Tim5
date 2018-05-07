package ftn.isamrs.tim5.controller;


import ftn.isamrs.tim5.dto.CineterAdminCreateDTO;
import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.dto.PropsCreateDTO;
import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.CineterAdmin;
import ftn.isamrs.tim5.model.Props;
import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/admin")
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
