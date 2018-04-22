package ftn.isamrs.tim5.controller;


import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.model.User;
import ftn.isamrs.tim5.service.CineterService;
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

    @RequestMapping(value = "/create_cinetar",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTheater(@RequestBody CineterCreateDTO cineter)
    {
        return new ResponseEntity<>(cineterService.save(cineter), HttpStatus.OK);
    }



}
