package ftn.isamrs.tim5.controller;


import ftn.isamrs.tim5.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api")
public class AdminController {


    @RequestMapping(value = "/get_hello",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHello()
    {
        User user = new User();
        user.setName("Pera");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



}
