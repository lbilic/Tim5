import { Component, OnInit } from '@angular/core';
import {CineterCreate} from "../../models/cineterCreate";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AddCinetarServiceService} from "../../services/add-cinetar-service.service";

@Component({
  selector: 'app-add-cinetar',
  templateUrl: './add-cinetar.component.html',
  styleUrls: ['./add-cinetar.component.css']
})
export class AddCinetarComponent implements OnInit {

  form : FormGroup;
  isTheater : boolean;

  constructor(private fb : FormBuilder, private cinetarService : AddCinetarServiceService) {
    this.form = this.fb.group({
      name: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],
      address: ['', [
        Validators.required,
        Validators.minLength(5)
      ]],
      city: ['', [
        Validators.required,
      ]],

    });
  }

  get name() {
    return this.form.get('name');
  }

  get address() {
    return this.form.get('address');
  }

  get city() {
    return this.form.get('city');
  }

  ngOnInit() {
  }

  register(){
    this.cinetarService.sendRegistration(new CineterCreate(this.name.value, this.address.value, this.city.value, this.
      isTheater));
  }

}
