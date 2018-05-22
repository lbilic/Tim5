package ftn.isamrs.tim5.controller;


import ftn.isamrs.tim5.dto.PropsCreateDTO;
import ftn.isamrs.tim5.dto.PropsDTO;
import ftn.isamrs.tim5.model.CineterAdmin;
import ftn.isamrs.tim5.model.Props;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.AccountService;
import ftn.isamrs.tim5.service.PropsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.websocket.server.PathParam;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/props")
public class PropsController
{

    @Autowired
    private PropsService propsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JWTUtils jwtUtils;

    @RequestMapping(value = "get_all",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestHeader("Authentication-Token")String token){

        String username = jwtUtils.getUsernameFromToken(token);

        CineterAdmin account = (CineterAdmin)accountService.findByUsername(username);


        List<Props> propsList = this.propsService.findAllByCineterId(account.getCineter().getId());

        List<PropsCreateDTO> dto = new ArrayList<>();

        for (Props prop : propsList)
            dto.add(new PropsCreateDTO(prop));

        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "change_prop",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeProp(@RequestBody PropsCreateDTO dto){

        Long id = dto.getId();

        Props props = propsService.findPropById(id);

        props.setName(dto.getName());
        props.setDescription(dto.getDescription());
        props.setPrice(dto.getPrice());

        props = propsService.saveProp(props);

        return new ResponseEntity<>(props, HttpStatus.OK);

    }

    @RequestMapping(value = "find_prop",
                   method = RequestMethod.GET,
                   produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findProp(@RequestParam("id") Long id){

        Props props = propsService.findPropById(id);

        return new ResponseEntity<>(new PropsCreateDTO(props), HttpStatus.OK);
    }

    @RequestMapping(value = "delete_prop",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteProps(@RequestBody PropsCreateDTO props){

        System.out.println(props);
        propsService.deleteProp(props.getId());

        return new ResponseEntity(HttpStatus.OK);

    }

}
