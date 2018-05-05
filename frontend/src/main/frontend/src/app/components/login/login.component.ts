import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import 'rxjs/add/operator/catch';
import { ToasterConfig, ToasterService } from "angular5-toaster/dist";
// error
import { AppError } from "../../shared/errors/app-error";
import { BadRequestError } from "../../shared/errors/bad-request-error";
import { NotFoundError } from "../../shared/errors/not-found-error";
// service
import { AuthService } from "../../services/auth.service";
// validator
import { SpaceValidator } from "../../shared/validators/space.validator";
// model
import { Login } from "../../shared/models/login";
import { JwtService } from '../../services/jwt.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  providers: [JwtService, AuthService],
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  returnURL: string = '';
  toasterConfig: ToasterConfig;

  constructor(private fb: FormBuilder, private authService: AuthService,
              private router: Router, private route: ActivatedRoute, private toasterService: ToasterService) {
    this.form = this.fb.group({
      username: ['', [Validators.required, SpaceValidator.cannotContainSpace]],
      password: ['', Validators.required]
    });
    this.toasterConfig = new ToasterConfig({timeout: 4000});
  }

  ngOnInit() {
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get username() {
    return this.form.get('username');
  }

  get password() {
    return this.form.get('password');
  }

  login() {
    let login = new Login(this.username.value, this.password.value);
    this.authService.login(login)
      .subscribe((successfullyLoggedIn) => {
        if(successfullyLoggedIn)
          this.router.navigateByUrl(this.returnURL);
        else
          this.toasterService.pop('error', 'Error', 'Invalid login');
      }, (error: AppError) => {
        if (error instanceof BadRequestError)
          this.toasterService.pop('error', 'Error','Invalid format of given data!');
        else if (error instanceof NotFoundError)
          this.toasterService.pop('error', 'Error', 'Bad credentials!');
        else {
          this.toasterService.pop('error', 'Error', 'Something unexpected happened! \nSee information about error in console.');
          throw error;
        }
      });
  }
}
