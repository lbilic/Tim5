package ftn.isamrs.tim5.controller;

import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.security.JWTUtils;
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
@RequestMapping(value = "/api/show")
public class ShowController {


    @Autowired
    ShowService showService;

    @Autowired
    JWTUtils jwtUtils;

    @RequestMapping(value = "/get_all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllShows() {
        List<Show> shows = showService.findAll();
        ArrayList<ShowCreateDTO> dtos = new ArrayList<>();

        for (Show show : shows)
            dtos.add(new ShowCreateDTO(show));

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "get_show",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findProp(@RequestParam("id") Long id){

        Show show = showService.findById(id);

        return new ResponseEntity<>(new ShowCreateDTO(show), HttpStatus.OK);
    }
}
