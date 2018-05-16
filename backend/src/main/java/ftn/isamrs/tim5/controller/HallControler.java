package ftn.isamrs.tim5.controller;

import ftn.isamrs.tim5.dto.HallCreateDTO;
import ftn.isamrs.tim5.model.Hall;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.HallService;
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
@RequestMapping(value = "/api/halls")
public class HallControler {


    @Autowired
    HallService hallService;

    @Autowired
    JWTUtils jwtUtils;

    @RequestMapping(value = "/get_all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity getAllHalls() {
        List<Hall> halls = this.hallService.findAll();
        ArrayList<HallCreateDTO> dtos = new ArrayList<>();

        for (Hall hall : halls)
            dtos.add(new HallCreateDTO(hall));

        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }
}