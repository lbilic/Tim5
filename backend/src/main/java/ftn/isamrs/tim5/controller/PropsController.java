package ftn.isamrs.tim5.controller;


import ftn.isamrs.tim5.dto.BoughtPropDTO;
import ftn.isamrs.tim5.dto.PropsCreateDTO;
import ftn.isamrs.tim5.model.*;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.*;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.print.attribute.standard.Media;
import javax.websocket.server.PathParam;
import javax.xml.ws.Response;
import java.io.Console;
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
    private BoughtPropsService boughtPropsService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private PropsRequestService propsRequestService;

    @Autowired
    private CineterService cineterService;


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
        props.setAmount(dto.getAmount());

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

    @RequestMapping(value = "user_view_props",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAllPropsById(@RequestParam("id") Long id){

        List<Props> propsList = propsService.findAllByCineterId(id);
        ArrayList<PropsCreateDTO> dtos = new ArrayList<>();

        for(Props p : propsList)
            dtos.add(new PropsCreateDTO(p));

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "reserve_props",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity reserveProp(@RequestParam("id") Long id,
                                      @RequestHeader(value = "Authentication-Token") String token
                                      ){


        System.out.println(id);
        Props props = propsService.findPropById(id);
        System.out.println("ovo je prop stvarno" + props);
        System.out.println("ovo je prop"+ props.getName());



        BoughtProps boughtProps = new BoughtProps();

        Account account = accountService.findByUsername(jwtUtils.getUsernameFromToken(token));

        BoughtProps boughtProps1 = boughtPropsService.findBoughtPropById(id);

       if(boughtProps1 == null){
            boughtProps.setAmount(1);
        }
        else{
            boughtProps.setAmount(boughtProps.getAmount()+1);
        }

        boughtProps.setName(props.getName());
        boughtProps.setDescription(props.getDescription());

        propsService.sellProp(id);

        props = propsService.findPropById(id);

        if(props.getAmount() == 0){
            propsService.deleteProp(id);
        }

        boughtProps = boughtPropsService.saveBoughtProp(boughtProps, account);

        BoughtPropDTO dto = new BoughtPropDTO(boughtProps);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @RequestMapping(value = "/send_props_request",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sell_props(@RequestHeader(value = "Authentication-Token") String token,
                                      @RequestBody PropsCreateDTO prop, @RequestParam("id") Long id){

        if(token == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Account account = accountService.findByUsername(jwtUtils.getUsernameFromToken(token));
        PropRequest request = new PropRequest();

        List<CineterAdmin> cineterAdmins = adminService.findFanZoneAdmins(id);
        System.out.println(cineterAdmins.size());
        Cineter cineter = cineterService.findById(id);

        request.setAdminAccounts(cineterAdmins);
        Props props = ConvertDTOToModel.convertPropsDTOtoProps(prop);
        request.setProps(props);

        request.getProps().setCineter(cineter);
        cineter.getProps().add(request.getProps());
        account = accountService.save(account);
        cineterService.save(cineter);
        request.setUserAccount(account);
        request.getProps().setAccount(account);
        propsService.saveProp(request.getProps());
        for (CineterAdmin c : cineterAdmins){
            c.getPropRequests().add(request);
            adminService.saveTheaterAdmin(c);
        }

        propsRequestService.saveRequest(request);




        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
