package ftn.isamrs.tim5.controller;

import ftn.isamrs.tim5.dto.*;
import ftn.isamrs.tim5.exception.BadRequestException;
import ftn.isamrs.tim5.exception.ForbiddenException;
import ftn.isamrs.tim5.exception.NotFoundException;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.Cineter;
import ftn.isamrs.tim5.model.CineterAdmin;
import ftn.isamrs.tim5.model.Friendship;
import ftn.isamrs.tim5.model.enumeration.FriendshipStatus;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.AccountService;
import ftn.isamrs.tim5.service.FriendshipService;
import ftn.isamrs.tim5.util.MessageConstants;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "user's essentials functionalities", description = "Operations pertaining to all kind of users in application.")
@CrossOrigin(value = "http://localhost:4200")
public class AppUserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;


    @RequestMapping(
            value = "/api/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Login user to application.",
            notes = "You have to provide a valid user's credentials.",
            httpMethod = "POST",
            consumes = "application/json",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully login and retrieved generated jwt token", response = TokenDTO.class),
            @ApiResponse(code = 400, message = "Inappropriate login object sent in request body"),
            @ApiResponse(code = 401, message = "You are not authorized to complete activity"),
            @ApiResponse(code = 404, message = "Invalid login")
    })
    public ResponseEntity login(
            @ApiParam(value = "The user's credentials.", required = true) @Valid @RequestBody LoginDTO loginDTO,
            @ApiParam(value = "The object that contains all errors from validation of DTO object") BindingResult errors) {
        System.out.println(loginDTO);
        try {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword());
            authenticationManager.authenticate(token);
            Account account = this.accountService.findByUsername(loginDTO.getUsername());
            if(!account.isConfirmed())
                throw new ForbiddenException("Account not confirmed!");

            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());

            Long id = account.getId();
            TokenDTO userToken = new TokenDTO(jwtUtils.generateToken(details, id, account.getAccountAuthorities()));
            return new ResponseEntity<>(userToken, HttpStatus.OK);
        } catch(ForbiddenException ex) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ASFIASJFOISAJOI");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/api/check_username",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Check does given username already exist.",
            notes = "You must provide valid username in the URL.",
            httpMethod = "GET",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully checked availability of given username"),
            @ApiResponse(code = 400, message = "Username parameter is missing")
    })
    public ResponseEntity checkUsername(
            @ApiParam(value = "User's username") @RequestParam("username") String username) {

        if (username == null || username.equals(""))
            throw new BadRequestException("Username can't be empty!");

        return new ResponseEntity(this.accountService.isUsernameTaken(username), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/current_user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Get current logged user.",
            httpMethod = "GET",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved current user")
    })
    public ResponseEntity getCurrentuser(@RequestHeader("Authentication-Token") String token) {
        String username = jwtUtils.getUsernameFromToken(token);

        Account acc = accountService.findByUsername(username);
        try {
            CineterAdmin admin = (CineterAdmin) acc;
            return new ResponseEntity<>(new CineterAdminCreateDTO(admin), HttpStatus.OK);
        }
        catch(ClassCastException e){
            return new ResponseEntity<>(new AccountDTO(acc), HttpStatus.OK);
        }

    }

    @RequestMapping(
            value = "/api/change_password",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity changePassword(@RequestHeader("Authentication-Token") String token,
                                         @RequestBody PasswordDTO password) {

        String username = jwtUtils.getUsernameFromToken(token);

        Account acc = accountService.findByUsername(username);

        if (!BCrypt.checkpw(password.getOldPassword(), acc.getPassword()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (!password.getNewPassword().equals(password.getConfirmPassword()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        CineterAdmin admin = (CineterAdmin) acc;

        String newPassword = BCrypt.hashpw(password.getNewPassword(), BCrypt.gensalt());
        acc.setPassword(newPassword);

        if (admin.getCineter() != null) {
            if (!admin.getChangedPassword())
                admin.setChangedPassword(true);

            accountService.save(admin);
            return new ResponseEntity<>(new CineterAdminCreateDTO(admin), HttpStatus.OK);
        }

        accountService.save(acc);
        return new ResponseEntity<>(new AccountDTO(acc), HttpStatus.OK);

    }

    @RequestMapping(
            value = "/api/profile",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity changeProfile(@RequestHeader("Authentication-Token") String token,
                                        @RequestBody ProfileChangeDTO profile){

        String username = jwtUtils.getUsernameFromToken(token);

        Account acc = accountService.findByUsername(username);

        acc.setName(profile.getName());
        acc.setLastName(profile.getLastName());

        accountService.save(acc);

        return new ResponseEntity<>(new AccountDTO(acc), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/send_request",
            method = RequestMethod.POST,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Send a request for friendship to another user.",
            notes = "User has to exist, and not be in your friendlist.",
            httpMethod = "POST",
            consumes = "text/plain",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Friend request successfully sent"),
            @ApiResponse(code = 404, message = "User doesn't exist")
    })
    public ResponseEntity send_request(@RequestHeader("Authentication-Token") String token,
                                       @RequestBody String receiverUsername) {
        if(!this.accountService.isUsernameTaken(receiverUsername))
            throw new NotFoundException("Account doesn't exist!");
        String senderUsername = jwtUtils.getUsernameFromToken(token);
        Account sender = this.accountService.findByUsername(senderUsername);
        Account receiver = this.accountService.findByUsername(receiverUsername);

        Friendship f = new Friendship(sender, receiver, FriendshipStatus.AWAITING);

        friendshipService.save(f);

        return new ResponseEntity(this.friendshipService.findBySenderAndReceiver(sender.getUsername(), receiver.getUsername()), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/get_friends",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllShows(@RequestHeader("Authentication-Token") String token) {
        String username = jwtUtils.getUsernameFromToken(token);
        List<Friendship> friendships = friendshipService.findAllBySender(username);
        ArrayList<AccountDTO> dtos = new ArrayList<>();

        for (Friendship friendship : friendships) {
            if(friendship.getStatus() != FriendshipStatus.ACCEPTED)
                continue;
            Account account = friendship.getReceiver();
            dtos.add(new AccountDTO(account));
        }

        friendships = friendshipService.findAllByReceiver(username);
        for (Friendship friendship : friendships) {
            if(friendship.getStatus() != FriendshipStatus.ACCEPTED)
                continue;
            Account account = friendship.getSender();
            dtos.add(new AccountDTO(account));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/get_requests",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRequests(@RequestHeader("Authentication-Token") String token) {
        String receiverUsername = jwtUtils.getUsernameFromToken(token);
        List<Friendship> friendships = friendshipService.findAllByReceiver(receiverUsername);
        ArrayList<AccountDTO> dtos = new ArrayList<>();

        for (Friendship friendship : friendships) {
            if(friendship.getStatus() != FriendshipStatus.AWAITING)
                continue;
            Account account = friendship.getSender();
            dtos.add(new AccountDTO(account));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/accept_request",
            method = RequestMethod.POST,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity accept_request(@RequestHeader("Authentication-Token") String token,
                                       @RequestBody String senderUsername) {
        try {
            String receiverUsername = jwtUtils.getUsernameFromToken(token);
            Account sender = this.accountService.findByUsername(senderUsername);
            Account receiver = this.accountService.findByUsername(receiverUsername);

            Friendship f = friendshipService.findBySenderAndReceiver(senderUsername, receiverUsername);
            f.setStatus(FriendshipStatus.ACCEPTED);

            friendshipService.save(f);

            return new ResponseEntity(null, HttpStatus.OK);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/api/decline_request",
            method = RequestMethod.POST,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity decline_request(@RequestHeader("Authentication-Token") String token,
                                         @RequestBody String senderUsername) {
        try {
            String receiverUsername = jwtUtils.getUsernameFromToken(token);
            Account sender = this.accountService.findByUsername(senderUsername);
            Account receiver = this.accountService.findByUsername(receiverUsername);

            Friendship f = friendshipService.findBySenderAndReceiver(senderUsername, receiverUsername);
            f.setStatus(FriendshipStatus.REJECTED);

            friendshipService.save(f);

            return new ResponseEntity(null, HttpStatus.OK);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/api/remove_friend",
            method = RequestMethod.POST,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeFriend(@RequestHeader("Authentication-Token") String token,
                                       @RequestBody String senderUsername){
        try {
            System.out.println(senderUsername);
            String receiverUsername = jwtUtils.getUsernameFromToken(token);
            System.out.println(receiverUsername);
            //Account sender = this.accountService.findByUsername(senderUsername);
            //Account receiver = this.accountService.findByUsername(receiverUsername);
            //System.out.println(sender);
            //System.out.println(receiver);

            Friendship f = friendshipService.findBySenderAndReceiver(senderUsername, receiverUsername);

            friendshipService.removeFriend(f.getId());

            return new ResponseEntity(null, HttpStatus.OK);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);

    }




    /*@RequestMapping(
            value = "/api/current_user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Get current logged user.",
            httpMethod = "GET",
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved current user")
    })
    public ResponseEntity getCurrentUser() {
        Account account = this.accountService.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        List<String> roles = new ArrayList<>();
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(
                authority -> roles.add(authority.getAuthority()));

        ResponseEntity responseEntity = null;
        if(roles.contains(MessageConstants.USER_ROLE))
            responseEntity = new ResponseEntity(new UserProfileDTO(account.getUser()), HttpStatus.OK);
        else if(roles.contains(MessageConstants.ADMIN_ROLE))
            responseEntity = new ResponseEntity(new AdminDTO(account.getAdministrator()), HttpStatus.OK);
        return responseEntity;
    }*/
}