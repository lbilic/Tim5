import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from "@angular/forms";
import { ToasterConfig, ToasterService } from "angular5-toaster/dist";
import { ActivatedRoute, Router } from "@angular/router";
import 'rxjs/add/operator/catch';
// validator
import { UsernameUniqueValidator } from "../../shared/validators/username-unique.validator";
import { PasswordValidator } from "../../shared/validators/password.validator";
import { SpaceValidator } from "../../shared/validators/space.validator";
// error
import { AppError } from "../../shared/errors/app-error";
import { BadRequestError } from "../../shared/errors/bad-request-error";
import { NotFoundError } from "../../shared/errors/not-found-error";
//models
import { Login } from "../../shared/models/login";
import { Account } from "../../models/account";
//services
import { AccountService } from "../../services/account/account.service"
import { JwtService } from '../../services/jwt.service';
import { AuthService } from "../../services/auth.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  providers: [JwtService, ToasterService, PasswordValidator, UsernameUniqueValidator]
})
export class RegistrationComponent implements OnInit {
  form: FormGroup;
  returnURL: string;
  maxDate: Date;
  toasterConfig: ToasterConfig;

  constructor(private fb: FormBuilder, private accountService: AccountService, private router: Router, private route: ActivatedRoute,
              private usernameUniqueValidator: UsernameUniqueValidator, private passwordValidator: PasswordValidator,
              private toasterService: ToasterService) {
    this.form = this.fb.group({
      firstName: ['', [
        Validators.required,
        Validators.minLength(2)
        ]],
      lastName: ['', [
        Validators.required,
        Validators.minLength(2)
        ]],
      username: ['', [
          Validators.required,
          SpaceValidator.cannotContainSpace
          ],
        this.usernameUniqueValidator.isUsernameTaken.bind(this.usernameUniqueValidator)
        ],
      passwords: fb.group({
        password: ['', [
            Validators.required,
            Validators.minLength(5)
            ]],
          confirmPassword: ['',
            Validators.required
            ]
      }, {validator: this.passwordValidator.matchPasswords.bind(this.passwordValidator)}),
      email: ['', [
        Validators.required, Validators.email
        ]]
      });

    this.toasterConfig = new ToasterConfig({
    timeout: {success: 2000, error: 4000}
    })}

  ngOnInit() {
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get firstName() {
      return this.form.get('firstName');
    }

  get lastName() {
      return this.form.get('lastName');
    }

  get username() {
      return this.form.get('username');
    }

  get passwords() {
      return this.form.get('passwords');
    }

  get password() {
      return this.form.get('passwords').get('password');
    }

  get confirmPassword() {
      return this.form.get('passwords').get('confirmPassword');
    }

  get email() {
      return this.form.get('email');
    }

  register() {
      let login = new Login(this.username.value, this.password.value);
      console.log(login);
      let account = new Account(login, this.firstName.value, this.lastName.value, this.email.value);
      console.log(account);
      this.accountService.save(account)
        .subscribe(() => {
          this.router.navigateByUrl(this.returnURL);
        }, (error: AppError) => {
          if (error instanceof BadRequestError)
            this.toasterService.pop('error', 'Error', 'Given data have bad format!');
          else if (error instanceof NotFoundError)
            this.toasterService.pop('error', 'Error', 'Registration can\'t be done, because account role is missing!');
          else {
            this.toasterService.pop('error', 'Error', 'Something unexpected happened! \nSee information about error in console.');
            throw error;
          }
        });
    }
}
