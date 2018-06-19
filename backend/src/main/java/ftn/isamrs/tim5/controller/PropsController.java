package ftn.isamrs.tim5.controller;


import ftn.isamrs.tim5.dto.BidDTO;
import ftn.isamrs.tim5.dto.BoughtPropDTO;
import ftn.isamrs.tim5.dto.PropWithRoleDTO;
import ftn.isamrs.tim5.dto.PropsCreateDTO;
import ftn.isamrs.tim5.model.*;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.*;
import ftn.isamrs.tim5.util.ConvertDTOToModel;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.PreUpdate;
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
    private AccountAuthorityService accountAuthorityService;

    @Autowired
    private BiddingService biddingService;


    @Autowired
    private JWTUtils jwtUtils;

    @RequestMapping(value = "get_all",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestHeader("Authentication-Token")String token){

        String username = jwtUtils.getUsernameFromToken(token);

        CineterAdmin account = (CineterAdmin) accountService.findByUsername(username);

        List<Props> propsList = this.propsService.findAllByCineterId(account.getCineter().getId());

        List<PropsCreateDTO> dto = new ArrayList<>();

        for (Props prop : propsList) {
            dto.add(new PropsCreateDTO(prop));
            System.out.println(dto.get(dto.size() - 1));
        }


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
        ArrayList<PropWithRoleDTO> dtos = new ArrayList<>();
        List<Props> propsList1 = new ArrayList<>();

        int auth = 0;

        List<PropRequest> requests = propsRequestService.getAll();

        if(requests != null && requests.size() > 0) {
            for (Props p : propsList) {
                boolean inRequest = false;
                for (PropRequest pr : requests) {
                    if (pr.getProps().getId().equals(p.getId())) {
                        inRequest = true;
                        break;
                    }
                }
                if(!inRequest) propsList1.add(p);
            }
        }
        else{
            propsList1 = propsList;
        }

        for(Props p : propsList1) {
            auth = accountAuthorityService.AuthorityByAccId(p.getAccount().getId());
            System.out.println(auth);
            dtos.add(new PropWithRoleDTO(p, auth));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "reserve_props",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity reserveProp(@RequestParam("id") Long id,
                                      @RequestParam("version") Integer version,
                                      @RequestHeader(value = "Authentication-Token") String token){


        try {
            Props props = propsService.findPropById(id);

            if(!Integer.valueOf(props.getVersion()).equals(version))
                throw new OptimisticEntityLockException(props, "");

            BoughtProps boughtProps = new BoughtProps();
            BoughtProps boughtProps2 = null;

            Account account = accountService.findByUsername(jwtUtils.getUsernameFromToken(token));

            List<BoughtProps> boughtProps1 = boughtPropsService.findBoughtPropById(id);


            for(BoughtProps bp : boughtProps1){
                if(bp.getAccount().getId().equals(account.getId())){
                    boughtProps2 = bp;
                }
            }

            if (boughtProps2 == null) {
                boughtProps.setAmount(1);
            } else {
                boughtProps2.setAmount(boughtProps.getAmount() + 1);
                boughtProps = boughtProps2;
            }

            boughtProps.setName(props.getName());
            boughtProps.setDescription(props.getDescription());
            boughtProps.setPropId(id);

            propsService.sellProp(id);

            props = propsService.findPropById(id);

            if (props.getAmount() == 0) {
                propsService.deleteProp(id);
            }

            boughtProps = boughtPropsService.saveBoughtProp(boughtProps, account);
            //kako da resim ako vec postoji samo da se prepravi preko sa vecom kolicinom?? ako je sve isto samo ne kolicina?

            BoughtPropDTO dto = new BoughtPropDTO(boughtProps);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch(OptimisticEntityLockException e){return new ResponseEntity(HttpStatus.CONFLICT);}

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

        propsRequestService.saveRequest(request);
        for (CineterAdmin c : cineterAdmins){
            c.getPropRequests().add(request);
            adminService.saveTheaterAdmin(c);
        }
        propsRequestService.saveRequest(request);
        propsService.saveProp(request.getProps());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/my_props",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sell_props(@RequestHeader(value = "Authentication-Token") String token) {


        String username = jwtUtils.getUsernameFromToken(token);

        Account account = accountService.findByUsername(username);

        List<Props> propsList = this.propsService.findMyProps(account.getId());

        List<PropsCreateDTO> dto = new ArrayList<>();

        for (Props prop : propsList)
            dto.add(new PropsCreateDTO(prop));

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/bought_props",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity bought_Props(@RequestHeader(value = "Authentication-Token") String token){

        String username = jwtUtils.getUsernameFromToken(token);

        Account account = accountService.findByUsername(username);

        List<Props> propsList = this.propsService.findBoughtProps(account.getId());

        List<PropsCreateDTO> dto = new ArrayList<>();

        for (Props prop : propsList)
            dto.add(new PropsCreateDTO(prop));

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @RequestMapping(value = "/bid_for_prop",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity bid_for_prop(@RequestParam("prop_id") Long prop_id, @RequestParam("price") Integer price,
                                       @RequestParam("version") Integer version,
                                       @RequestHeader("Authentication-Token") String token){
        try {
            Props prop = propsService.findPropById(prop_id);

            if (!Integer.valueOf(prop.getVersion()).equals(version))
                throw new OptimisticEntityLockException(prop, "");

            Account account = accountService.findByUsername(jwtUtils.getUsernameFromToken(token));

            Bid bid = new Bid();
            List<Bid> bids = biddingService.getMyBiddings(account.getId());
            boolean existing = false;
            if (bids != null && bids.size() > 0) {
                for (Bid b : bids) {
                    if (b.getProp().getId().equals(prop_id)) {
                        b.setPrice(price);
                        bid = b;
                        existing = true;
                        break;
                    }
                }
            }

            if (!existing) {
                bid = new Bid(prop, price, account);
            }


            biddingService.save(bid);

            return new ResponseEntity<>(new BidDTO(bid), HttpStatus.OK);
        }catch (OptimisticEntityLockException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/bids_for_my_prop",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity bids_for_my_prop(@RequestHeader(value = "Authentication-Token") String token){

        String username = jwtUtils.getUsernameFromToken(token);
        Account account = accountService.findByUsername(username);
        Long id = account.getId();

        List<Bid> bids = biddingService.getMyBids(id);

        List<BidDTO> dtos = new ArrayList<>();

        for(Bid b : bids){
            dtos.add(new BidDTO(b)); //u bidsDTO zakomentarisala ono za objekte jer ne znam kako tu aaaa...
            //je l' treba id-jev eili objekteee
        }
        //je l' ovde da koristim onaj dto? posto meni u BIDS tabeli stoje id-jevi od ownera, propa i biddera....

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/accept_bid",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity accept_bid(@RequestParam("id") Long id, @RequestParam("prop_id") Long prop_id){

        Bid bid = biddingService.getBidById(id);

        Props props;
        BoughtProps boughtProps2 = null;
        BoughtProps boughtProps = new BoughtProps(bid.getProp().getId(), bid.getProp().getName(), bid.getProp().getDescription(), 1);

        List<BoughtProps> boughtProps1 = boughtPropsService.findBoughtPropById(prop_id);

        for(BoughtProps bp : boughtProps1){
            if(bp.getAccount().getId().equals(bid.getBidder().getId())){
                boughtProps2 = bp;
            }
        }

        if (boughtProps2 == null) {
            boughtProps.setAmount(1);
        } else {
            boughtProps.setAmount(boughtProps.getAmount() + 1);
        }

        propsService.sellProp(prop_id);

        props = propsService.findPropById(prop_id);

        if (props.getAmount() == 0) {
            propsService.deleteProp(prop_id);
        }

        boughtPropsService.saveBoughtProp(boughtProps, bid.getBidder());

        List<Bid> bids = biddingService.getBidsByPropId(prop_id);

        for (Bid b :
                bids) {
            b.setProp(null);
            biddingService.save(b);
            biddingService.deleteBids(b.getId());
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "my_biddings",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity myBiddings(@RequestHeader(value = "Authentication-Token") String token){

        String username = jwtUtils.getUsernameFromToken(token);
        Account account = accountService.findByUsername(username);
        Long id = account.getId();

        List<Bid> myBiddings = biddingService.getMyBiddings(id);

        List<BidDTO> dto = new ArrayList<>();

        for (Bid b : myBiddings)
            dto.add(new BidDTO(b));

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @RequestMapping(value = "delete_user_prop",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete_user_prop(@RequestHeader(value = "Authentication-Token") String token,
                                           @RequestParam("id") Long id){





        List<Bid> bids = biddingService.getBidsByPropId(id);

        for (Bid b : bids){
            biddingService.deleteBids(b.getId());
        }

        propsService.deleteProp(id);

        return new ResponseEntity<>(HttpStatus.OK);


    }
}
