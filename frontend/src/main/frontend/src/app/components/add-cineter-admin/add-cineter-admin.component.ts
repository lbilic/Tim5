import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../../services/admin/admin.service";
import {CineterAdminCreate} from "../../models/cineterAdminCreate";
import {AddCinetarServiceService} from "../../services/cineter/add-cinetar-service.service";
import {CineterCreate} from "../../models/cineterCreate";

@Component({
  selector: 'app-add-cineter-admin',
  templateUrl: './add-cineter-admin.component.html',
  styleUrls: ['./add-cineter-admin.component.css']
})
export class AddCineterAdminComponent implements OnInit {

  form: FormGroup;
  isFanZone : boolean;
  cineters  : Array<string>;

  constructor(private fb : FormBuilder, private adminService: AdminService, private cineterService : AddCinetarServiceService) {
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
    this.cineterService.getAllCineters().subscribe(data =>{
        let cineters =  data as CineterCreate[];
        this.cineters = new Array<string>();
        for(let i = 0; i < cineters.length; i++)
        {
            this.cineters.push(cineters[i].name + "," + cineters[i].city + "," + cineters[i].address);
        }
        console.log(this.cineters);
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
    let admin = new CineterAdminCreate(this.name.value, this.lastname.value, this.email.value, this.password.value, this.number.value, this.isFanZone);
    this.adminService.registerCineterAdmin(admin).subscribe(data => {
      console.log(data);
    });
  }
}
