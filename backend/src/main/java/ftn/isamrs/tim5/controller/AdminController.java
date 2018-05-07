package ftn.isamrs.tim5.controller;


import ftn.isamrs.tim5.dto.CineterAdminCreateDTO;
import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.CineterAdmin;
import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.service.AdminService;
import ftn.isamrs.tim5.service.CineterService;
import ftn.isamrs.tim5.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    CineterService cineterService;

    @Autowired
    ShowService showService;

    @Autowired
    AdminService adminService;

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
    public ResponseEntity createShow(@RequestBody ShowCreateDTO show)
    {
        Show s = showService.save(show);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
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


}
