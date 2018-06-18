package ftn.isamrs.tim5.controller;

import ftn.isamrs.tim5.dto.HallCreateDTO;
import ftn.isamrs.tim5.model.Hall;
import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.repository.ShowRepository;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.HallService;
import ftn.isamrs.tim5.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/halls")
public class HallController {


    @Autowired
    HallService hallService;

    @Autowired
    ShowService showService;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    JWTUtils jwtUtils;

    @RequestMapping(value = "/get_all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity getAllHalls(@RequestParam("id") Long id) {
        List<Hall> halls = hallService.findAll();
        ArrayList<HallCreateDTO> dtos = new ArrayList<>();

        Show show = showService.findById(id);

        for (Hall hall : halls) {
            // id cinetera od hale i od sale mora biti isti
            if (hall.getCineter().getId() == show.getCineter().getId()) {
                dtos.add(new HallCreateDTO(hall));
            }
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

}