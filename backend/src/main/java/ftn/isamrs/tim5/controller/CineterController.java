package ftn.isamrs.tim5.controller;


import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.model.CineterAdmin;
import ftn.isamrs.tim5.security.AuthenticationTokenFilter;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.CineterService;
import ftn.isamrs.tim5.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cineter")
public class CineterController {

    @Autowired
    CineterService cineterService;

    @Autowired
    AccountService accountService;

    @Autowired
    JWTUtils jwtUtils;

    @RequestMapping(value = "/get_all",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCineters(){
        List<Cineter> cineterList = cineterService.findAll();
        ArrayList<CineterCreateDTO> dtos = new ArrayList<>();

        for(Cineter c : cineterList)
            dtos.add(new CineterCreateDTO(c));

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @RequestMapping(value = "/delete_cineter",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCineter(@RequestBody CineterCreateDTO cineter)
    {
        cineterService.delete(cineter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/get",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCineter(@RequestParam Long id){
        Cineter cineter = cineterService.findById(id);

        if(cineter == null) return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new CineterCreateDTO(cineter), HttpStatus.OK);
    }

    @RequestMapping(value = "/get_cineter",
            method = RequestMethod.GET,
         //   consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCineterByAccount(@RequestHeader(value = "Authentication-Token") String token)
    {
        Account account = accountService.findByUsername(jwtUtils.getUsernameFromToken(token));

        Long id = ((CineterAdmin)account).getCineter().getId();

        Cineter cineter = cineterService.findById(id);

        if(cineter== null) return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new CineterCreateDTO(cineter),HttpStatus.OK);
    }

    @RequestMapping(value = "/update_cineter",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCineter(@RequestBody CineterCreateDTO dto){

        Cineter cineter = cineterService.updateCineter(dto);



        if(cineter == null) return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new CineterCreateDTO(cineter), HttpStatus.OK);
    }


}
