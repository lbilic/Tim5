import { Component, OnInit } from '@angular/core';
import {Cineter} from "../../models/cineter";
import {ActivatedRoute, Params} from "@angular/router";
import {CineterService} from "../../services/cineter/cineter.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-cineter-details',
  templateUrl: './cineter-details.component.html',
  styleUrls: ['./cineter-details.component.css']
})
export class CineterDetailsComponent implements OnInit {
  cineter : Cineter;
  id : number;
  form : FormGroup;
  constructor(private fb: FormBuilder, private route : ActivatedRoute, private cineterService: CineterService) {
    this.route.params.subscribe((param: Params) => {
      this.id = param['id'];
      this.getCineter();
    });

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

  ngOnInit() {
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

  private getCineter() {
    this.cineterService.getCineterById(this.id).subscribe(data => {
      this.cineter = data as Cineter;
      console.log(this.cineter);
    });
  }

  clicked()
  {
    this.cineter.isTheater = !this.cineter.isTheater;
    this.cineter.theater = !this.cineter.theater;
    console.log(this.cineter.isTheater);
  }

  private change()
  {
    console.log(this.cineter);
    this.cineterService.updateCineter(this.cineter).subscribe(data =>{

    });
  }
}
