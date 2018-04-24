package ftn.isamrs.tim5.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ftn.isamrs.tim5.exception.BadRequestException;
import ftn.isamrs.tim5.exception.NotFoundException;
import ftn.isamrs.tim5.model.User;
import ftn.isamrs.tim5.security.JWTUtils;
import ftn.isamrs.tim5.service.AccountService;
import ftn.isamrs.tim5.util.MessageConstants;
import ftn.isamrs.tim5.dto.LoginDTO;
import ftn.isamrs.tim5.dto.TokenDTO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@Api(value="user's essentials functionalities", description="Operations pertaining to all kind of users in application.")
public class AppUserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AccountService accountService;

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

        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword());
            authenticationManager.authenticate(token);
            User account = this.accountService.findByUsername(loginDTO.getUsername());

            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());

            Long id = account.getId();

            TokenDTO userToken = new TokenDTO(jwtUtils.generateToken(details, id));
            return new ResponseEntity(userToken, HttpStatus.OK);
        } catch (Exception ex) {
            throw new NotFoundException("Invalid login!");
        }
    }
}