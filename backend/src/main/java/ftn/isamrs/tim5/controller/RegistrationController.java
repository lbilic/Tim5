package ftn.isamrs.tim5.controller;

import ftn.isamrs.tim5.dto.AccountCreateDTO;
import ftn.isamrs.tim5.dto.AccountDTO;
import ftn.isamrs.tim5.exception.ForbiddenException;
import ftn.isamrs.tim5.model.Account;
import ftn.isamrs.tim5.model.AccountAuthority;
import ftn.isamrs.tim5.model.Authority;
import ftn.isamrs.tim5.service.AccountAuthorityService;
import ftn.isamrs.tim5.service.AccountService;
import ftn.isamrs.tim5.service.AuthorityService;
import ftn.isamrs.tim5.service.EmailService;
import ftn.isamrs.tim5.util.MessageConstants;
import io.swagger.annotations.*;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.MessagingException;

import javax.validation.Valid;

import static ftn.isamrs.tim5.util.ConvertDTOToModel.convertAccountCreateDTOToAccount;

@RestController
@Api(value="registration", description="Operations pertaining to registration in application.")
@CrossOrigin(value = "http://localhost:4200")
public class RegistrationController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AccountAuthorityService accountAuthorityService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(
            value = "/api/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(
            value = "Create a new account resource.",
            notes = "Returns the account being saved.",
            httpMethod = "POST",
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created account", response = AccountDTO.class),
            @ApiResponse(code = 400, message = "Inappropriate account object sent in request body"),
            @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
            @ApiResponse(code = 403, message = "You don't have permission to create resource"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity registerAccount(
            @ApiParam(value = "The account object", required = true) @Valid @RequestBody AccountCreateDTO accountCreateDTO,
            @ApiParam(value = "The object that contains all errors from validation of DTO object") BindingResult errors) {
        try {
            Boolean canRegister = false;
            Boolean isAdmin = false;

            for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
                if (authority.getAuthority().equals(MessageConstants.ADMIN_ROLE))
                    isAdmin = true;
                if (authority.getAuthority().equals("ROLE_ANONYMOUS"))
                    canRegister = true;
            }

            if (!canRegister && !isAdmin)
                throw new ForbiddenException("You need to logout to register or you need to be admin.");

            this.accountService.checkUsername(accountCreateDTO.getLoginAccount().getUsername());

            Account account = new Account(accountCreateDTO.getLoginAccount().getUsername(), accountCreateDTO.getLoginAccount().getPassword());
            //Mapiranje istoimenih atributa iz DTO objekta na objekat koji se snima u bazu
            Account acc = convertAccountCreateDTOToAccount(accountCreateDTO);
            //System.out.println(acc);
            //System.out.println(account);
            //if(isAdmin)
            //    acc.setConfirmed(true);
            //else
            //    acc.setConfirmed(false);
            account.setName(acc.getName());
            account.setLastName(acc.getLastName());
            account.setEmail(acc.getEmail());


            Authority authority = this.authorityService.findByName("USER");

            AccountAuthority accountAuthority = new AccountAuthority(account, authority);
            account.getAccountAuthorities().add(accountAuthority);
            try {
                emailService.sendActivationMail(account);
            } /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/ catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            account = this.accountService.save(account);

            accountAuthority.setAccount(account);
            accountAuthority.setAuthority(authority);
            this.accountAuthorityService.save(accountAuthority);
            return new ResponseEntity<>(new AccountDTO(account), HttpStatus.CREATED);
        } catch(OptimisticEntityLockException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(
            value = "/api/activate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> activateAccount(@RequestParam("activationId") String activationId) {
        Account account = accountService.findByActivationId(activationId);
        account.setConfirmed(true);
        boolean successActivate = false;
        System.out.println(account.getUsername());
        if(account != null)
            if(account.isConfirmed())
                successActivate = true;
        System.out.println(account.isConfirmed());
        accountService.save(account);
        return new ResponseEntity<>(successActivate, HttpStatus.OK);
    }

}
