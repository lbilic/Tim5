package ftn.isamrs.tim5.controller;

import ftn.isamrs.tim5.dto.ShowCreateDTO;
import ftn.isamrs.tim5.model.Show;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // delete shows and movie projections

    @RequestMapping(value = "/delete_show",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteShow(@RequestBody ShowCreateDTO show)
    {
        showService.deleteShow(show);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // update shows and movie projections

    @RequestMapping(value = "/update_show",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateShow(@RequestBody ShowCreateDTO dto){

        Show show = showService.updateShow(dto);

        if(show == null) return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new ShowCreateDTO(show), HttpStatus.OK);
    }
}
