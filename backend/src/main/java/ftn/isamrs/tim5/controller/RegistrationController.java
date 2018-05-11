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
import ftn.isamrs.tim5.util.MessageConstants;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static ftn.isamrs.tim5.util.ConvertDTOToModel.convertAccountCreateDTOToAccount;

@RestController
@Api(value="registration", description="Operations pertaining to registration in application.")
public class RegistrationController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AccountAuthorityService accountAuthorityService;

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
            @ApiResponse(code = 201, message = "Successfully created tenant", response = AccountDTO.class),
            @ApiResponse(code = 400, message = "Inappropriate account object sent in request body"),
            @ApiResponse(code = 401, message = "You are not authorized to create the resource"),
            @ApiResponse(code = 403, message = "You don't have permission to create resource"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity registerAccount(
            @ApiParam(value = "The account object", required = true) @Valid @RequestBody AccountCreateDTO accountCreateDTO,
            @ApiParam(value = "The object that contains all errors from validation of DTO object") BindingResult errors) {

        Boolean canRegister = false;
        Boolean isAdmin = false;

        for(GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities())
        {
            if(authority.getAuthority().equals(MessageConstants.ADMIN_ROLE))
                isAdmin = true;
            if(authority.getAuthority().equals("ROLE_ANONYMOUS"))
                canRegister = true;
        }

        if(!canRegister && !isAdmin) throw new ForbiddenException("You need to logout to register or you need to be admin.");
        Account acc = convertAccountCreateDTOToAccount(accountCreateDTO);
        System.out.println(acc.getUsername());
        this.accountService.checkUsername(accountCreateDTO.getLoginAccount().getUsername());

        Account account = new Account(accountCreateDTO.getLoginAccount().getUsername(), accountCreateDTO.getLoginAccount().getPassword());
        //Mapiranje istoimenih atributa iz DTO objekta na objekat koji se snima u bazu
        //Account acc = convertAccountCreateDTOToAccount(accountCreateDTO);
        //System.out.println(acc);
        //System.out.println(account);
        //if(isAdmin)
        //    acc.setConfirmed(true);
        //else
        //    acc.setConfirmed(false);


        Authority authority = this.authorityService.findByName("USER");

        AccountAuthority accountAuthority = new AccountAuthority(account, authority);
        account.getAccountAuthorities().add(accountAuthority);
        account = this.accountService.save(account);

        //acc.setAccount(account);
        //acc = this.tenantService.save(tenant);
        //account.setTenant(tenant);
        account = this.accountService.save(account);

        accountAuthority.setAccount(account);
        accountAuthority.setAuthority(authority);
        this.accountAuthorityService.save(accountAuthority);
        System.out.println("OVDE4");
        return new ResponseEntity<>(new AccountDTO(account), HttpStatus.CREATED);
    }

}
