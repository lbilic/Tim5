import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../../services/admin/admin.service";
import {CineterAdminCreate} from "../../models/cineterAdminCreate";

@Component({
  selector: 'app-add-cineter-admin',
  templateUrl: './add-cineter-admin.component.html',
  styleUrls: ['./add-cineter-admin.component.css']
})
export class AddCineterAdminComponent implements OnInit {

  form: FormGroup;
  constructor(private fb : FormBuilder, private adminService: AdminService) {
    this.form = this.fb.group({
      name: ['', [
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
    let admin = new CineterAdminCreate(this.name.value, this.lastname.value, this.email.value, this.password.value, this.number.value);
    this.adminService.registerCineterAdmin(admin).subscribe(data => {
      console.log(data);
    });
  }
}
