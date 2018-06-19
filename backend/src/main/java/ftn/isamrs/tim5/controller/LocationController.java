package ftn.isamrs.tim5.controller;

import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/api")
public class LocationController {

    @RequestMapping(
            value = "/location",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getCoordinates(@RequestParam("address") String address) {

        final String API_KEY = "AIzaSyBtEDXxVtj8B6Pe_w5S0C7rx8p8rMgaVPU";
        final String API_URL = "https://maps.googleapis.com/maps/api/geocode/json?key=" + API_KEY +
                "&address=" + address;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(API_URL, String.class);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
