import { Component, OnInit } from '@angular/core';
import {CineterCreate} from "../../models/cineterCreate";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CineterService} from "../../services/cineter/cineter.service";
import {Router, ActivatedRoute} from "@angular/router";


@Component({
  selector: 'app-add-cinetar',
  templateUrl: './add-cinetar.component.html',
  styleUrls: ['./add-cinetar.component.css']
})
export class AddCinetarComponent implements OnInit {

  form : FormGroup;
  returnURL: string = '';
  isTheater : boolean;

  constructor(private fb : FormBuilder, private router: Router,
              private cinetarService : CineterService, private route: ActivatedRoute) {
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
    this.returnURL = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  register(){
    this.cinetarService.sendRegistration(new CineterCreate(this.name.value,
      this.address.value, this.city.value, this.isTheater)).subscribe(data => {
      this.router.navigateByUrl(this.returnURL);
    });
  }
}
