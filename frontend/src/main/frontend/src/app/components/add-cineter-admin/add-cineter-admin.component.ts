import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../../services/admin/admin.service";
import {CineterAdminCreate} from "../../models/cineterAdminCreate";
import {CineterCreate} from "../../models/cineterCreate";
import {CineterService} from "../../services/cineter/cineter.service";

@Component({
  selector: 'app-add-cineter-admin',
  templateUrl: './add-cineter-admin.component.html',
  styleUrls: ['./add-cineter-admin.component.css']
})
export class AddCineterAdminComponent implements OnInit {

  form: FormGroup;
  cineters: Array<CineterCreate>;
  selected_index : number;
  isFanZone;

  constructor(private fb : FormBuilder, private adminService: AdminService, private cineterService : CineterService) {
    this.form = this.fb.group({
      name: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],
      username: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],
      lastname: ['', [
        Validators.required,
        Validators.minLength(5)
      ]],
      email: ['', [
        Validators.required,
        Validators.email
      ]],

      password: ['', [
        Validators.required
      ]],
      number: ['',
        Validators.required
      ]

    });

    this.selected_index = 0;

    this.cineterService.getAllCineters().subscribe(data => {
      this.cineters = data as Array<CineterCreate>;
    });
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


  get password()
  {
    return this.form.get('password');
  }

  get number()
  {
    return this.form.get('number');
  }

  ngOnInit() {
  }

  register(){
    let admin = new CineterAdminCreate(this.name.value, this.lastname.value, this.email.value, this.password.value, this.number.value, this.username.value, this.isFanZone,
      this.cineters[this.selected_index]);
    this.adminService.registerCineterAdmin(admin).subscribe(data => {
      console.log(data);
    });
  }

  onChange(value){
    this.selected_index = value;
  }
}
