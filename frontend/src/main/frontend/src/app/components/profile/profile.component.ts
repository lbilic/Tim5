import { Component, OnInit } from '@angular/core';
import {AccountService} from "../../services/account/account.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Account} from "../../models/account";
import {Login} from "../../models/login";
import {ProfileDisplay} from "../../models/profile-display";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  form: FormGroup;
  returnURL: string = '';
  private account: ProfileDisplay;

  constructor(private fb : FormBuilder, private accountService: AccountService, private router: Router, 
    private route: ActivatedRoute) {

    this.account = new ProfileDisplay('', '', '', '');

    accountService.getCurrentUser().subscribe(data => {
      this.account = data as ProfileDisplay;
    });
      this.form = this.fb.group({
        name: ['', [
          Validators.required,
          Validators.minLength(3),

        ]],
        username: [{disabled: true, value: ''}, [
          Validators.required,
          Validators.minLength(3)
        ]],
        lastname: ['', [
          Validators.required,
          Validators.minLength(5)
        ]],
        email: [{disabled: true, value: ''}, [
          Validators.required,
          Validators.email
        ]],
    });
  }

  ngOnInit() {
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/profil';
  }

  get name()
  {
    return this.form.get('name');
  }

  get lastname()
  {
    return this.form.get('lastname');
  }

  get email()
  {
    return this.form.get('email');
  }

  get username()
  {
    return this.form.get('username');
  }

  changeProfile() {
    let acc = new ProfileDisplay(this.name.value, this.lastname.value, this.email.value, this.username.value);
    this.accountService.changeProfile(acc).subscribe(data => {
      this.router.navigateByUrl(this.returnURL);
    });

}}
