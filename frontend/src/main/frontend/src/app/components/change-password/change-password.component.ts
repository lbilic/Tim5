import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../../services/account/account.service";
import {Password} from "../../models/password";
import {Router, ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  form: FormGroup;
  returnURL: string = '';

  constructor(private fb : FormBuilder, private accountService: AccountService, private router: Router, private route: ActivatedRoute ) {
    this.form = this.fb.group({
      oldPassword: ['', [
        Validators.required
      ]],
      newPassword: ['', [
        Validators.required
      ]],
      confirmPassword: ['', [
        Validators.required
      ]],
    })
  }

  get oldPassword(){
    return this.form.get("oldPassword");
  }

  get newPassword(){
    return this.form.get("newPassword");
  }

  get confirmPassword(){
    return this.form.get("confirmPassword");
  }

  ngOnInit() {
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  changePassword(){
    let passwordChange = new Password(this.oldPassword.value, this.newPassword.value, this.confirmPassword.value);
    this.accountService.changePassword(passwordChange).subscribe(data =>{
      this.router.navigateByUrl(this.returnURL);
    });
  }

}
