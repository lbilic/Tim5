package ftn.isamrs.tim5.controller;


import ftn.isamrs.tim5.dto.CineterCreateDTO;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.service.CineterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cineter")
public class CineterController {

    @Autowired
    CineterService cineterService;

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
}
